package engineer.nightowl.sonos.api.domain;

import java.util.List;

/**
 * <p>SonosGroup class.</p>
 */
public class SonosGroup
{
    private String coordinatorId;
    private String id;
    private String playbackState;
    private List<String> playerIds;
    private String name;

    /**
     * <p>Constructor for SonosGroup.</p>
     */
    public SonosGroup()
    {
    }

    /**
     * <p>Constructor for SonosGroup.</p>
     *
     * @param coordinatorId a {@link java.lang.String} object.
     * @param id a {@link java.lang.String} object.
     * @param playbackState a {@link java.lang.String} object.
     * @param playerIds a {@link java.util.List} object.
     * @param name a {@link java.lang.String} object.
     */
    public SonosGroup(final String coordinatorId, final String id, final String playbackState, final List<String> playerIds, final String name)
    {
        this.coordinatorId = coordinatorId;
        this.id = id;
        this.playbackState = playbackState;
        this.playerIds = playerIds;
        this.name = name;
    }

    /**
     * <p>Getter for the field <code>coordinatorId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCoordinatorId()
    {
        return coordinatorId;
    }

    /**
     * <p>Setter for the field <code>coordinatorId</code>.</p>
     *
     * @param coordinatorId a {@link java.lang.String} object.
     */
    public void setCoordinatorId(final String coordinatorId)
    {
        this.coordinatorId = coordinatorId;
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
     * <p>Getter for the field <code>playbackState</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPlaybackState()
    {
        return playbackState;
    }

    /**
     * <p>Setter for the field <code>playbackState</code>.</p>
     *
     * @param playbackState a {@link java.lang.String} object.
     */
    public void setPlaybackState(final String playbackState)
    {
        this.playbackState = playbackState;
    }

    /**
     * <p>Getter for the field <code>playerIds</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<String> getPlayerIds()
    {
        return playerIds;
    }

    /**
     * <p>Setter for the field <code>playerIds</code>.</p>
     *
     * @param playerIds a {@link java.util.List} object.
     */
    public void setPlayerIds(final List<String> playerIds)
    {
        this.playerIds = playerIds;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(final String name)
    {
        this.name = name;
    }
}
