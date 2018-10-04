package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.*;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Household represents a parent object containing a number of players/groups, and favorites.
 * <p>
 * Reference: <a href="https://developer.sonos.com/reference/control-api/groups/">Groups API</a>
 * Reference: <a href="https://developer.sonos.com/reference/control-api/group-volume/">Group Volume API</a>
 *
 * @see SonosGroup
 * @see SonosGroups
 * @see SonosGroupVolume
 */
public class GroupResource extends BaseResource implements Subscribable
{
    public GroupResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Get a list of {@link SonosGroup} and {@link SonosPlayer}
     *
     * @param clientToken for the user
     * @param householdId for the household you want to fetch groups & players
     * @return the groups and players for the specified household
     * @throws SonosApiClientException if an error occurs during the call
     */
    public SonosGroups getGroups(final String clientToken, final String householdId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosGroups.class, clientToken, String.format("/v1/households/%s/groups", householdId));
    }

    public SonosGroupInfo createGroup(final String clientToken, final String householdId, final List<String> playerIds,
                                      final String musicContextGroupId) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(playerIds);
        final Map<String, Object> payload = new HashMap<>();
        if (musicContextGroupId != null)
        {
            payload.put("musicContextGroupId", musicContextGroupId);
        }
        payload.put("playerIds", playerIds);

        return postToApi(SonosGroupInfo.class, clientToken, String.format("/v1/households/%s/groups/createGroup", householdId), payload);
    }

    public SonosGroupInfo modifyGroupMembers(final String clientToken, final String groupId, final List<String> playerIdsToAdd,
                                             final List<String> playerIdsToRemove) throws SonosApiClientException, SonosApiError
    {
        final Map<String, Object> payload = new HashMap<>();
        if (playerIdsToAdd != null && playerIdsToAdd.size() > 0)
        {
            payload.put("playerIdsToAdd", playerIdsToAdd);
        }
        if (playerIdsToRemove != null && playerIdsToRemove.size() > 0)
        {
            payload.put("playerIdsToRemove", playerIdsToRemove);
        }

        return postToApi(SonosGroupInfo.class, clientToken, String.format("/v1/groups/%s/modifyGroupMembers", groupId), payload);
    }

    public SonosGroupInfo setGroupMembers(final String clientToken, final String groupId, final List<String> playerIds) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(playerIds);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("playerIds", playerIds);

        return postToApi(SonosGroupInfo.class, clientToken, String.format("/v1/groups/%s/setGroupMembers", groupId), payload);
    }

    @Override
    public SonosSuccess subscribe(final String clientToken, final String householdId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/households/%s/groups/subscription", householdId));
    }

    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String householdId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/households/%s/groups/subscription", householdId));
    }
}
