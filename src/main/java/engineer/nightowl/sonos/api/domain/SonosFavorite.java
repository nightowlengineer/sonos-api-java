package engineer.nightowl.sonos.api.domain;

import java.net.URI;
import java.util.List;

/**
 * <p>SonosFavorite class.</p>
 */
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

    /**
     * <p>Constructor for SonosFavorite.</p>
     */
    public SonosFavorite()
    {
    }

    /**
     * <p>Constructor for SonosFavorite.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     * @param description a {@link java.lang.String} object.
     * @param type a {@link java.lang.String} object.
     * @param imageUrl a {@link java.net.URI} object.
     * @param imageCompilation a {@link java.util.List} object.
     * @param service a {@link engineer.nightowl.sonos.api.domain.SonosService} object.
     */
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

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getId()
    {
        return id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id a {@link java.lang.String} object.
     */
    public void setId(final String id)
    {
        this.id = id;
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
     * <p>Getter for the field <code>description</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * <p>Setter for the field <code>description</code>.</p>
     *
     * @param description a {@link java.lang.String} object.
     */
    public void setDescription(final String description)
    {
        this.description = description;
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
     * <p>Getter for the field <code>imageCompilation</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<URI> getImageCompilation()
    {
        return imageCompilation;
    }

    /**
     * <p>Setter for the field <code>imageCompilation</code>.</p>
     *
     * @param imageCompilation a {@link java.util.List} object.
     */
    public void setImageCompilation(final List<URI> imageCompilation)
    {
        this.imageCompilation = imageCompilation;
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

    /** {@inheritDoc} */
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
