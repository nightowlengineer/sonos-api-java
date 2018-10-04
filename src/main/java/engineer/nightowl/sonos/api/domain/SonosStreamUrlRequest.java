package engineer.nightowl.sonos.api.domain;

import java.net.URI;

public class SonosStreamUrlRequest
{
    private String itemId;
    private URI streamUrl;
    private Boolean playOnCompletion;
    private SonosMusicContainer stationMetadata;

    public SonosStreamUrlRequest()
    {
    }

    public SonosStreamUrlRequest(final String itemId, final URI streamUrl, final Boolean playOnCompletion, final SonosMusicContainer stationMetadata)
    {
        this.itemId = itemId;
        this.streamUrl = streamUrl;
        this.playOnCompletion = playOnCompletion;
        this.stationMetadata = stationMetadata;
    }

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(final String itemId)
    {
        this.itemId = itemId;
    }

    public URI getStreamUrl()
    {
        return streamUrl;
    }

    public void setStreamUrl(final URI streamUrl)
    {
        this.streamUrl = streamUrl;
    }

    public Boolean getPlayOnCompletion()
    {
        return playOnCompletion;
    }

    public void setPlayOnCompletion(final Boolean playOnCompletion)
    {
        this.playOnCompletion = playOnCompletion;
    }

    public SonosMusicContainer getStationMetadata()
    {
        return stationMetadata;
    }

    public void setStationMetadata(final SonosMusicContainer stationMetadata)
    {
        this.stationMetadata = stationMetadata;
    }

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
