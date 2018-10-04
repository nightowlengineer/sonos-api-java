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
 * Get information about players and their groupings
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/groups/">Sonos docs</a>
 * @see engineer.nightowl.sonos.api.domain.SonosGroup
 * @see engineer.nightowl.sonos.api.domain.SonosGroups
 */
public class GroupResource extends BaseResource implements Subscribable
{
    /**
     * <p>Constructor for GroupResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public GroupResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Get a list of {@link engineer.nightowl.sonos.api.domain.SonosGroup} and {@link engineer.nightowl.sonos.api.domain.SonosPlayer}
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/groups/getgroups/">Sonos docs</a>
     * @param clientToken for the user
     * @param householdId for the household you want to fetch groups and players
     * @return the groups and players for the specified household
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosGroups getGroups(final String clientToken, final String householdId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosGroups.class, clientToken, String.format("/v1/households/%s/groups", householdId));
    }

    /**
     * Join players together into a group, optionally with an existing audio stream
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/groups/creategroup/">Sonos docs</a>
     * @param clientToken for the user
     * @param householdId for the household you want to fetch groups and players
     * @param playerIds that you want to join together
     * @param musicContextGroupId if you want to start/continue playing an audio stream
     * @return information about the newly created group as a {@link engineer.nightowl.sonos.api.domain.SonosGroupInfo} object
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
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

    /**
     * Modify an existing group of players
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/groups/modifygroupmembers/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to modify
     * @param playerIdsToAdd list of player IDs to add to the group (optional)
     * @param playerIdsToRemove list of player IDs to remove from the group (optional)
     * @return updated group information once the operation completes
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
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

    /**
     * Provide a list of player IDs to set in an existing group
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/groups/setgroupmembers/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to modify
     * @param playerIds to be set as the players in this group
     * @return updated group information once the operation completes
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosGroupInfo setGroupMembers(final String clientToken, final String groupId, final List<String> playerIds) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(playerIds);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("playerIds", playerIds);

        return postToApi(SonosGroupInfo.class, clientToken, String.format("/v1/groups/%s/setGroupMembers", groupId), payload);
    }

    /**
     * {@inheritDoc}
     *
     * Subscribe to group events in a specified household
     */
    @Override
    public SonosSuccess subscribe(final String clientToken, final String householdId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/households/%s/groups/subscription", householdId));
    }

    /**
     * {@inheritDoc}
     *
     * Unsubscribe from group events in a specified household
     */
    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String householdId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/households/%s/groups/subscription", householdId));
    }
}
