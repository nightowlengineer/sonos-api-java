package engineer.nightowl.sonos.api.domain;

import java.net.URI;

class SonosArtist
{
    private String name;
    private URI imageUrl;
    private SonosMusicObjectId id;

    public SonosArtist()
    {
    }

    public SonosArtist(final String name, final URI imageUrl, final SonosMusicObjectId id)
    {
        this.name = name;
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
        return "SonosArtist{" +
                "name='" + name + '\'' +
                ", imageUrl=" + imageUrl +
                ", id=" + id +
                '}';
    }
}
