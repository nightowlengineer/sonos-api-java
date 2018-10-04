package engineer.nightowl.sonos.api.domain;

import java.util.List;

public class SonosFavoriteList
{
    private String version;
    private List<SonosFavorite> items;

    public SonosFavoriteList()
    {
    }

    public SonosFavoriteList(final String version, final List<SonosFavorite> items)
    {
        this.version = version;
        this.items = items;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(final String version)
    {
        this.version = version;
    }

    public List<SonosFavorite> getItems()
    {
        return items;
    }

    public void setItems(final List<SonosFavorite> items)
    {
        this.items = items;
    }
}
