package engineer.nightowl.sonos.api.util;

import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

class SonosCallbackHelperTest
{
    final String apiKey = "sonos";
    final String apiSecret = "secret";

    private Map<String, String> getHeaders()
    {
        final Map<String, String> headers = new HashMap<>();
        headers.put("X-Sonos-Event-Seq-Id", "event-seq-id");
        headers.put("X-Sonos-Namespace", "namespace");
        headers.put("X-Sonos-Type", "type");
        headers.put("X-Sonos-Target-Type", "target-type");
        headers.put("X-Sonos-Target-Value", "target-value");
        headers.put("Unrelated-Header", "sonos123");
        headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());

        return headers;
    }

    private Map<String, String> getValidHeaders()
    {
        final Map<String, String> headers = getHeaders();
        headers.put("X-Sonos-Event-Signature", "n9V0MCdGYaOPy_dJb_nUPqw5dHiBSd_g0NOQTe4IaVI");
        return headers;
    }

    private Map<String, String> getInvalidHeaders()
    {
        final Map<String, String> headers = getHeaders();
        headers.put("X-Sonos-Event-Signature", "invalidSignature");
        return headers;
    }

    @Test
    void testVerifySignatureWithValidHeaders() throws SonosApiClientException, NoSuchAlgorithmException
    {
        assertTrue(SonosCallbackHelper.verifySignature(getValidHeaders(), apiKey, apiSecret));
    }

    @Test
    void testVerifySignatureWithInvalidHeaders() throws SonosApiClientException, NoSuchAlgorithmException
    {
        assertFalse(SonosCallbackHelper.verifySignature(getInvalidHeaders(), apiKey, apiSecret));
    }
}