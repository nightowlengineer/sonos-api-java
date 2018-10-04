package engineer.nightowl.sonos.api.domain;

import java.net.URI;

class SonosAlbum
{
    private String name;
    private SonosArtist artist;
    private URI imageUrl;
    private SonosMusicObjectId id;

    public SonosAlbum()
    {
    }

    public SonosAlbum(final String name, final SonosArtist artist, final URI imageUrl, final SonosMusicObjectId id)
    {
        this.name = name;
        this.artist = artist;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public SonosArtist getArtist()
    {
        return artist;
    }

    public void setArtist(final SonosArtist artist)
    {
        this.artist = artist;
    }

    public URI getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(final URI imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public SonosMusicObjectId getId()
    {
        return id;
    }

    public void setId(final SonosMusicObjectId id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "SonosAlbum{" +
                "name='" + name + '\'' +
                ", artist=" + artist +
                ", imageUrl=" + imageUrl +
                ", id=" + id +
                '}';
    }
}
