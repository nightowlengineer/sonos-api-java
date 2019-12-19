package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosPlayMode;
import engineer.nightowl.sonos.api.domain.SonosPlaylist;
import engineer.nightowl.sonos.api.domain.SonosPlaylistList;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

import java.util.HashMap;
import java.util.Map;

/**
 * Control playlists in a household.
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/playlists/">Sonos docs</a>
 */
public class PlaylistResource extends SubscribableResource
{
    /**
     * <p>Constructor for PlaylistResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public PlaylistResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /** {@inheritDoc} */
    @Override
    String getSubscriptionPath()
    {
        return "/v1/households/%s/playlists/subscription";
    }

    /**
     * Get playlists for a household
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playlists/getplaylists/">Sonos docs</a>
     * @param clientToken for the user
     * @param householdId the household for which you want to retrieve playlists
     * @return the playlists for the specified household
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosPlaylistList getPlaylists(final String clientToken, final String householdId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosPlaylistList.class, clientToken, String.format("/v1/households/%s/playlists", householdId));
    }

    /**
     * Get a playlist for a household
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playlists/getplaylist/">Sonos docs</a>
     * @param clientToken for the user
     * @param householdId the household for which the playlist belongs to
     * @param playlistId  the specific playlist you want to retrieve
     * @return the specified playlist
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosPlaylist getPlaylist(final String clientToken, final String householdId, final String playlistId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosPlaylist.class, clientToken, String.format("/v1/households/%s/playlists/%s", householdId, playlistId));
    }

    /**
     * Activate a playlist in a group
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playlists/loadplaylist/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to load the playlist in
     * @param playlistId of the playlist
     * @param playOnCompletion (optional) start playing once loaded
     * @param playMode (optional) provide playback options, where supported
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess loadPlaylist(final String clientToken, final String groupId, final String playlistId, final Boolean playOnCompletion,
                                     final SonosPlayMode playMode) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(playlistId, "playlistId");
        final Map<String, Object> payload = new HashMap<>();
        if (playOnCompletion != null)
        {
            payload.put("playOnCompletion", playOnCompletion);
        }
        if (playMode != null)
        {
            payload.put("playModes", playMode);
        }
        payload.put("playlistId", playlistId);

        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playlists", groupId), payload);
    }
}
