package engineer.nightowl.sonos.api.domain;

/**
 * <p>SonosGroupInfo class.</p>
 */
public class SonosGroupInfo
{
    private SonosGroup group;

    /**
     * <p>Constructor for SonosGroupInfo.</p>
     */
    public SonosGroupInfo()
    {
    }

    /**
     * <p>Constructor for SonosGroupInfo.</p>
     *
     * @param group a {@link engineer.nightowl.sonos.api.domain.SonosGroup} object.
     */
    public SonosGroupInfo(final SonosGroup group)
    {
        this.group = group;
    }

    /**
     * <p>Getter for the field <code>group</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosGroup} object.
     */
    public SonosGroup getGroup()
    {
        return group;
    }

    /**
     * <p>Setter for the field <code>group</code>.</p>
     *
     * @param group a {@link engineer.nightowl.sonos.api.domain.SonosGroup} object.
     */
    public void setGroup(final SonosGroup group)
    {
        this.group = group;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SonosGroupInfo{" +
                "group=" + group +
                '}';
    }
}
