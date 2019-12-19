package engineer.nightowl.sonos.api.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class SonosPlaylistList
{
    private String version;
    private List<SonosPlaylist> playlists;

    public SonosPlaylistList()
    {
    }

    public SonosPlaylistList(String version, List<SonosPlaylist> playlists)
    {
        this.version = version;
        this.playlists = playlists;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public List<SonosPlaylist> getPlaylists()
    {
        return playlists;
    }

    public void setPlaylists(List<SonosPlaylist> playlists)
    {
        this.playlists = playlists;
    }

    @Override
    public String toString()
    {
        return "SonosPlaylistList{" +
                "version='" + version + '\'' +
                ", playlists=" + playlists +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SonosPlaylistList that = (SonosPlaylistList) o;

        return new EqualsBuilder()
                .append(version, that.version)
                .append(playlists, that.playlists)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(version)
                .append(playlists)
                .toHashCode();
    }
}
