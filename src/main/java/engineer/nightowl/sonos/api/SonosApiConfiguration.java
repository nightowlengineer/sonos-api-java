package engineer.nightowl.sonos.api;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * Configuration class to be built up and passed into a {@link engineer.nightowl.sonos.api.SonosApiClient}
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

    /**
     * <p>Constructor for SonosApiConfiguration.</p>
     */
    public SonosApiConfiguration()
    {
        loadDefaults();
    }

    /**
     * <p>Getter for the field <code>applicationId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getApplicationId()
    {
        return applicationId;
    }

    /**
     * <p>Setter for the field <code>applicationId</code>.</p>
     *
     * @param applicationId a {@link java.lang.String} object.
     */
    public void setApplicationId(final String applicationId)
    {
        this.applicationId = applicationId;
    }

    /**
     * <p>Getter for the field <code>apiKey</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getApiKey()
    {
        return apiKey;
    }

    /**
     * <p>Setter for the field <code>apiKey</code>.</p>
     *
     * @param apiKey a {@link java.lang.String} object.
     */
    public void setApiKey(final String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getApiSecret()
    {
        return apiSecret;
    }

    /**
     * <p>Setter for the field <code>apiSecret</code>.</p>
     *
     * @param apiSecret a {@link java.lang.String} object.
     */
    public void setApiSecret(final String apiSecret)
    {
        this.apiSecret = apiSecret;
    }

    /**
     * <p>Getter for the field <code>authBaseUrl</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAuthBaseUrl()
    {
        return authBaseUrl;
    }

    public void setAuthBaseUrl(final String authBaseUrl)
    {
        this.authBaseUrl = authBaseUrl;
    }

    /**
     * <p>Getter for the field <code>controlBaseUrl</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getControlBaseUrl()
    {
        return controlBaseUrl;
    }

    public void setControlBaseUrl(final String controlBaseUrl)
    {
        this.controlBaseUrl = controlBaseUrl;
    }

    public Boolean isClientSideValidationEnabled()
    {
        return clientSideValidationEnabled;
    }

    public void setClientSideValidationEnabled(Boolean clientSideValidationEnabled)
    {
        this.clientSideValidationEnabled = clientSideValidationEnabled;
    }


    public void loadDefaults()
    {
        setAuthBaseUrl("api.sonos.com");
        setControlBaseUrl("api.ws.sonos.com/control/api");
        setClientSideValidationEnabled(Boolean.TRUE);
    }

    /**
     * <p>getAuthorizationHeader.</p>
     *
     * @return a {@link org.apache.http.Header} object.
     */
    public Header getAuthorizationHeader()
    {
        final byte[] authBytes = String.join(":", getApiKey(), getApiSecret()).getBytes();
        final String authBase64 = Base64.encodeBase64String(authBytes);
        final String headerValue = String.join(" ", "Basic", authBase64);
        return new BasicHeader("Authorization", headerValue);
    }

    @Override
    public String toString()
    {
        return "SonosApiConfiguration{" +
                "applicationId='" + applicationId + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", apiSecret='" + apiSecret + '\'' +
                ", authBaseUrl='" + authBaseUrl + '\'' +
                ", controlBaseUrl='" + controlBaseUrl + '\'' +
                ", clientSideValidationEnabled=" + clientSideValidationEnabled +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SonosApiConfiguration that = (SonosApiConfiguration) o;

        return new EqualsBuilder()
                .append(applicationId, that.applicationId)
                .append(apiKey, that.apiKey)
                .append(apiSecret, that.apiSecret)
                .append(authBaseUrl, that.authBaseUrl)
                .append(controlBaseUrl, that.controlBaseUrl)
                .append(clientSideValidationEnabled, that.clientSideValidationEnabled)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(applicationId)
                .append(apiKey)
                .append(apiSecret)
                .append(authBaseUrl)
                .append(controlBaseUrl)
                .append(clientSideValidationEnabled)
                .toHashCode();
    }
}
