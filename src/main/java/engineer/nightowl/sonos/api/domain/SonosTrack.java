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

    /**
     * <p>Constructor for SonosTrack.</p>
     */
    public SonosTrack()
    {
    }

    /**
     * <p>Constructor for SonosTrack.</p>
     *
     * @param canCrossfade a {@link java.lang.Boolean} object.
     * @param canSkip a {@link java.lang.Boolean} object.
     * @param durationMillis a {@link java.lang.Integer} object.
     * @param id a {@link engineer.nightowl.sonos.api.domain.SonosMusicObjectId} object.
     * @param imageUrl a {@link java.net.URI} object.
     * @param name a {@link java.lang.String} object.
     * @param replayGain a {@link java.lang.Integer} object.
     * @param tags a {@link java.util.List} object.
     * @param type a {@link java.lang.String} object.
     * @param service a {@link engineer.nightowl.sonos.api.domain.SonosService} object.
     * @param mediaUrl a {@link java.net.URI} object.
     * @param contentType a {@link java.lang.String} object.
     * @param trackNumber a {@link java.lang.Integer} object.
     * @param artist a {@link engineer.nightowl.sonos.api.domain.SonosArtist} object.
     * @param album a {@link engineer.nightowl.sonos.api.domain.SonosAlbum} object.
     */
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

    /**
     * <p>Getter for the field <code>canCrossfade</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanCrossfade()
    {
        return canCrossfade;
    }

    /**
     * <p>Setter for the field <code>canCrossfade</code>.</p>
     *
     * @param canCrossfade a {@link java.lang.Boolean} object.
     */
    public void setCanCrossfade(final Boolean canCrossfade)
    {
        this.canCrossfade = canCrossfade;
    }

    /**
     * <p>Getter for the field <code>canSkip</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanSkip()
    {
        return canSkip;
    }

    /**
     * <p>Setter for the field <code>canSkip</code>.</p>
     *
     * @param canSkip a {@link java.lang.Boolean} object.
     */
    public void setCanSkip(final Boolean canSkip)
    {
        this.canSkip = canSkip;
    }

    /**
     * <p>Getter for the field <code>durationMillis</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getDurationMillis()
    {
        return durationMillis;
    }

    /**
     * <p>Setter for the field <code>durationMillis</code>.</p>
     *
     * @param durationMillis a {@link java.lang.Integer} object.
     */
    public void setDurationMillis(final Integer durationMillis)
    {
        this.durationMillis = durationMillis;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosMusicObjectId} object.
     */
    public SonosMusicObjectId getId()
    {
        return id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id a {@link engineer.nightowl.sonos.api.domain.SonosMusicObjectId} object.
     */
    public void setId(final SonosMusicObjectId id)
    {
        this.id = id;
    }

    /**
     * <p>Getter for the field <code>imageUrl</code>.</p>
     *
     * @return a {@link java.net.URI} object.
     */
    public URI getImageUrl()
    {
        return imageUrl;
    }

    /**
     * <p>Setter for the field <code>imageUrl</code>.</p>
     *
     * @param imageUrl a {@link java.net.URI} object.
     */
    public void setImageUrl(final URI imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * <p>Getter for the field <code>replayGain</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getReplayGain()
    {
        return replayGain;
    }

    /**
     * <p>Setter for the field <code>replayGain</code>.</p>
     *
     * @param replayGain a {@link java.lang.Integer} object.
     */
    public void setReplayGain(final Integer replayGain)
    {
        this.replayGain = replayGain;
    }

    /**
     * <p>Getter for the field <code>tags</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<SonosTag> getTags()
    {
        return tags;
    }

    /**
     * <p>Setter for the field <code>tags</code>.</p>
     *
     * @param tags a {@link java.util.List} object.
     */
    public void setTags(final List<SonosTag> tags)
    {
        this.tags = tags;
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getType()
    {
        return type;
    }

    /**
     * <p>Setter for the field <code>type</code>.</p>
     *
     * @param type a {@link java.lang.String} object.
     */
    public void setType(final String type)
    {
        this.type = type;
    }

    /**
     * <p>Getter for the field <code>service</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosService} object.
     */
    public SonosService getService()
    {
        return service;
    }

    /**
     * <p>Setter for the field <code>service</code>.</p>
     *
     * @param service a {@link engineer.nightowl.sonos.api.domain.SonosService} object.
     */
    public void setService(final SonosService service)
    {
        this.service = service;
    }

    /**
     * <p>Getter for the field <code>mediaUrl</code>.</p>
     *
     * @return a {@link java.net.URI} object.
     */
    public URI getMediaUrl()
    {
        return mediaUrl;
    }

    /**
     * <p>Setter for the field <code>mediaUrl</code>.</p>
     *
     * @param mediaUrl a {@link java.net.URI} object.
     */
    public void setMediaUrl(final URI mediaUrl)
    {
        this.mediaUrl = mediaUrl;
    }

    /**
     * <p>Getter for the field <code>contentType</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getContentType()
    {
        return contentType;
    }

    /**
     * <p>Setter for the field <code>contentType</code>.</p>
     *
     * @param contentType a {@link java.lang.String} object.
     */
    public void setContentType(final String contentType)
    {
        this.contentType = contentType;
    }

    /**
     * <p>Getter for the field <code>trackNumber</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getTrackNumber()
    {
        return trackNumber;
    }

    /**
     * <p>Setter for the field <code>trackNumber</code>.</p>
     *
     * @param trackNumber a {@link java.lang.Integer} object.
     */
    public void setTrackNumber(final Integer trackNumber)
    {
        this.trackNumber = trackNumber;
    }

    /**
     * <p>Getter for the field <code>artist</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosArtist} object.
     */
    public SonosArtist getArtist()
    {
        return artist;
    }

    /**
     * <p>Setter for the field <code>artist</code>.</p>
     *
     * @param artist a {@link engineer.nightowl.sonos.api.domain.SonosArtist} object.
     */
    public void setArtist(final SonosArtist artist)
    {
        this.artist = artist;
    }

    /**
     * <p>Getter for the field <code>album</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosAlbum} object.
     */
    public SonosAlbum getAlbum()
    {
        return album;
    }

    /**
     * <p>Setter for the field <code>album</code>.</p>
     *
     * @param album a {@link engineer.nightowl.sonos.api.domain.SonosAlbum} object.
     */
    public void setAlbum(final SonosAlbum album)
    {
        this.album = album;
    }

    /** {@inheritDoc} */
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
