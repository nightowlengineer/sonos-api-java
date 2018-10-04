package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SonosToken
{

    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Integer expiresIn;
    private String scope;

    public SonosToken()
    {
    }

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

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(final String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getTokenType()
    {
        return tokenType;
    }

    public void setTokenType(final String tokenType)
    {
        this.tokenType = tokenType;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    public Integer getExpiresIn()
    {
        return expiresIn;
    }

    public void setExpiresIn(final Integer expiresIn)
    {
        this.expiresIn = expiresIn;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(final String scope)
    {
        this.scope = scope;
    }
}
