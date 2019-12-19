package engineer.nightowl.sonos.api.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class SonosPlaylist
{
    private String id;
    private String name;
    private String type;
    private Integer trackCount;
    private List<SonosPlaylistTrack> tracks;

    public SonosPlaylist()
    {
    }

    public SonosPlaylist(String id, String name, String type, Integer trackCount, List<SonosPlaylistTrack> tracks)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.trackCount = trackCount;
        this.tracks = tracks;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Integer getTrackCount()
    {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount)
    {
        this.trackCount = trackCount;
    }

    public List<SonosPlaylistTrack> getTracks()
    {
        return tracks;
    }

    public void setTracks(List<SonosPlaylistTrack> tracks)
    {
        this.tracks = tracks;
    }

    @Override
    public String toString()
    {
        return "SonosPlaylist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", trackCount=" + trackCount +
                ", tracks=" + tracks +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SonosPlaylist that = (SonosPlaylist) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(type, that.type)
                .append(trackCount, that.trackCount)
                .append(tracks, that.tracks)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(type)
                .append(trackCount)
                .append(tracks)
                .toHashCode();
    }
}
