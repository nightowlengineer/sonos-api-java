package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>SonosToken class.</p>
 */
public class SonosToken
{

    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Integer expiresIn;
    private String scope;

    /**
     * <p>Constructor for SonosToken.</p>
     */
    public SonosToken()
    {
    }

    /**
     * <p>Constructor for SonosToken.</p>
     *
     * @param accessToken a {@link java.lang.String} object.
     * @param tokenType a {@link java.lang.String} object.
     * @param refreshToken a {@link java.lang.String} object.
     * @param expiresIn a {@link java.lang.Integer} object.
     * @param scope a {@link java.lang.String} object.
     */
    public SonosToken(
            @JsonProperty("access_token") final String accessToken,
            @JsonProperty("token_type") final String tokenType,
            @JsonProperty("refresh_token") final String refreshToken,
            @JsonProperty("expires_in") final Integer expiresIn,
            @JsonProperty("scope") final String scope)
    {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.scope = scope;
    }

    /**
     * <p>Getter for the field <code>accessToken</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAccessToken()
    {
        return accessToken;
    }

    /**
     * <p>Setter for the field <code>accessToken</code>.</p>
     *
     * @param accessToken a {@link java.lang.String} object.
     */
    public void setAccessToken(final String accessToken)
    {
        this.accessToken = accessToken;
    }

    /**
     * <p>Getter for the field <code>tokenType</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTokenType()
    {
        return tokenType;
    }

    /**
     * <p>Setter for the field <code>tokenType</code>.</p>
     *
     * @param tokenType a {@link java.lang.String} object.
     */
    public void setTokenType(final String tokenType)
    {
        this.tokenType = tokenType;
    }

    /**
     * <p>Getter for the field <code>refreshToken</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }

    /**
     * <p>Setter for the field <code>refreshToken</code>.</p>
     *
     * @param refreshToken a {@link java.lang.String} object.
     */
    public void setRefreshToken(final String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    /**
     * <p>Getter for the field <code>expiresIn</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getExpiresIn()
    {
        return expiresIn;
    }

    /**
     * <p>Setter for the field <code>expiresIn</code>.</p>
     *
     * @param expiresIn a {@link java.lang.Integer} object.
     */
    public void setExpiresIn(final Integer expiresIn)
    {
        this.expiresIn = expiresIn;
    }

    /**
     * <p>Getter for the field <code>scope</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * <p>Setter for the field <code>scope</code>.</p>
     *
     * @param scope a {@link java.lang.String} object.
     */
    public void setScope(final String scope)
    {
        this.scope = scope;
    }
}
