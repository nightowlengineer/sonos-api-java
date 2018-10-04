package engineer.nightowl.sonos.api.domain;

public class SonosPlaybackSessionStatus
{
    // Should be an enum, but only has one value, and may be deprecated in the future
    private String sessionState;
    private String sessionId;
    private Boolean sessionCreated;
    private String customData;

    public SonosPlaybackSessionStatus()
    {
    }

    public SonosPlaybackSessionStatus(final String sessionState, final String sessionId, final Boolean sessionCreated, final String customData)
    {
        this.sessionState = sessionState;
        this.sessionId = sessionId;
        this.sessionCreated = sessionCreated;
        this.customData = customData;
    }

    public String getSessionState()
    {
        return sessionState;
    }

    public void setSessionState(final String sessionState)
    {
        this.sessionState = sessionState;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(final String sessionId)
    {
        this.sessionId = sessionId;
    }

    public Boolean getSessionCreated()
    {
        return sessionCreated;
    }

    public void setSessionCreated(final Boolean sessionCreated)
    {
        this.sessionCreated = sessionCreated;
    }

    public String getCustomData()
    {
        return customData;
    }

    public void setCustomData(final String customData)
    {
        this.customData = customData;
    }

    @Override
    public String toString()
    {
        return "SonosPlaybackSessionStatus{" +
                "sessionState='" + sessionState + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", sessionCreated=" + sessionCreated +
                ", customData='" + customData + '\'' +
                '}';
    }
}
