package engineer.nightowl.sonos.api.domain;

import java.net.URI;
import java.util.List;

public class SonosFavorite
{
    private String id;
    private String name;
    private String description;
    // TODO: Change to enums once implemented
    private String type;
    private URI imageUrl;
    private List<URI> imageCompilation;
    private SonosService service;

    public SonosFavorite()
    {
    }

    public SonosFavorite(final String id, final String name, final String description, final String type, final URI imageUrl, final List<URI> imageCompilation, final SonosService service)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.imageUrl = imageUrl;
        this.imageCompilation = imageCompilation;
        this.service = service;
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public URI getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(final URI imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public List<URI> getImageCompilation()
    {
        return imageCompilation;
    }

    public void setImageCompilation(final List<URI> imageCompilation)
    {
        this.imageCompilation = imageCompilation;
    }

    public SonosService getService()
    {
        return service;
    }

    public void setService(final SonosService service)
    {
        this.service = service;
    }

    @Override
    public String toString()
    {
        return "SonosFavorite{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl=" + imageUrl +
                ", imageCompilation=" + imageCompilation +
                ", service=" + service +
                '}';
    }
}
