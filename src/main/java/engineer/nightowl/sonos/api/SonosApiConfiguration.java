package engineer.nightowl.sonos.api;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * Configuration class to be built up and passed into a {@link SonosApiClient}
 * <p>
 * Loads defaults on construction.
 */
public class SonosApiConfiguration
{

    private String applicationId;
    private String apiKey;
    private String apiSecret;
    private String authBaseUrl;
    private String controlBaseUrl;

    public SonosApiConfiguration()
    {
        loadDefaults();
    }

    public String getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId(final String applicationId)
    {
        this.applicationId = applicationId;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(final String apiKey)
    {
        this.apiKey = apiKey;
    }

    private String getApiSecret()
    {
        return apiSecret;
    }

    public void setApiSecret(final String apiSecret)
    {
        this.apiSecret = apiSecret;
    }

    public String getAuthBaseUrl()
    {
        return authBaseUrl;
    }

    private void setAuthBaseUrl(final String authBaseUrl)
    {
        this.authBaseUrl = authBaseUrl;
    }

    public String getControlBaseUrl()
    {
        return controlBaseUrl;
    }

    private void setControlBaseUrl(final String controlBaseUrl)
    {
        this.controlBaseUrl = controlBaseUrl;
    }

    private void loadDefaults()
    {
        setAuthBaseUrl("api.sonos.com");
        setControlBaseUrl("api.ws.sonos.com/control/api");
    }

    public Header getAuthorizationHeader()
    {
        final byte[] authBytes = String.join(":", getApiKey(), getApiSecret()).getBytes();
        final String authBase64 = Base64.encodeBase64String(authBytes);
        final String headerValue = String.join(" ", "Basic", authBase64);
        return new BasicHeader("Authorization", headerValue);
    }
}
