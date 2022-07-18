package engineer.nightowl.sonos.api.util;

import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.http.HttpHeaders;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SonosCallbackHelperTest
{
    final String apiKey = "sonos";
    final String apiSecret = "secret";

    private HttpHeaders getHeaders()
    {
        final Map<String, List<String>> headers = new HashMap<>();
        headers.put("X-Sonos-Event-Seq-Id", List.of("event-seq-id"));
        headers.put("X-Sonos-Namespace", List.of("namespace"));
        headers.put("X-Sonos-Type", List.of("type"));
        headers.put("X-Sonos-Target-Type", List.of("target-type"));
        headers.put("X-Sonos-Target-Value", List.of("target-value"));
        headers.put("Unrelated-Header", List.of("sonos123"));
        headers.put("Content-Type", List.of("application/json"));

        return HttpHeaders.of(headers, new HeaderFilter());
    }

    private HttpHeaders getValidHeaders()
    {
        final HttpHeaders headers = getHeaders();
        final Map<String, List<String>> copiedHeaders = new HashMap<>(headers.map());
        copiedHeaders.put("X-Sonos-Event-Signature", List.of("n9V0MCdGYaOPy_dJb_nUPqw5dHiBSd_g0NOQTe4IaVI"));
        return HttpHeaders.of(copiedHeaders, new HeaderFilter());
    }

    private HttpHeaders getInvalidHeaders()
    {
        final HttpHeaders headers = getHeaders();
        final Map<String, List<String>> copiedHeaders = new HashMap<>(headers.map());
        copiedHeaders.put("X-Sonos-Event-Signature", List.of("invalidSignature"));
        return HttpHeaders.of(copiedHeaders, new HeaderFilter());
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