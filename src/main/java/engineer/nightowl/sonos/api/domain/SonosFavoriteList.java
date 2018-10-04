package engineer.nightowl.sonos.api.domain;

import java.util.List;

/**
 * <p>SonosFavoriteList class.</p>
 */
public class SonosFavoriteList
{
    private String version;
    private List<SonosFavorite> items;

    /**
     * <p>Constructor for SonosFavoriteList.</p>
     */
    public SonosFavoriteList()
    {
    }

    /**
     * <p>Constructor for SonosFavoriteList.</p>
     *
     * @param version a {@link java.lang.String} object.
     * @param items a {@link java.util.List} object.
     */
    public SonosFavoriteList(final String version, final List<SonosFavorite> items)
    {
        this.version = version;
        this.items = items;
    }

    /**
     * <p>Getter for the field <code>version</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * <p>Setter for the field <code>version</code>.</p>
     *
     * @param version a {@link java.lang.String} object.
     */
    public void setVersion(final String version)
    {
        this.version = version;
    }

    /**
     * <p>Getter for the field <code>items</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<SonosFavorite> getItems()
    {
        return items;
    }

    /**
     * <p>Setter for the field <code>items</code>.</p>
     *
     * @param items a {@link java.util.List} object.
     */
    public void setItems(final List<SonosFavorite> items)
    {
        this.items = items;
    }
}
