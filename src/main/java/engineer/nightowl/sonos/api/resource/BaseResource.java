package engineer.nightowl.sonos.api.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.SonosApiConfiguration;
import engineer.nightowl.sonos.api.enums.SonosType;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Validatable;
import engineer.nightowl.sonos.api.util.SonosUtilityHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

/**
 * Generic base class used for integrating with the Sonos API.
 */
class BaseResource {
    // Default ObjectMapper.
    private static final ObjectMapper OM = new ObjectMapper()
            // Allow unknown properties - these may be added by Sonos, but Jackson will
            // error if not specified in the relevant POJO
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private static final String ASYNC_ERROR_MSG = "Failure during async method execution";
    /**
     * Sonos can provide a header describing the response type
     *
     * @see engineer.nightowl.sonos.api.enums.SonosType
     */
    static final String SONOS_TYPE_HEADER = "X-Sonos-Type";
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    SonosApiClient apiClient;

    BaseResource(final SonosApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Main API call method. Takes in a {@link HttpUriRequest} comprising of a URI
     * and method
     *
     * @param request with the relevant URI and method (with associated data as
     *                appropriate)
     * @param type    what the response should interpreted as
     * @return the type specified in 'type'
     * @throws SonosApiClientException if unable to build or execute the request
     * @throws SonosApiError           if the Sonos API returns an error to an
     *                                 otherwise successful request
     * @throws InterruptedException
     */
    <T> CompletableFuture<T> callApiAsync(final HttpRequest request, final Class<T> type)
            throws SonosApiClientException, SonosApiError
    {
        logger.debug("Sending request to {}", request.uri());
        return apiClient.getHttpClient().sendAsync(request, BodyHandlers.ofInputStream()).thenApply(resp -> handleApiResponseAsync(resp, type));
    }

    <T> T callApi(final HttpRequest request, final Class<T> type) throws SonosApiClientException, SonosApiError {
        try {
            return callApiAsync(request, type).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new SonosApiClientException(ASYNC_ERROR_MSG, e);
        }
    }

    <T> T handleApiResponseAsync(final HttpResponse<InputStream> response, final Class<T> type)
    {
        if (401 == response.statusCode())
        {
            throw new CompletionException(new SonosApiClientException("Invalid token"));
        }

        final byte[] bytes;
        try (final InputStream stream = response.body())
        {
            bytes = stream.readAllBytes();
        } catch (final IOException ioe)
        {
            throw new CompletionException(new SonosApiClientException("Unable to convert response body", ioe));
        }

        logger.debug("Raw response from API: {}", response);
        logger.debug("Raw response content from API: {}", bytes);

        // Get type from Sonos response - not always possible
        SonosType sonosDeclaredClass;
        try {
            sonosDeclaredClass = getTypeFromHeader(response.headers());
        } catch (SonosApiClientException sace) {
            throw new CompletionException(sace);
        }

        final String sonosDeclaredClassName = sonosDeclaredClass == null ? null : sonosDeclaredClass.getClazz().getSimpleName();

        // If Sonos didn't provide a type, or if one was provided and it matches what we wanted returned, proceed
        if (sonosDeclaredClassName == null || sonosDeclaredClassName.equals(type.getSimpleName()))
        {
            try
            {
                return type.cast(OM.readValue(bytes, Class.forName(type.getName())));
            } catch (final IOException | ClassNotFoundException e)
            {
                final String msg = String.format("Unexpected error converting response to %s (Sonos declared %s)", type.getSimpleName(), sonosDeclaredClassName);
                throw new CompletionException(new SonosApiClientException(msg, e));
            }
        }
        // Otherwise it's not what we expected - likely an error object, in which case throw an exception with the mapped object
        else
        {
            final Object responseContent;
            try
            {
                responseContent = OM.readValue(bytes, sonosDeclaredClass.getClazz());
            } catch (final IOException e)
            {
                throw new CompletionException(new SonosApiClientException("Unable to parse error response from Sonos", e));
            }

            if (SonosType.getErrorTypes().contains(sonosDeclaredClass))
            {
                throw new CompletionException((SonosApiError) sonosDeclaredClass.getClazz().cast(responseContent));
            } else
            {
                final String mismatchMsg = String.format("Sonos declared %s as the response type, but the integration requested %s", sonosDeclaredClassName, type.getSimpleName());
                throw new CompletionException(new SonosApiClientException(mismatchMsg));
            }
        }
    }

    /**
     * Obtain the declared object type given by Sonos in the response header
     *
     * @param response - raw response to fetch the header from
     * @return the {@link SonosType} declared, or null if not found
     */
    SonosType getTypeFromHeader(final HttpHeaders headers) throws SonosApiClientException
    {
        final Optional<String> header = headers.firstValue(SONOS_TYPE_HEADER);
        if (header.isPresent() && !SonosUtilityHelper.isEmpty(header.get()))
        {
            final String headerValue = header.get();
            if (!"none".equalsIgnoreCase(headerValue))
            {
                try
                {
                    return SonosType.valueOf(headerValue);
                }
                catch (final IllegalArgumentException iae)
                {
                    final String msg = String.format("Unexpected return type [%s] - please raise a bug", headerValue);
                    throw new SonosApiClientException(msg, iae);
                }
            }
        }
        return null;
    }

    /**
     * Simple 'GET' from API
     *
     * @param returnType class reference to type to map response as
     * @param token      for the user
     * @param path       for the API resource
     * @param <T>        class reference to use as the response type
     * @return the response from the Sonos API as the specified type
     * @throws SonosApiClientException if an error occurs during the call
     * @throws SonosApiError           if the API returns an error
     * @throws ExecutionException
     * @throws InterruptedException
     */
    <T> T getFromApi(final Class<T> returnType, final String token, final String path)
            throws SonosApiClientException, SonosApiError
    {
        return callApi(buildGetRequest(token, path).build(), returnType);
    }

    /**
     * Simple 'DELETE' from API
     *
     * @param returnType class reference to type to map response as
     * @param token      for the user
     * @param path       for the API resource
     * @param <T>        class reference to use as the response type
     * @return the response from the Sonos API as the specified type
     * @throws SonosApiClientException if an error occurs during the call
     * @throws SonosApiError           if the API returns an error
     * @throws ExecutionException
     * @throws InterruptedException
     */
    <T> T deleteFromApi(final Class<T> returnType, final String token, final String path)
            throws SonosApiClientException, SonosApiError
    {
        return callApi(buildDeleteRequest(token, path).build(), returnType);
    }

    /**
     * Simple empty 'POST' to API
     *
     * @param returnType class reference to type to map response as
     * @param token      for the user
     * @param path       for the API resource
     * @param <T>        class reference to use as the response type
     * @return the response from the Sonos API as the specified type
     * @throws SonosApiClientException if an error occurs during the call
     * @throws SonosApiError           if the API returns an error
     * @throws ExecutionException
     * @throws InterruptedException
     */
    <T> T postToApi(final Class<T> returnType, final String token, final String path)
            throws SonosApiClientException, SonosApiError
    {
        try {
            return postToApiAsync(returnType, token, path).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new SonosApiClientException(ASYNC_ERROR_MSG, e);
        }
    }

    <T> CompletableFuture<T> postToApiAsync(final Class<T> returnType, final String token, final String path) throws SonosApiClientException, SonosApiError
    {
        return postToApiAsync(returnType, token, path, null);
    }

    <T, U> T postToApi(final Class<T> returnType, final String token, final String path,
                       final U content) throws SonosApiClientException, SonosApiError
    {
        try {
            return postToApiAsync(returnType, token, path, content).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new SonosApiClientException(ASYNC_ERROR_MSG, e);
        }
    }

    /**
     * Simple 'POST' to API
     *
     * @param returnType class reference to type to map response as
     * @param token      for the user
     * @param path       for the API resource
     * @param content    for the body of the POST request
     * @param <T>        class reference to use as the response type
     * @param <U>        class reference for the request type
     * @return the response from the Sonos API as the specified type
     * @throws SonosApiClientException if an error occurs during the call
     * @throws SonosApiError           if the API returns an error
     */
    <T, U> CompletableFuture<T> postToApiAsync(final Class<T> returnType, final String token, final String path,
                       final U content) throws SonosApiClientException, SonosApiError
    {
        final HttpRequest.Builder request = buildPostRequest(token, path);
        final Boolean validationEnabled = apiClient.getConfiguration().isClientSideValidationEnabled();
        // If the content for the request has the ability to be validated, do so if enabled.
        // If the object is invalid, there's no point sending it to the API to be rejected.
        if (Boolean.TRUE.equals(validationEnabled) && content instanceof Validatable)
        {
            ((Validatable) content).validate();
        }
        if (!SonosUtilityHelper.isEmpty(content))
        {
            final String json;
            try
            {
                json = OM.writeValueAsString(content);
            } catch (final JsonProcessingException e)
            {
                throw new SonosApiClientException("Unable to convert POST request parameters", e);
            }
            request.POST(BodyPublishers.ofString(json));
        }

        return callApiAsync(request.build(), returnType);
    }

    /**
     * Build up a generic request
     *
     * @param requestType e.g. {@link HttpGet} or {@link HttpPost}
     * @param token       for the user
     * @param path        for the API resource
     * @param <T>         for the requestType
     * @return a generic request
     * @throws SonosApiClientException if an error occurs building the request
     */
    HttpRequest.Builder buildStandardRequest(final HttpRequest.Builder request, final String token,
                                                             final String path) throws SonosApiClientException
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        URI uri;
        try {
            uri = new URI("https", configuration.getControlBaseUrl(), path, null);
        } catch (URISyntaxException e) {
            throw new SonosApiClientException("Invalid URI constructed", e);
        }

        request.setHeader("Authorization", String.format("Bearer %s", token));
        request.uri(uri);

        return request;
    }

