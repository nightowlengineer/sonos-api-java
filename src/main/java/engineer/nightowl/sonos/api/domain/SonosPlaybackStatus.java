package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosPlaybackState;

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

    public SonosPlaybackStatus()
    {
    }

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

    public SonosPlaybackPolicy getAvailablePlaybackActions()
    {
        return availablePlaybackActions;
    }

    public void setAvailablePlaybackActions(final SonosPlaybackPolicy availablePlaybackActions)
    {
        this.availablePlaybackActions = availablePlaybackActions;
    }

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(final String itemId)
    {
        this.itemId = itemId;
    }

    public SonosPlaybackState getPlaybackState()
    {
        return playbackState;
    }

    public void setPlaybackState(final SonosPlaybackState playbackState)
    {
        this.playbackState = playbackState;
    }

    public SonosPlayMode getPlayModes()
    {
        return playModes;
    }

    public void setPlayModes(final SonosPlayMode playModes)
    {
        this.playModes = playModes;
    }

    public Integer getPositionMillis()
    {
        return positionMillis;
    }

    public void setPositionMillis(final Integer positionMillis)
    {
        this.positionMillis = positionMillis;
    }

    public String getPreviousItemId()
    {
        return previousItemId;
    }

    public void setPreviousItemId(final String previousItemId)
    {
        this.previousItemId = previousItemId;
    }

    public Integer getPreviousPositionMillis()
    {
        return previousPositionMillis;
    }

    public void setPreviousPositionMillis(final Integer previousPositionMillis)
    {
        this.previousPositionMillis = previousPositionMillis;
    }

    public String getQueueVersion()
    {
        return queueVersion;
    }

    public void setQueueVersion(final String queueVersion)
    {
        this.queueVersion = queueVersion;
    }

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
