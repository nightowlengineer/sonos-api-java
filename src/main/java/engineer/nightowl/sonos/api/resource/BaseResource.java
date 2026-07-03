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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;

/**
 * Generic base class used for integrating with the Sonos API.
 */
class BaseResource
{
    // Default ObjectMapper.
    private static final ObjectMapper OM = new ObjectMapper()
            // Allow unknown properties - these may be added by Sonos, but Jackson will
            // error if not specified in the relevant POJO
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    /**
     * Sonos can provide a header describing the response type
     *
     * @see engineer.nightowl.sonos.api.enums.SonosType
     */
    static final String SONOS_TYPE_HEADER = "X-Sonos-Type";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    SonosApiClient apiClient;

    BaseResource(final SonosApiClient apiClient)
    {
        this.apiClient = apiClient;
    }

    /**
     * Main API call method. Takes in a {@link HttpRequest} comprising of a
     * URI and method
     *
     * @param request with the relevant URI and method (with associated data as
     *                appropriate)
     * @param type    what the response should interpreted as
     * @return the type specified in 'type'
     * @throws SonosApiClientException if unable to build or execute the request
     * @throws SonosApiError           if the Sonos API returns an error to an otherwise successful request
     */
    <T> T callApi(final HttpRequest request, final Class<T> type) throws SonosApiClientException, SonosApiError
    {
        logger.debug("Sending request to {}", request.uri());
        final HttpResponse<byte[]> response = executeRequest(request);

        if (401 == response.statusCode())
        {
            throw new SonosApiClientException("Invalid token");
        }
        final byte[] bytes = response.body();

        logger.debug("Raw response from API: {}", response);
        if (logger.isDebugEnabled())
        {
            // A token response body contains OAuth access/refresh tokens - never write those to logs,
            // even at debug. Other bodies are decoded as text (logging the raw byte[] would just print
            // its identity hash, which is useless).
            if (isTokenResponse(request))
            {
                logger.debug("Raw response content from API: <redacted - token response>");
            }
            else
            {
                logger.debug("Raw response content from API: {}", new String(bytes, StandardCharsets.UTF_8));
            }
        }

        // Get type from Sonos response - not always possible
        final SonosType sonosDeclaredClass = getTypeFromHeader(response);
        final String sonosDeclaredClassName = sonosDeclaredClass == null ? null : sonosDeclaredClass.getClazz().getSimpleName();

        // A non-2xx response that Sonos did not describe with a structured error type would otherwise be
        // handed to the deserializer for the requested type, producing a confusing "converting response"
        // parse error. Surface the HTTP status (and a short body snippet) instead. Responses that DO carry
        // a declared error type still flow through to the SonosApiError handling below, regardless of status.
        final int status = response.statusCode();
        final boolean sonosDeclaredError = sonosDeclaredClass != null && SonosType.getErrorTypes().contains(sonosDeclaredClass);
        if ((status < 200 || status >= 300) && !sonosDeclaredError)
        {
            final String detail = isTokenResponse(request) ? "<redacted - token response>" : bodySnippet(bytes);
            throw new SonosApiClientException(String.format("Sonos API returned HTTP %d: %s", status, detail));
        }

        // If Sonos didn't provide a type, or if one was provided and it matches what we wanted returned, proceed
        if (sonosDeclaredClassName == null || sonosDeclaredClassName.equals(type.getSimpleName()))
        {
            try
            {
                return OM.readValue(bytes, type);
            } catch (final IOException e)
            {
                final String msg = String.format("Unexpected error converting response to %s (Sonos declared %s)", type.getSimpleName(), sonosDeclaredClassName);
                throw new SonosApiClientException(msg, e);
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
                throw new SonosApiClientException("Unable to parse error response from Sonos", e);
            }

            if (SonosType.getErrorTypes().contains(sonosDeclaredClass))
            {
                throw (SonosApiError) sonosDeclaredClass.getClazz().cast(responseContent);
            } else
            {
                final String mismatchMsg = String.format("Sonos declared %s as the response type, but the integration requested %s", sonosDeclaredClassName, type.getSimpleName());
                throw new SonosApiClientException(mismatchMsg);
            }
        }
    }

