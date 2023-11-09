package engineer.nightowl.sonos.api;

import java.util.Base64;
import java.util.Objects;

/**
 * Configuration class to be built up and passed into a
 * {@link engineer.nightowl.sonos.api.SonosApiClient}
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
    private Boolean clientSideValidationEnabled;

    public SonosApiConfiguration() {
        loadDefaults();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(final String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(final String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getAuthBaseUrl() {
        return authBaseUrl;
    }

    public void setAuthBaseUrl(final String authBaseUrl) {
        this.authBaseUrl = authBaseUrl;
    }

    public String getControlBaseUrl() {
        return controlBaseUrl;
    }

    public void setControlBaseUrl(final String controlBaseUrl) {
        this.controlBaseUrl = controlBaseUrl;
    }

    public Boolean isClientSideValidationEnabled() {
        return clientSideValidationEnabled;
    }

    public void setClientSideValidationEnabled(Boolean clientSideValidationEnabled) {
        this.clientSideValidationEnabled = clientSideValidationEnabled;
    }

    public void loadDefaults() {
        setAuthBaseUrl("api.sonos.com");
        setControlBaseUrl("api.ws.sonos.com/control/api");
        setClientSideValidationEnabled(Boolean.TRUE);
    }

    public String getAuthorizationHeaderValue() {
        final byte[] authBytes = String.join(":", getApiKey(), getApiSecret()).getBytes();
        final String authBase64 = Base64.getEncoder().encodeToString(authBytes);
        return String.join(" ", "Basic", authBase64);
    }

    @Override
    public String toString() {
        return "SonosApiConfiguration [apiKey=" + apiKey + ", apiSecret=" + apiSecret + ", applicationId="
                + applicationId + ", authBaseUrl=" + authBaseUrl + ", clientSideValidationEnabled="
                + clientSideValidationEnabled + ", controlBaseUrl=" + controlBaseUrl + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SonosApiConfiguration))
            return false;
        SonosApiConfiguration other = (SonosApiConfiguration) obj;
        return Objects.equals(apiKey, other.apiKey) && Objects.equals(apiSecret, other.apiSecret)
                && Objects.equals(applicationId, other.applicationId) && Objects.equals(authBaseUrl, other.authBaseUrl)
                && Objects.equals(clientSideValidationEnabled, other.clientSideValidationEnabled)
                && Objects.equals(controlBaseUrl, other.controlBaseUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, apiSecret, applicationId, authBaseUrl, clientSideValidationEnabled, controlBaseUrl);
    }
}
