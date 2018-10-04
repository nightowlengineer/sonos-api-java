package engineer.nightowl.sonos.api.domain;

import java.net.URI;

/**
 * <p>SonosStreamUrlRequest class.</p>
 */
public class SonosStreamUrlRequest
{
    private String itemId;
    private URI streamUrl;
    private Boolean playOnCompletion;
    private SonosMusicContainer stationMetadata;

    /**
     * <p>Constructor for SonosStreamUrlRequest.</p>
     */
    public SonosStreamUrlRequest()
    {
    }

    /**
     * <p>Constructor for SonosStreamUrlRequest.</p>
     *
     * @param itemId a {@link java.lang.String} object.
     * @param streamUrl a {@link java.net.URI} object.
     * @param playOnCompletion a {@link java.lang.Boolean} object.
     * @param stationMetadata a {@link engineer.nightowl.sonos.api.domain.SonosMusicContainer} object.
     */
    public SonosStreamUrlRequest(final String itemId, final URI streamUrl, final Boolean playOnCompletion, final SonosMusicContainer stationMetadata)
    {
        this.itemId = itemId;
        this.streamUrl = streamUrl;
        this.playOnCompletion = playOnCompletion;
        this.stationMetadata = stationMetadata;
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
     * <p>Getter for the field <code>streamUrl</code>.</p>
     *
     * @return a {@link java.net.URI} object.
     */
    public URI getStreamUrl()
    {
        return streamUrl;
    }

    /**
     * <p>Setter for the field <code>streamUrl</code>.</p>
     *
     * @param streamUrl a {@link java.net.URI} object.
     */
    public void setStreamUrl(final URI streamUrl)
    {
        this.streamUrl = streamUrl;
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
     * <p>Getter for the field <code>stationMetadata</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosMusicContainer} object.
     */
    public SonosMusicContainer getStationMetadata()
    {
        return stationMetadata;
    }

    /**
     * <p>Setter for the field <code>stationMetadata</code>.</p>
     *
     * @param stationMetadata a {@link engineer.nightowl.sonos.api.domain.SonosMusicContainer} object.
     */
    public void setStationMetadata(final SonosMusicContainer stationMetadata)
    {
        this.stationMetadata = stationMetadata;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SonosStreamUrlRequest{" +
                "itemId='" + itemId + '\'' +
                ", streamUrl=" + streamUrl +
                ", playOnCompletion=" + playOnCompletion +
                ", stationMetadata=" + stationMetadata +
                '}';
    }
}
