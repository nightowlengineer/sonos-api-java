package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.BaseTestSetup;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

class AuthorizeResourceTest extends BaseTestSetup
{
    private final AuthorizeResource authorizeResource = new AuthorizeResource(apiClient);

    @Test
    public void testGetAuthorizeCodeUri() throws URISyntaxException
    {
        final URI uri = authorizeResource.getAuthorizeCodeUri("https://localhost");
        assertEquals("https", uri.getScheme());
    }

    @Test
    public void testGetAuthorizeCodeWithState() throws URISyntaxException
    {
        final String state = authorizeResource.generateStateValue();
        final URI uri = authorizeResource.getAuthorizeCodeUri("https://localhost", state);
        assertEquals("https", uri.getScheme());
        uri.getQuery();

        final List<NameValuePair> params = URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
        final HashMap<String, String> map = new HashMap<>();
        params.forEach(p -> map.put(p.getName(), p.getValue()));

        assertEquals(state, map.get("state"));
        assertEquals(configuration.getApiKey(), map.get("client_id"));
    }

}
