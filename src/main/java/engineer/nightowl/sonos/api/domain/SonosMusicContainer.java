package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosTag;

import java.net.URI;
import java.util.List;

/**
 * <p>SonosMusicContainer class.</p>
 */
public class SonosMusicContainer
{
    private String name;
    private SonosMusicSource type;
    private SonosMusicObjectId id;
    private SonosService service;
    private URI imageUrl;
    private List<SonosTag> tags;

    /**
     * <p>Constructor for SonosMusicContainer.</p>
     */
    public SonosMusicContainer()
    {
    }

    /**
     * <p>Constructor for SonosMusicContainer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link engineer.nightowl.sonos.api.domain.SonosMusicSource} object.
     * @param id a {@link engineer.nightowl.sonos.api.domain.SonosMusicObjectId} object.
     * @param service a {@link engineer.nightowl.sonos.api.domain.SonosService} object.
     * @param imageUrl a {@link java.net.URI} object.
     * @param tags a {@link java.util.List} object.
     */
    public SonosMusicContainer(final String name, final SonosMusicSource type, final SonosMusicObjectId id, final SonosService service, final URI imageUrl, final List<SonosTag> tags)
    {
        this.name = name;
        this.type = type;
        this.id = id;
        this.service = service;
        this.imageUrl = imageUrl;
        this.tags = tags;
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
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosMusicSource} object.
     */
    public SonosMusicSource getType()
    {
        return type;
    }

    /**
     * <p>Setter for the field <code>type</code>.</p>
     *
     * @param type a {@link engineer.nightowl.sonos.api.domain.SonosMusicSource} object.
     */
    public void setType(final SonosMusicSource type)
    {
        this.type = type;
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

    /** {@inheritDoc} */
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
