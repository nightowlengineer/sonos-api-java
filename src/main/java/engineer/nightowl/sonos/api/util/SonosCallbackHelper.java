package engineer.nightowl.sonos.api.util;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;

public class SonosCallbackHelper
{
    private static final Logger logger = LoggerFactory.getLogger(SonosCallbackHelper.class);

    private SonosCallbackHelper()
    {
        // Empty private constructor
    }

    /**
     * Verify that the message was signed by Sonos.
     *
     * @param headers   from the message
     * @param apiKey    of your integration
     * @param apiSecret of your integration
     * @return true if the message is cryptographically provable to be from Sonos
     * @throws SonosApiClientException if in an unsupported environment
     */
    public static Boolean verifySignature(final HttpHeaders headers, final String apiKey, final String apiSecret) throws SonosApiClientException
    {
        final Optional<String> sentSignature = headers.firstValue("X-Sonos-Event-Signature");
        if (!sentSignature.isPresent())
        {
            throw new SonosApiClientException("No signature present in header, ending early.");
        }

        MessageDigest messageDigest = null;
        try
        {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e)
        {
            throw new SonosApiClientException("Unsupported execution environment", e);
        }

        if (headers.map().keySet().containsAll(SignatureHeader.getAllHeaders()))
        {
            for (SignatureHeader key : SignatureHeader.values())
            {
                final Optional<String> value = headers.firstValue(key.getHeaderKey());
                if (value.isPresent())
                {
                    messageDigest.update(value.get().getBytes(UTF_8));
                }
            }
        }

        messageDigest.update(apiKey.getBytes(UTF_8));
        messageDigest.update(apiSecret.getBytes(UTF_8));        

        final String signature = Base64.getUrlEncoder().withoutPadding().encodeToString(messageDigest.digest());

        logger.debug("Verifying signature: {}", signature);

        return signature.equals(sentSignature.get());
    }

    public static Boolean verifySignature(final HttpResponse<Object> response, final SonosApiClient apiClient) throws SonosApiClientException
    {
        return SonosCallbackHelper.verifySignature(response.headers(), apiClient.getConfiguration().getApiKey(), apiClient.getConfiguration().getApiSecret());
    }

    public static Boolean verifySignature(final HttpResponse<Object> response, final String apiKey, final String apiSecret) throws SonosApiClientException
    {
        return SonosCallbackHelper.verifySignature(response.headers(), apiKey, apiSecret);
    }
}
