package engineer.nightowl.sonos.api.domain;

import java.net.URI;

public class SonosCloudQueueRequest
{
    private String httpAuthorization;
    private String itemId;
    private Boolean playOnCompletion;
    private Integer positionMillis;
    private URI queueBaseUrl;
    private String queueVersion;
    private SonosTrack trackMetadata;
    private Boolean useHttpAuthorizationForMedia;

    public SonosCloudQueueRequest()
    {
    }

    public SonosCloudQueueRequest(final String httpAuthorization, final String itemId, final Boolean playOnCompletion, final Integer positionMillis, final URI queueBaseUrl, final String queueVersion, final SonosTrack trackMetadata, final Boolean useHttpAuthorizationForMedia)
    {
        this.httpAuthorization = httpAuthorization;
        this.itemId = itemId;
        this.playOnCompletion = playOnCompletion;
        this.positionMillis = positionMillis;
        this.queueBaseUrl = queueBaseUrl;
        this.queueVersion = queueVersion;
        this.trackMetadata = trackMetadata;
        this.useHttpAuthorizationForMedia = useHttpAuthorizationForMedia;
    }

    public String getHttpAuthorization()
    {
        return httpAuthorization;
    }

    public void setHttpAuthorization(final String httpAuthorization)
    {
        this.httpAuthorization = httpAuthorization;
    }

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(final String itemId)
    {
        this.itemId = itemId;
    }

    public Boolean getPlayOnCompletion()
    {
        return playOnCompletion;
    }

    public void setPlayOnCompletion(final Boolean playOnCompletion)
    {
        this.playOnCompletion = playOnCompletion;
    }

    public Integer getPositionMillis()
    {
        return positionMillis;
    }

    public void setPositionMillis(final Integer positionMillis)
    {
        this.positionMillis = positionMillis;
    }

    public URI getQueueBaseUrl()
    {
        return queueBaseUrl;
    }

    public void setQueueBaseUrl(final URI queueBaseUrl)
    {
        this.queueBaseUrl = queueBaseUrl;
    }

    public String getQueueVersion()
    {
        return queueVersion;
    }

    public void setQueueVersion(final String queueVersion)
    {
        this.queueVersion = queueVersion;
    }

    public SonosTrack getTrackMetadata()
    {
        return trackMetadata;
    }

    public void setTrackMetadata(final SonosTrack trackMetadata)
    {
        this.trackMetadata = trackMetadata;
    }

    public Boolean getUseHttpAuthorizationForMedia()
    {
        return useHttpAuthorizationForMedia;
    }

    public void setUseHttpAuthorizationForMedia(final Boolean useHttpAuthorizationForMedia)
    {
        this.useHttpAuthorizationForMedia = useHttpAuthorizationForMedia;
    }

    @Override
    public String toString()
    {
        return "SonosCloudQueueRequest{" +
                "httpAuthorization='" + httpAuthorization + '\'' +
                ", itemId='" + itemId + '\'' +
                ", playOnCompletion=" + playOnCompletion +
                ", positionMillis=" + positionMillis +
                ", queueBaseUrl=" + queueBaseUrl +
                ", queueVersion='" + queueVersion + '\'' +
                ", trackMetadata=" + trackMetadata +
                ", useHttpAuthorizationForMedia=" + useHttpAuthorizationForMedia +
                '}';
    }
}
