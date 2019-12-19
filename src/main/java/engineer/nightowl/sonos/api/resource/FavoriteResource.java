package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosFavoriteList;
import engineer.nightowl.sonos.api.domain.SonosPlayMode;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

import java.util.HashMap;
import java.util.Map;

/**
 * Favorites are 'shortcuts' to playlists, albums, artists etc.
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/favorites/">Sonos docs</a>
 * @see engineer.nightowl.sonos.api.domain.SonosFavorite
 */
public class FavoriteResource extends SubscribableResource
{
    /**
     * <p>Constructor for FavoriteResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public FavoriteResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /** {@inheritDoc} */
    @Override
    String getSubscriptionPath()
    {
        return "/v1/households/%s/favorites/subscription";
    }

    /**
     * Get favorites for the specified household
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/favorites/getfavorites/">Sonos docs</a>
     * @param clientToken for the user
     * @param householdId for the household you want to fetch favorites
     * @return the favorites for the specified household
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError           if the API returns an error
     */
    public SonosFavoriteList getFavorites(final String clientToken, final String householdId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosFavoriteList.class, clientToken, String.format("/v1/households/%s/favorites", householdId));
    }

    /**
     * Activate a favorite in a group
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/favorites/loadfavorite/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to load the favorite in
     * @param favoriteId of the favorite
     * @param playOnCompletion (optional) start playing once loaded
     * @param playMode (optional) provide playback options, where supported
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess loadFavorite(final String clientToken, final String groupId, final String favoriteId, final Boolean playOnCompletion,
                                     final SonosPlayMode playMode) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(favoriteId, "favoriteId");
        final Map<String, Object> payload = new HashMap<>();
        if (playOnCompletion != null)
        {
            payload.put("playOnCompletion", playOnCompletion);
        }
        if (playMode != null)
        {
            payload.put("playModes", playMode);
        }
        payload.put("favoriteId", favoriteId);

        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/favorites", groupId), payload);
    }
}
