package engineer.nightowl.sonos.api.domain;

public class SonosItem
{
    private String id;
    private SonosTrack track;
    private Boolean deleted;
    private SonosPlaybackPolicy policies;

    public SonosItem()
    {
    }

    public SonosItem(final String id, final SonosTrack track, final Boolean deleted, final SonosPlaybackPolicy policies)
    {
        this.id = id;
        this.track = track;
        this.deleted = deleted;
        this.policies = policies;
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public SonosTrack getTrack()
    {
        return track;
    }

    public void setTrack(final SonosTrack track)
    {
        this.track = track;
    }

    public Boolean getDeleted()
    {
        return deleted;
    }

    public void setDeleted(final Boolean deleted)
    {
        this.deleted = deleted;
    }

    public SonosPlaybackPolicy getPolicies()
    {
        return policies;
    }

    public void setPolicies(final SonosPlaybackPolicy policies)
    {
        this.policies = policies;
    }

    @Override
    public String toString()
    {
        return "SonosItem{" +
                "id='" + id + '\'' +
                ", track=" + track +
                ", deleted=" + deleted +
                ", policies=" + policies +
                '}';
    }
}
