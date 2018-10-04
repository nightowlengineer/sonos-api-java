package engineer.nightowl.sonos.api.domain;

import java.net.URI;

/**
 * <p>SonosCloudQueueRequest class.</p>
 */
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

    /**
     * <p>Constructor for SonosCloudQueueRequest.</p>
     */
    public SonosCloudQueueRequest()
    {
    }

    /**
     * <p>Constructor for SonosCloudQueueRequest.</p>
     *
     * @param httpAuthorization a {@link java.lang.String} object.
     * @param itemId a {@link java.lang.String} object.
     * @param playOnCompletion a {@link java.lang.Boolean} object.
     * @param positionMillis a {@link java.lang.Integer} object.
     * @param queueBaseUrl a {@link java.net.URI} object.
     * @param queueVersion a {@link java.lang.String} object.
     * @param trackMetadata a {@link engineer.nightowl.sonos.api.domain.SonosTrack} object.
     * @param useHttpAuthorizationForMedia a {@link java.lang.Boolean} object.
     */
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

    /**
     * <p>Getter for the field <code>httpAuthorization</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getHttpAuthorization()
    {
        return httpAuthorization;
    }

    /**
     * <p>Setter for the field <code>httpAuthorization</code>.</p>
     *
     * @param httpAuthorization a {@link java.lang.String} object.
     */
    public void setHttpAuthorization(final String httpAuthorization)
    {
        this.httpAuthorization = httpAuthorization;
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
     * <p>Getter for the field <code>playOnCompletion</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getPlayOnCompletion()
    {
        return playOnCompletion;
    }

    /**
     * <p>Setter for the field <code>playOnCompletion</code>.</p>
     *
     * @param playOnCompletion a {@link java.lang.Boolean} object.
     */
    public void setPlayOnCompletion(final Boolean playOnCompletion)
    {
        this.playOnCompletion = playOnCompletion;
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
     * <p>Getter for the field <code>queueBaseUrl</code>.</p>
     *
     * @return a {@link java.net.URI} object.
     */
    public URI getQueueBaseUrl()
    {
        return queueBaseUrl;
    }

    /**
     * <p>Setter for the field <code>queueBaseUrl</code>.</p>
     *
     * @param queueBaseUrl a {@link java.net.URI} object.
     */
    public void setQueueBaseUrl(final URI queueBaseUrl)
    {
        this.queueBaseUrl = queueBaseUrl;
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

    /**
     * <p>Getter for the field <code>trackMetadata</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosTrack} object.
     */
    public SonosTrack getTrackMetadata()
    {
        return trackMetadata;
    }

    /**
     * <p>Setter for the field <code>trackMetadata</code>.</p>
     *
     * @param trackMetadata a {@link engineer.nightowl.sonos.api.domain.SonosTrack} object.
     */
    public void setTrackMetadata(final SonosTrack trackMetadata)
    {
        this.trackMetadata = trackMetadata;
    }

    /**
     * <p>Getter for the field <code>useHttpAuthorizationForMedia</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getUseHttpAuthorizationForMedia()
    {
        return useHttpAuthorizationForMedia;
    }

    /**
     * <p>Setter for the field <code>useHttpAuthorizationForMedia</code>.</p>
     *
     * @param useHttpAuthorizationForMedia a {@link java.lang.Boolean} object.
     */
    public void setUseHttpAuthorizationForMedia(final Boolean useHttpAuthorizationForMedia)
    {
        this.useHttpAuthorizationForMedia = useHttpAuthorizationForMedia;
    }

    /** {@inheritDoc} */
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
