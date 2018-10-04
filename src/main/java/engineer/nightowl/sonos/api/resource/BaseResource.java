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
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

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
    private static final String SONOS_TYPE_HEADER = "X-Sonos-Type";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    SonosApiClient apiClient;

    BaseResource(final SonosApiClient apiClient)
    {
        this.apiClient = apiClient;
    }

    /**
     * Main API call method. Takes in a {@link HttpUriRequest} comprising of a
     * URI and method
     *
     * @param request with the relevant URI and method (with associated data as
     *                appropriate)
     * @param type    what the response should interpreted as
     * @return the type specified in 'type'
     * @throws SonosApiClientException if unable to build or execute the request
     * @throws SonosApiError           if the Sonos API returns an error to an otherwise successful request
     */
    <T> T callApi(final HttpUriRequest request, final Class<T> type) throws SonosApiClientException, SonosApiError
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Sending request to " + request.getURI());
        }
        final CloseableHttpResponse response;
        try
        {
            response = apiClient.getHttpClient().execute(request);
        } catch (final IOException e)
        {
            throw new SonosApiClientException("Error interrogating Sonos API", e);
        }
        if (401 == response.getStatusLine().getStatusCode())
        {
            throw new SonosApiClientException("Invalid token");
        }
        final byte[] bytes;
        try (final InputStream stream = response.getEntity().getContent())
        {
            bytes = IOUtils.toByteArray(stream);
        } catch (final IOException ioe)
        {
            throw new SonosApiClientException("Unable to convert response body", ioe);
        }

        if (logger.isDebugEnabled())
        {
            final String debugMsg = String.format("Raw response from API: %s", new String(bytes));
            logger.debug(debugMsg);
        }

        // Get type from Sonos response - not always possible
        final SonosType sonosDeclaredClass = getTypeFromHeader(response);
        final String sonosDeclaredClassName = sonosDeclaredClass == null ? null : sonosDeclaredClass.getClazz().getSimpleName();

        // If Sonos didn't provide a type, or if one was provided and it matches, proceed
        if (sonosDeclaredClassName == null || sonosDeclaredClassName.equals(type.getSimpleName()))
        {
            try
            {
                return type.cast(OM.readValue(bytes, Class.forName(type.getName())));
            } catch (final IOException | ClassNotFoundException e)
            {
                final String msg = String.format("Unexpected error converting response to %s (Sonos declared %s)", type.getSimpleName(), sonosDeclaredClassName);
                throw new SonosApiClientException(msg, e);
            }
        }
        // Otherwise it's likely an error object, in which case throw an exception with the mapped object
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
     * Obtain the declared object type given by Sonos in the response header
     *
     * @param response - raw response to fetch the header from
     * @return the {@link SonosType} declared, or null if not found
     */
    private SonosType getTypeFromHeader(final CloseableHttpResponse response)
    {
        if (response != null)
        {
            final Header header = response.getFirstHeader(SONOS_TYPE_HEADER);
            if (header != null && !SonosUtilityHelper.isEmpty(header.getValue()))
            {
                if (!"none".equalsIgnoreCase(header.getValue()))
                {
                    return SonosType.valueOf(header.getValue());
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
        final HttpGet request = getGetRequest(token, path);
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
        final HttpDelete request = getDeleteRequest(token, path);
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
        return postToApi(returnType, token, path, null);
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
        final HttpPost request = getPostRequest(token, path);
        // If the content for the request has the ability to be validated, do so.
        // If the object is invalid, there's no point sending it to the API to be rejected.
        if (content instanceof Validatable)
        {
            ((Validatable) content).validate();
        }
        if (!SonosUtilityHelper.isEmpty(content))
        {
            final String json;
            final StringEntity requestContent;
            try
            {
                json = OM.writeValueAsString(content);
                requestContent = new StringEntity(json);
            } catch (final JsonProcessingException | UnsupportedEncodingException e)
            {
                throw new SonosApiClientException("Unable to convert POST request parameters", e);
            }
            request.setEntity(requestContent);
        }
        return callApi(request, returnType);
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
    private <T extends HttpRequestBase> T getStandardRequest(final Class<T> requestType, final String token,
                                                             final String path) throws SonosApiClientException
    {
        final T request;
        try
        {
            request = requestType.newInstance();
        } catch (final Exception e)
        {
            throw new SonosApiClientException("Unable to create class " + requestType.getSimpleName(), e);
        }

        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost(configuration.getControlBaseUrl());
        uri.setPath(path);

        request.setHeader("Authorization", String.format("Bearer %s", token));

        try
        {
            request.setURI(uri.build());
        } catch (final URISyntaxException e)
        {
            throw new SonosApiClientException("Invalid URI built", e);
        }

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
    private HttpGet getGetRequest(final String token, final String path) throws SonosApiClientException
    {
        return getStandardRequest(HttpGet.class, token, path);
    }

    /**
     * Helper method to generate a basic GET request
     *
     * @param token for the user
     * @param path  for the API resource
     * @return a basic DELETE request
     * @throws SonosApiClientException if an error occurs building the request
     */
    private HttpDelete getDeleteRequest(final String token, final String path) throws SonosApiClientException
    {
        return getStandardRequest(HttpDelete.class, token, path);
    }

    /**
     * Helper method to generate a basic POST request
     *
     * @param token for the user
     * @param path  for the API resource
     * @return a basic POST request
     * @throws SonosApiClientException if an error occurs building the request
     */
    private HttpPost getPostRequest(final String token, final String path) throws SonosApiClientException
    {
        return getStandardRequest(HttpPost.class, token, path);
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
