package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosPlayerVolume;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;
import engineer.nightowl.sonos.api.util.SonosUtilityHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Control player volume state
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/playervolume/">Sonos docs</a>
 */
public class PlayerVolumeResource extends BaseResource implements Subscribable
{
    /**
     * <p>Constructor for PlayerVolumeResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public PlayerVolumeResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }


    /**
     * Get the volume properties for the specified player
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playervolume/getvolume/">Sonos docs</a>
     * @param clientToken for the user
     * @param playerId    to fetch the volume properties for
     * @return {@link engineer.nightowl.sonos.api.domain.SonosPlayerVolume} for the specified {@link engineer.nightowl.sonos.api.domain.SonosPlayer}
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosPlayerVolume getVolume(final String clientToken, final String playerId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosPlayerVolume.class, clientToken, String.format("/v1/players/%s/playerVolume", playerId));
    }

    /**
     * Set the mute status of a player (not a toggle)
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playervolume/setmute/">Sonos docs</a>
     * @param clientToken for the user
     * @param playerId of the player to mute/unmute
     * @param isMuted whether the player should be muted/unmuted
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess setMute(final String clientToken, final String playerId, final Boolean isMuted) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(isMuted);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("muted", isMuted);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/playerVolume/mute", playerId), payload);
    }

    /**
     * Adjust the volume of a player by the specified amount
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playervolume/setrelativevolume/">Sonos docs</a>
     * @param clientToken for the user
     * @param playerId to set the volume for
     * @param volumeDelta to adjust the volume by
     * @param isMuted if the player should be muted
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess setRelativeVolume(final String clientToken, final String playerId, final Integer volumeDelta, final Boolean isMuted) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(volumeDelta);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("volumeDelta", volumeDelta);
        if (!SonosUtilityHelper.isEmpty(isMuted))
        {
            payload.put("muted", isMuted);
        }
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/playerVolume/relative", playerId), payload);
    }

    /**
     * Set a specific volume for a player
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playervolume/setvolume/">Sonos docs</a>
     * @param clientToken for the user
     * @param playerId to modify the volume of
     * @param volume to set the player to
     * @param isMuted if the player should be muted
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess setVolume(final String clientToken, final String playerId, final Integer volume, final Boolean isMuted) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(volume);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("volume", volume);
        if (!SonosUtilityHelper.isEmpty(isMuted))
        {
            payload.put("muted", isMuted);
        }
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/playerVolume", playerId), payload);
    }

    /**
     * {@inheritDoc}
     *
     * Subscribe to volume events for a player
     */
    @Override
    public SonosSuccess subscribe(final String clientToken, final String playerId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/playerVolume/subscription", playerId));
    }

    /**
     * {@inheritDoc}
     *
     * Unsubscribe from volume events for a player
     */
    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String playerId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/playerVolume/subscription", playerId));
    }
}
