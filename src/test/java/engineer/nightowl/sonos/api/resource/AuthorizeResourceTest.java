package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.BaseTest;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class AuthorizeResourceTest extends BaseTest
{
    private final AuthorizeResource authorizeResource = new AuthorizeResource(apiClient);

    @Test
    public void testGetAuthorizeCodeUri() throws URISyntaxException
    {
        final URI uri = authorizeResource.getAuthorizeCodeUri("https://localhost");
        Assert.assertEquals("https", uri.getScheme());
    }

    @Test
    public void testGetAuthorizeCodeWithState() throws URISyntaxException
    {
        final String state = authorizeResource.generateStateValue();
        final URI uri = authorizeResource.getAuthorizeCodeUri("https://localhost", state);
        Assert.assertEquals("https", uri.getScheme());
        uri.getQuery();

        final List<NameValuePair> params = URLEncodedUtils.parse(uri, StandardCharsets.UTF_8.name());
        final HashMap<String, String> map = new HashMap<>();
        params.forEach(p -> map.put(p.getName(), p.getValue()));

        Assert.assertEquals(state, map.get("state"));
        Assert.assertEquals(configuration.getApiKey(), map.get("client_id"));
    }

}
