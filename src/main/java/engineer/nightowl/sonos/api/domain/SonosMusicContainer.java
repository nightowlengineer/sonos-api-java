package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosTag;

import java.net.URI;
import java.util.List;

public class SonosMusicContainer
{
    private String name;
    private SonosMusicSource type;
    private SonosMusicObjectId id;
    private SonosService service;
    private URI imageUrl;
    private List<SonosTag> tags;

    public SonosMusicContainer()
    {
    }

    public SonosMusicContainer(final String name, final SonosMusicSource type, final SonosMusicObjectId id, final SonosService service, final URI imageUrl, final List<SonosTag> tags)
    {
        this.name = name;
        this.type = type;
        this.id = id;
        this.service = service;
        this.imageUrl = imageUrl;
        this.tags = tags;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public SonosMusicSource getType()
    {
        return type;
    }

    public void setType(final SonosMusicSource type)
    {
        this.type = type;
    }

    public SonosMusicObjectId getId()
    {
        return id;
    }

    public void setId(final SonosMusicObjectId id)
    {
        this.id = id;
    }

    public SonosService getService()
    {
        return service;
    }

    public void setService(final SonosService service)
    {
        this.service = service;
    }

    public URI getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(final URI imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public List<SonosTag> getTags()
    {
        return tags;
    }

    public void setTags(final List<SonosTag> tags)
    {
        this.tags = tags;
    }

    @Override
    public String toString()
    {
        return "SonosMusicContainer{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", service=" + service +
                ", imageUrl=" + imageUrl +
                ", tags=" + tags +
                '}';
    }
}
