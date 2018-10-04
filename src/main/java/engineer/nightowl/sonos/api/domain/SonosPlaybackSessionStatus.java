package engineer.nightowl.sonos.api.domain;

/**
 * <p>SonosPlaybackSessionStatus class.</p>
 */
public class SonosPlaybackSessionStatus
{
    // Should be an enum, but only has one value, and may be deprecated in the future
    private String sessionState;
    private String sessionId;
    private Boolean sessionCreated;
    private String customData;

    /**
     * <p>Constructor for SonosPlaybackSessionStatus.</p>
     */
    public SonosPlaybackSessionStatus()
    {
    }

    /**
     * <p>Constructor for SonosPlaybackSessionStatus.</p>
     *
     * @param sessionState a {@link java.lang.String} object.
     * @param sessionId a {@link java.lang.String} object.
     * @param sessionCreated a {@link java.lang.Boolean} object.
     * @param customData a {@link java.lang.String} object.
     */
    public SonosPlaybackSessionStatus(final String sessionState, final String sessionId, final Boolean sessionCreated, final String customData)
    {
        this.sessionState = sessionState;
        this.sessionId = sessionId;
        this.sessionCreated = sessionCreated;
        this.customData = customData;
    }

    /**
     * <p>Getter for the field <code>sessionState</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSessionState()
    {
        return sessionState;
    }

    /**
     * <p>Setter for the field <code>sessionState</code>.</p>
     *
     * @param sessionState a {@link java.lang.String} object.
     */
    public void setSessionState(final String sessionState)
    {
        this.sessionState = sessionState;
    }

    /**
     * <p>Getter for the field <code>sessionId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSessionId()
    {
        return sessionId;
    }

    /**
     * <p>Setter for the field <code>sessionId</code>.</p>
     *
     * @param sessionId a {@link java.lang.String} object.
     */
    public void setSessionId(final String sessionId)
    {
        this.sessionId = sessionId;
    }

    /**
     * <p>Getter for the field <code>sessionCreated</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getSessionCreated()
    {
        return sessionCreated;
    }

    /**
     * <p>Setter for the field <code>sessionCreated</code>.</p>
     *
     * @param sessionCreated a {@link java.lang.Boolean} object.
     */
    public void setSessionCreated(final Boolean sessionCreated)
    {
        this.sessionCreated = sessionCreated;
    }

    /**
     * <p>Getter for the field <code>customData</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomData()
    {
        return customData;
    }

    /**
     * <p>Setter for the field <code>customData</code>.</p>
     *
     * @param customData a {@link java.lang.String} object.
     */
    public void setCustomData(final String customData)
    {
        this.customData = customData;
    }

    /** {@inheritDoc} */
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