    /**
     * Execute a request, wrapping transport failures in a {@link SonosApiClientException}. The response
     * body is fully buffered before this method returns, so there is nothing for callers to close.
     *
     * @param request the request to execute
     * @return the response
     * @throws SonosApiClientException if the request could not be executed
     */
    private HttpResponse<byte[]> executeRequest(final HttpRequest request) throws SonosApiClientException
    {
        try
        {
            return apiClient.getHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray());
        } catch (final IOException e)
        {
            throw new SonosApiClientException("Error interrogating Sonos API", e);
        } catch (final InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new SonosApiClientException("Interrupted while calling Sonos API", e);
        }
    }

    /**
     * Obtain the declared object type given by Sonos in the response header
     *
     * @param response - raw response to fetch the header from
     * @return the {@link SonosType} declared, or null if not found
     */
    SonosType getTypeFromHeader(final HttpResponse<?> response) throws SonosApiClientException
    {
        if (response != null)
        {
            final Optional<String> header = response.headers().firstValue(SONOS_TYPE_HEADER);
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
     */
    <T> T getFromApi(final Class<T> returnType, final String token, final String path) throws SonosApiClientException, SonosApiError
    {
        final HttpRequest request = getGetRequest(token, path);
        return callApi(request, returnType);
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
     */
    <T> T deleteFromApi(final Class<T> returnType, final String token, final String path) throws SonosApiClientException, SonosApiError
    {
        final HttpRequest request = getDeleteRequest(token, path);
        return callApi(request, returnType);
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
     */
    <T> T postToApi(final Class<T> returnType, final String token, final String path) throws SonosApiClientException, SonosApiError
    {
        // Pass null (not an empty array) so isEmpty() sees "no content" and the request is sent with
        // no body. isEmpty() does not treat a non-null array as empty, so a sentinel like new String[0]
        // would be serialized to "[]" and sent as a bogus application/json body.
        return postToApi(returnType, token, path, (Object) null);
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
    <T, U> T postToApi(final Class<T> returnType, final String token, final String path,
                       final U content) throws SonosApiClientException, SonosApiError
    {
        final HttpRequest.Builder builder = getStandardRequest(token, path);
        final Boolean validationEnabled = apiClient.getConfiguration().isClientSideValidationEnabled();
        // If the content for the request has the ability to be validated, do so if enabled.
        // If the object is invalid, there's no point sending it to the API to be rejected.
        if (Boolean.TRUE.equals(validationEnabled) && content instanceof Validatable)
        {
            ((Validatable) content).validate();
        }

        final HttpRequest request;
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
            request = builder.setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                    .build();
        }
        else
        {
            request = builder.POST(HttpRequest.BodyPublishers.noBody()).build();
        }
        return callApi(request, returnType);
    }

    /**
     * Build up a generic request builder, decorated with the standard URI and Bearer auth header used by
     * every Sonos control API call. The caller finishes the builder (e.g. {@code .GET().build()}).
     *
     * @param token for the user
     * @param path  for the API resource
     * @return a decorated, not-yet-finished request builder
     * @throws SonosApiClientException if an error occurs building the request
     */
    HttpRequest.Builder getStandardRequest(final String token, final String path) throws SonosApiClientException
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final URI uri = buildControlUri(configuration.getControlBaseUrl(), path);

        final HttpRequest.Builder builder = HttpRequest.newBuilder(uri)
                .setHeader("Authorization", String.format("Bearer %s", token))
                .setHeader("User-Agent", apiClient.getUserAgent());
        if (isPositive(configuration.getRequestTimeout()))
        {
            builder.timeout(configuration.getRequestTimeout());
        }
        return builder;
    }

    /**
     * Build the control API URI for a path, percent-encoding the path segments (which may contain
     * interpolated resource IDs). {@code controlBaseUrl} bundles the host with a base path, e.g.
     * {@code api.ws.sonos.com/control/api}, so it is split on the first {@code /} to let the multi-argument
     * {@link URI} constructor encode the assembled path - unlike {@link URI#create(String)}, which requires
     * an already-valid URI string and would reject (rather than encode) a stray space or reserved character.
     *
     * @param controlBaseUrl the configured control base URL (host + optional base path)
     * @param path           the resource path, already interpolated with any IDs
     * @return the assembled, encoded URI
     * @throws SonosApiClientException if the URI could not be built
     */
    private static URI buildControlUri(final String controlBaseUrl, final String path) throws SonosApiClientException
    {
        final int firstSlash = controlBaseUrl.indexOf('/');
        final String host = firstSlash < 0 ? controlBaseUrl : controlBaseUrl.substring(0, firstSlash);
        final String basePath = firstSlash < 0 ? "" : controlBaseUrl.substring(firstSlash);
        try
        {
            return new URI("https", host, basePath + path, null);
        } catch (final URISyntaxException e)
        {
            throw new SonosApiClientException("Invalid URI built", e);
        }
    }

    /**
     * Helper method to generate a basic GET request
     *
     * @param token for the user
     * @param path  for the API resource
     * @return a basic GET request
     * @throws SonosApiClientException if an error occurs building the request
     */
    HttpRequest getGetRequest(final String token, final String path) throws SonosApiClientException
    {
        return getStandardRequest(token, path).GET().build();
    }

    /**
     * Helper method to generate a basic DELETE request
     *
     * @param token for the user
     * @param path  for the API resource
     * @return a basic DELETE request
     * @throws SonosApiClientException if an error occurs building the request
     */
    HttpRequest getDeleteRequest(final String token, final String path) throws SonosApiClientException
    {
        return getStandardRequest(token, path).DELETE().build();
    }

    /**
     * Whether a request targets the OAuth token endpoint, whose response body carries access/refresh
     * tokens that must be kept out of logs.
     *
     * @param request the request being made
     * @return true if this is a token request
     */
    private static boolean isTokenResponse(final HttpRequest request)
    {
        final String path = request.uri().getPath();
        return path != null && path.contains("/oauth/access");
    }

    /**
     * Decode a response body as text for inclusion in an error message, truncated so an unexpectedly large
     * body (e.g. an HTML error page from an intermediary) cannot bloat the exception.
     *
     * @param bytes the raw response body
     * @return a short, human-readable snippet of the body
     */
    private static String bodySnippet(final byte[] bytes)
    {
        final String body = new String(bytes, StandardCharsets.UTF_8);
        final int maxLength = 512;
        return body.length() <= maxLength ? body : body.substring(0, maxLength) + "...(truncated)";
    }

    /**
     * A timeout only applies if it is present and strictly positive - {@link HttpRequest.Builder#timeout}
     * and {@link java.net.http.HttpClient.Builder#connectTimeout} both reject zero/negative durations, so a
     * non-positive configured value is treated as "no timeout" rather than an error.
     *
     * @param timeout the configured timeout, possibly null
     * @return true if the timeout should be applied
     */
    static boolean isPositive(final Duration timeout)
    {
        return timeout != null && !timeout.isZero() && !timeout.isNegative();
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
