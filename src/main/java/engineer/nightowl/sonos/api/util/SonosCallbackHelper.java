package engineer.nightowl.sonos.api.util;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SonosCallbackHelper
{
    private static final Logger logger = LoggerFactory.getLogger(SonosCallbackHelper.class);

    /**
     * Verify that the message was signed by Sonos.
     *
     * @param headers   from the message
     * @param apiKey    of your integration
     * @param apiSecret of your integration
     * @return true if the message is cryptographically provable to be from Sonos
     * @throws SonosApiClientException if in an unsupported environment
     */
    public static Boolean verifySignature(final Map<String, String> headers, final String apiKey, final String apiSecret) throws SonosApiClientException
    {
        MessageDigest messageDigest = null;
        try
        {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e)
        {
            throw new SonosApiClientException("Unsupported execution environment", e);
        }

        messageDigest.update(requireHeader(headers, "X-Sonos-Event-Seq-Id").getBytes(UTF_8));
        messageDigest.update(requireHeader(headers, "X-Sonos-Namespace").getBytes(UTF_8));
        messageDigest.update(requireHeader(headers, "X-Sonos-Type").getBytes(UTF_8));
        messageDigest.update(requireHeader(headers, "X-Sonos-Target-Type").getBytes(UTF_8));
        messageDigest.update(requireHeader(headers, "X-Sonos-Target-Value").getBytes(UTF_8));
        messageDigest.update(apiKey.getBytes(UTF_8));
        messageDigest.update(apiSecret.getBytes(UTF_8));

        final String signature = Base64.getUrlEncoder().withoutPadding().encodeToString(messageDigest.digest());

        logger.debug("Verifying signature: {}", signature);

        return signature.equals(requireHeader(headers, "X-Sonos-Event-Signature"));
    }

    /**
     * Fetch a required header value, failing with a clear {@link SonosApiClientException} rather than an
     * undeclared {@link NullPointerException} if the caller's supplied header map is missing it - this
     * method processes external (webhook) input, so a missing/misnamed header should never surface as an
     * unchecked exception.
     *
     * @param headers    the header map to look up
     * @param headerName the header name to require
     * @return the header value
     * @throws SonosApiClientException if the header is absent
     */
    private static String requireHeader(final Map<String, String> headers, final String headerName) throws SonosApiClientException
    {
        final String value = headers.get(headerName);
        if (value == null)
        {
            throw new SonosApiClientException("Missing required header: " + headerName);
        }
        return value;
    }

    public static Boolean verifySignature(final Map<String, String> headers, final SonosApiClient apiClient) throws SonosApiClientException
    {
        return SonosCallbackHelper.verifySignature(headers, apiClient.getConfiguration().getApiKey(), apiClient.getConfiguration().getApiSecret());
    }

    public static Boolean verifySignature(final HttpMessage message, final SonosApiClient apiClient) throws SonosApiClientException
    {
        return SonosCallbackHelper.verifySignature(message, apiClient.getConfiguration().getApiKey(), apiClient.getConfiguration().getApiSecret());
    }

    public static Boolean verifySignature(final HttpMessage message, final String apiKey, final String apiSecret) throws SonosApiClientException
    {
        // Map of headers - Name, Value
        Map<String, String> headers = convertHeadersToMap(message.getAllHeaders());
        return SonosCallbackHelper.verifySignature(headers, apiKey, apiSecret);
    }

    public static Map<String, String> convertHeadersToMap(final Header[] headers)
    {
        // HTTP permits repeated header names (e.g. behind a proxy/load balancer) - keep the first value
        // seen rather than letting Collectors.toMap throw IllegalStateException on a duplicate key.
        return Arrays
                .stream(headers)
                .collect(Collectors.toMap(Header::getName, Header::getValue, (first, second) -> first));
    }
}
