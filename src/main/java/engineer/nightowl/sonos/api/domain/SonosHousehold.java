package engineer.nightowl.sonos.api.domain;

/**
 * <p>SonosHousehold class.</p>
 */
public class SonosHousehold
{
    private String id;
    private String name;

    /**
     * <p>Constructor for SonosHousehold.</p>
     */
    public SonosHousehold()
    {
    }

    /**
     * <p>Constructor for SonosHousehold.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     */
    public SonosHousehold(final String id, final String name)
    {
        this.id = id;
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
}