    /**
     * Helper method to generate a basic GET request
     *
     * @param token for the user
     * @param path  for the API resource
     * @return a basic GET request
     * @throws SonosApiClientException if an error occurs building the request
     */
    HttpRequest.Builder buildGetRequest(final String token, final String path) throws SonosApiClientException
    {
        return buildStandardRequest(HttpRequest.newBuilder().GET(), token, path);
    }

    /**
     * Helper method to generate a basic GET request
     *
     * @param token for the user
     * @param path  for the API resource
     * @return a basic DELETE request
     * @throws SonosApiClientException if an error occurs building the request
     */
    HttpRequest.Builder buildDeleteRequest(final String token, final String path) throws SonosApiClientException
    {
        return buildStandardRequest(HttpRequest.newBuilder().DELETE(), token, path);
    }

    /**
     * Helper method to generate a basic POST request
     *
     * @param token for the user
     * @param path  for the API resource
     * @return a basic POST request
     * @throws SonosApiClientException if an error occurs building the request
     */
    HttpRequest.Builder buildPostRequest(final String token, final String path) throws SonosApiClientException
    {
        return buildStandardRequest(HttpRequest.newBuilder().POST(BodyPublishers.ofString(null)), token, path);
    }

    void validateNotNull(final Object o) throws SonosApiClientException
    {
        validateNotNull(o, null);
    }

    void validateNotNull(final Object o, final String fieldName) throws SonosApiClientException
    {
        if (SonosUtilityHelper.isEmpty(o))
        {
            final StringBuilder sb = new StringBuilder().append("Missing required parameter");
            if (fieldName != null)
            {
                sb.append(" - (").append(fieldName).append(")");
            }
            throw new SonosApiClientException(sb.toString());
        }
    }
}
