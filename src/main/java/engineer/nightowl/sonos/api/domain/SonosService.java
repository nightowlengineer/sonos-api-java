package engineer.nightowl.sonos.api.domain;

import java.net.URI;

/**
 * <p>SonosService class.</p>
 */
public class SonosService
{
    private String name;
    private String id;
    private URI imageUrl;

    /**
     * <p>Constructor for SonosService.</p>
     */
    public SonosService()
    {
    }

    /**
     * <p>Constructor for SonosService.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param id a {@link java.lang.String} object.
     * @param imageUrl a {@link java.net.URI} object.
     */
    public SonosService(final String name, final String id, final URI imageUrl)
    {
        this.name = name;
        this.id = id;
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
}
