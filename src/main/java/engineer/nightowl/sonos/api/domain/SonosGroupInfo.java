package engineer.nightowl.sonos.api.domain;

public class SonosGroupInfo
{
    private SonosGroup group;

    public SonosGroupInfo()
    {
    }

    public SonosGroupInfo(final SonosGroup group)
    {
        this.group = group;
    }

    public SonosGroup getGroup()
    {
        return group;
    }

    public void setGroup(final SonosGroup group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return "SonosGroupInfo{" +
                "group=" + group +
                '}';
    }
}
