package engineer.nightowl.sonos.api.domain;

import java.util.List;

/**
 * <p>SonosGroups class.</p>
 */
public class SonosGroups
{
    private List<SonosGroup> groups;
    private List<SonosPlayer> players;

    /**
     * <p>Constructor for SonosGroups.</p>
     */
    public SonosGroups()
    {
    }

    /**
     * <p>Constructor for SonosGroups.</p>
     *
     * @param groups a {@link java.util.List} object.
     * @param players a {@link java.util.List} object.
     */
    public SonosGroups(final List<SonosGroup> groups, final List<SonosPlayer> players)
    {
        this.groups = groups;
        this.players = players;
    }

    /**
     * <p>Getter for the field <code>groups</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<SonosGroup> getGroups()
    {
        return groups;
    }

    /**
     * <p>Setter for the field <code>groups</code>.</p>
     *
     * @param groups a {@link java.util.List} object.
     */
    public void setGroups(final List<SonosGroup> groups)
    {
        this.groups = groups;
    }

    /**
     * <p>Getter for the field <code>players</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<SonosPlayer> getPlayers()
    {
        return players;
    }

    /**
     * <p>Setter for the field <code>players</code>.</p>
     *
     * @param players a {@link java.util.List} object.
     */
    public void setPlayers(final List<SonosPlayer> players)
    {
        this.players = players;
    }
}
