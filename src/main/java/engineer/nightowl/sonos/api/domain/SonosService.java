package engineer.nightowl.sonos.api.domain;

import java.net.URI;

public class SonosService
{
    private String name;
    private String id;
    private URI imageUrl;

    public SonosService()
    {
    }

    public SonosService(final String name, final String id, final URI imageUrl)
    {
        this.name = name;
        this.id = id;
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

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
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
}
