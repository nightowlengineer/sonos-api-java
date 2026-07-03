package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.BaseTestSetup;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

class AuthorizeResourceTest extends BaseTestSetup
{
    private final AuthorizeResource authorizeResource = new AuthorizeResource(apiClient);

    @Test
    public void testGetAuthorizeCodeUri() throws SonosApiClientException
    {
        final URI uri = authorizeResource.getAuthorizeCodeUri("https://localhost");
        assertEquals("https", uri.getScheme());
    }

    @Test
    public void testGetAuthorizeCodeWithState() throws SonosApiClientException
    {
        final String state = authorizeResource.generateStateValue();
        final URI uri = authorizeResource.getAuthorizeCodeUri("https://localhost", state);
        assertEquals("https", uri.getScheme());

        final HashMap<String, String> map = new HashMap<>();
        for (final String pair : uri.getQuery().split("&"))
        {
            final String[] kv = pair.split("=", 2);
            map.put(URLDecoder.decode(kv[0], StandardCharsets.UTF_8),
                    kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : "");
        }

        assertEquals(state, map.get("state"));
        assertEquals(configuration.getApiKey(), map.get("client_id"));
    }

}
