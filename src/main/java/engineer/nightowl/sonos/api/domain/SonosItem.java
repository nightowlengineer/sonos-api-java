package engineer.nightowl.sonos.api.domain;

/**
 * <p>SonosItem class.</p>
 */
public class SonosItem
{
    private String id;
    private SonosTrack track;
    private Boolean deleted;
    private SonosPlaybackPolicy policies;

    /**
     * <p>Constructor for SonosItem.</p>
     */
    public SonosItem()
    {
    }

    /**
     * <p>Constructor for SonosItem.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param track a {@link engineer.nightowl.sonos.api.domain.SonosTrack} object.
     * @param deleted a {@link java.lang.Boolean} object.
     * @param policies a {@link engineer.nightowl.sonos.api.domain.SonosPlaybackPolicy} object.
     */
    public SonosItem(final String id, final SonosTrack track, final Boolean deleted, final SonosPlaybackPolicy policies)
    {
        this.id = id;
        this.track = track;
        this.deleted = deleted;
        this.policies = policies;
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
     * <p>Getter for the field <code>track</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosTrack} object.
     */
    public SonosTrack getTrack()
    {
        return track;
    }

    /**
     * <p>Setter for the field <code>track</code>.</p>
     *
     * @param track a {@link engineer.nightowl.sonos.api.domain.SonosTrack} object.
     */
    public void setTrack(final SonosTrack track)
    {
        this.track = track;
    }

    /**
     * <p>Getter for the field <code>deleted</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getDeleted()
    {
        return deleted;
    }

    /**
     * <p>Setter for the field <code>deleted</code>.</p>
     *
     * @param deleted a {@link java.lang.Boolean} object.
     */
    public void setDeleted(final Boolean deleted)
    {
        this.deleted = deleted;
    }

    /**
     * <p>Getter for the field <code>policies</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosPlaybackPolicy} object.
     */
    public SonosPlaybackPolicy getPolicies()
    {
        return policies;
    }

    /**
     * <p>Setter for the field <code>policies</code>.</p>
     *
     * @param policies a {@link engineer.nightowl.sonos.api.domain.SonosPlaybackPolicy} object.
     */
    public void setPolicies(final SonosPlaybackPolicy policies)
    {
        this.policies = policies;
    }

    /** {@inheritDoc} */
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
