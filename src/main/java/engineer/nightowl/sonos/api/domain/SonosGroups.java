package engineer.nightowl.sonos.api.domain;

import java.util.List;

public class SonosGroups
{
    private List<SonosGroup> groups;
    private List<SonosPlayer> players;

    public SonosGroups()
    {
    }

    public SonosGroups(final List<SonosGroup> groups, final List<SonosPlayer> players)
    {
        this.groups = groups;
        this.players = players;
    }

    public List<SonosGroup> getGroups()
    {
        return groups;
    }

    public void setGroups(final List<SonosGroup> groups)
    {
        this.groups = groups;
    }

    public List<SonosPlayer> getPlayers()
    {
        return players;
    }

    public void setPlayers(final List<SonosPlayer> players)
    {
        this.players = players;
    }
}
