package engineer.nightowl.sonos.api.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SonosPlaylistTrack
{
    private String name;
    private String artist;
    private String album;

    public SonosPlaylistTrack()
    {
    }

    public SonosPlaylistTrack(String name, String artist, String album)
    {
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public String getAlbum()
    {
        return album;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }

    @Override
    public String toString()
    {
        return "SonosPlaylistTrack{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SonosPlaylistTrack that = (SonosPlaylistTrack) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(artist, that.artist)
                .append(album, that.album)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(artist)
                .append(album)
                .toHashCode();
    }
}
