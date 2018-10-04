package engineer.nightowl.sonos.api.domain;

import java.net.URI;

class SonosArtist
{
    private String name;
    private URI imageUrl;
    private SonosMusicObjectId id;

    /**
     * <p>Constructor for SonosArtist.</p>
     */
    public SonosArtist()
    {
    }

    /**
     * <p>Constructor for SonosArtist.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param imageUrl a {@link java.net.URI} object.
     * @param id a {@link engineer.nightowl.sonos.api.domain.SonosMusicObjectId} object.
     */
    public SonosArtist(final String name, final URI imageUrl, final SonosMusicObjectId id)
    {
        this.name = name;
        this.imageUrl = imageUrl;
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

    /** {@inheritDoc} */
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
