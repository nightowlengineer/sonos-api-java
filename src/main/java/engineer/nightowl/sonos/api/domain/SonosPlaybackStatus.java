package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosPlaybackState;

/**
 * <p>SonosPlaybackStatus class.</p>
 */
public class SonosPlaybackStatus
{
    private SonosPlaybackPolicy availablePlaybackActions;
    private String itemId;
    private SonosPlaybackState playbackState;
    private SonosPlayMode playModes;
    private Integer positionMillis;
    private String previousItemId;
    private Integer previousPositionMillis;
    private String queueVersion;

    /**
     * <p>Constructor for SonosPlaybackStatus.</p>
     */
    public SonosPlaybackStatus()
    {
    }

    /**
     * <p>Constructor for SonosPlaybackStatus.</p>
     *
     * @param availablePlaybackActions a {@link engineer.nightowl.sonos.api.domain.SonosPlaybackPolicy} object.
     * @param itemId a {@link java.lang.String} object.
     * @param playbackState a {@link engineer.nightowl.sonos.api.enums.SonosPlaybackState} object.
     * @param playModes a {@link engineer.nightowl.sonos.api.domain.SonosPlayMode} object.
     * @param positionMillis a {@link java.lang.Integer} object.
     * @param previousItemId a {@link java.lang.String} object.
     * @param previousPositionMillis a {@link java.lang.Integer} object.
     * @param queueVersion a {@link java.lang.String} object.
     */
    public SonosPlaybackStatus(final SonosPlaybackPolicy availablePlaybackActions, final String itemId, final SonosPlaybackState playbackState, final SonosPlayMode playModes, final Integer positionMillis, final String previousItemId, final Integer previousPositionMillis, final String queueVersion)
    {
        this.availablePlaybackActions = availablePlaybackActions;
        this.itemId = itemId;
        this.playbackState = playbackState;
        this.playModes = playModes;
        this.positionMillis = positionMillis;
        this.previousItemId = previousItemId;
        this.previousPositionMillis = previousPositionMillis;
        this.queueVersion = queueVersion;
    }

    /**
     * <p>Getter for the field <code>availablePlaybackActions</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosPlaybackPolicy} object.
     */
    public SonosPlaybackPolicy getAvailablePlaybackActions()
    {
        return availablePlaybackActions;
    }

    /**
     * <p>Setter for the field <code>availablePlaybackActions</code>.</p>
     *
     * @param availablePlaybackActions a {@link engineer.nightowl.sonos.api.domain.SonosPlaybackPolicy} object.
     */
    public void setAvailablePlaybackActions(final SonosPlaybackPolicy availablePlaybackActions)
    {
        this.availablePlaybackActions = availablePlaybackActions;
    }

    /**
     * <p>Getter for the field <code>itemId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getItemId()
    {
        return itemId;
    }

    /**
     * <p>Setter for the field <code>itemId</code>.</p>
     *
     * @param itemId a {@link java.lang.String} object.
     */
    public void setItemId(final String itemId)
    {
        this.itemId = itemId;
    }

    /**
     * <p>Getter for the field <code>playbackState</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.enums.SonosPlaybackState} object.
     */
    public SonosPlaybackState getPlaybackState()
    {
        return playbackState;
    }

    /**
     * <p>Setter for the field <code>playbackState</code>.</p>
     *
     * @param playbackState a {@link engineer.nightowl.sonos.api.enums.SonosPlaybackState} object.
     */
    public void setPlaybackState(final SonosPlaybackState playbackState)
    {
        this.playbackState = playbackState;
    }

    /**
     * <p>Getter for the field <code>playModes</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosPlayMode} object.
     */
    public SonosPlayMode getPlayModes()
    {
        return playModes;
    }

    /**
     * <p>Setter for the field <code>playModes</code>.</p>
     *
     * @param playModes a {@link engineer.nightowl.sonos.api.domain.SonosPlayMode} object.
     */
    public void setPlayModes(final SonosPlayMode playModes)
    {
        this.playModes = playModes;
    }

    /**
     * <p>Getter for the field <code>positionMillis</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getPositionMillis()
    {
        return positionMillis;
    }

    /**
     * <p>Setter for the field <code>positionMillis</code>.</p>
     *
     * @param positionMillis a {@link java.lang.Integer} object.
     */
    public void setPositionMillis(final Integer positionMillis)
    {
        this.positionMillis = positionMillis;
    }

    /**
     * <p>Getter for the field <code>previousItemId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPreviousItemId()
    {
        return previousItemId;
    }

    /**
     * <p>Setter for the field <code>previousItemId</code>.</p>
     *
     * @param previousItemId a {@link java.lang.String} object.
     */
    public void setPreviousItemId(final String previousItemId)
    {
        this.previousItemId = previousItemId;
    }

    /**
     * <p>Getter for the field <code>previousPositionMillis</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getPreviousPositionMillis()
    {
        return previousPositionMillis;
    }

    /**
     * <p>Setter for the field <code>previousPositionMillis</code>.</p>
     *
     * @param previousPositionMillis a {@link java.lang.Integer} object.
     */
    public void setPreviousPositionMillis(final Integer previousPositionMillis)
    {
        this.previousPositionMillis = previousPositionMillis;
    }

    /**
     * <p>Getter for the field <code>queueVersion</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getQueueVersion()
    {
        return queueVersion;
    }

    /**
     * <p>Setter for the field <code>queueVersion</code>.</p>
     *
     * @param queueVersion a {@link java.lang.String} object.
     */
    public void setQueueVersion(final String queueVersion)
    {
        this.queueVersion = queueVersion;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SonosPlaybackStatus{" +
                "availablePlaybackActions=" + availablePlaybackActions +
                ", itemId='" + itemId + '\'' +
                ", playbackState=" + playbackState +
                ", playModes=" + playModes +
                ", positionMillis=" + positionMillis +
                ", previousItemId='" + previousItemId + '\'' +
                ", previousPositionMillis=" + previousPositionMillis +
                ", queueVersion='" + queueVersion + '\'' +
                '}';
    }
}
