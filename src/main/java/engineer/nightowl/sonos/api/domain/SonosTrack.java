package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosTag;

import java.net.URI;
import java.util.List;

class SonosTrack
{
    private Boolean canCrossfade;
    private Boolean canSkip;
    private Integer durationMillis;
    private SonosMusicObjectId id;
    private URI imageUrl;
    private String name;
    private Integer replayGain;
    private List<SonosTag> tags;
    private String type;
    private SonosService service;
    private URI mediaUrl;
    private String contentType;
    private Integer trackNumber;
    private SonosArtist artist;
    private SonosAlbum album;

    public SonosTrack()
    {
    }

    public SonosTrack(final Boolean canCrossfade, final Boolean canSkip, final Integer durationMillis, final SonosMusicObjectId id, final URI imageUrl, final String name, final Integer replayGain, final List<SonosTag> tags, final String type, final SonosService service, final URI mediaUrl, final String contentType, final Integer trackNumber, final SonosArtist artist, final SonosAlbum album)
    {
        this.canCrossfade = canCrossfade;
        this.canSkip = canSkip;
        this.durationMillis = durationMillis;
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.replayGain = replayGain;
        this.tags = tags;
        this.type = type;
        this.service = service;
        this.mediaUrl = mediaUrl;
        this.contentType = contentType;
        this.trackNumber = trackNumber;
        this.artist = artist;
        this.album = album;
    }

    public Boolean getCanCrossfade()
    {
        return canCrossfade;
    }

    public void setCanCrossfade(final Boolean canCrossfade)
    {
        this.canCrossfade = canCrossfade;
    }

    public Boolean getCanSkip()
    {
        return canSkip;
    }

    public void setCanSkip(final Boolean canSkip)
    {
        this.canSkip = canSkip;
    }

    public Integer getDurationMillis()
    {
        return durationMillis;
    }

    public void setDurationMillis(final Integer durationMillis)
    {
        this.durationMillis = durationMillis;
    }

    public SonosMusicObjectId getId()
    {
        return id;
    }

    public void setId(final SonosMusicObjectId id)
    {
        this.id = id;
    }

    public URI getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(final URI imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public Integer getReplayGain()
    {
        return replayGain;
    }

    public void setReplayGain(final Integer replayGain)
    {
        this.replayGain = replayGain;
    }

    public List<SonosTag> getTags()
    {
        return tags;
    }

    public void setTags(final List<SonosTag> tags)
    {
        this.tags = tags;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public SonosService getService()
    {
        return service;
    }

    public void setService(final SonosService service)
    {
        this.service = service;
    }

    public URI getMediaUrl()
    {
        return mediaUrl;
    }

    public void setMediaUrl(final URI mediaUrl)
    {
        this.mediaUrl = mediaUrl;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(final String contentType)
    {
        this.contentType = contentType;
    }

    public Integer getTrackNumber()
    {
        return trackNumber;
    }

    public void setTrackNumber(final Integer trackNumber)
    {
        this.trackNumber = trackNumber;
    }

    public SonosArtist getArtist()
    {
        return artist;
    }

    public void setArtist(final SonosArtist artist)
    {
        this.artist = artist;
    }

    public SonosAlbum getAlbum()
    {
        return album;
    }

    public void setAlbum(final SonosAlbum album)
    {
        this.album = album;
    }

    @Override
    public String toString()
    {
        return "SonosTrack{" +
                "canCrossfade=" + canCrossfade +
                ", canSkip=" + canSkip +
                ", durationMillis=" + durationMillis +
                ", id=" + id +
                ", imageUrl=" + imageUrl +
                ", name='" + name + '\'' +
                ", replayGain=" + replayGain +
                ", tags=" + tags +
                ", type='" + type + '\'' +
                ", service=" + service +
                ", mediaUrl=" + mediaUrl +
                ", contentType='" + contentType + '\'' +
                ", trackNumber=" + trackNumber +
                ", artist=" + artist +
                ", album=" + album +
                '}';
    }
}
