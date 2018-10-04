package engineer.nightowl.sonos.api.domain;

import java.util.List;

public class SonosGroup
{
    private String coordinatorId;
    private String id;
    private String playbackState;
    private List<String> playerIds;
    private String name;

    public SonosGroup()
    {
    }

    public SonosGroup(final String coordinatorId, final String id, final String playbackState, final List<String> playerIds, final String name)
    {
        this.coordinatorId = coordinatorId;
        this.id = id;
        this.playbackState = playbackState;
        this.playerIds = playerIds;
        this.name = name;
    }

    public String getCoordinatorId()
    {
        return coordinatorId;
    }

    public void setCoordinatorId(final String coordinatorId)
    {
        this.coordinatorId = coordinatorId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public String getPlaybackState()
    {
        return playbackState;
    }

    public void setPlaybackState(final String playbackState)
    {
        this.playbackState = playbackState;
    }

    public List<String> getPlayerIds()
    {
        return playerIds;
    }

    public void setPlayerIds(final List<String> playerIds)
    {
        this.playerIds = playerIds;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }
}
