package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosPlayer;
import engineer.nightowl.sonos.api.domain.SonosPlayerVolume;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;
import engineer.nightowl.sonos.api.util.SonosUtilityHelper;

import java.util.HashMap;
import java.util.Map;

public class PlayerVolumeResource extends BaseResource implements Subscribable
{
    public PlayerVolumeResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }


    /**
     * Get the volume properties for the specified player
     *
     * @param clientToken for the user
     * @param playerId    to fetch the volume properties for
     * @return {@link SonosPlayerVolume} for the specified {@link SonosPlayer}
     * @throws SonosApiClientException if an error occurs during the call
     */
    public SonosPlayerVolume getVolume(final String clientToken, final String playerId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosPlayerVolume.class, clientToken, String.format("/v1/players/%s/playerVolume", playerId));
    }

    public SonosSuccess setMute(final String clientToken, final String playerId, final Boolean isMuted) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(isMuted);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("muted", isMuted);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/playerVolume/mute", playerId), payload);
    }

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

    @Override
    public SonosSuccess subscribe(final String clientToken, final String playerId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/playerVolume/subscription", playerId));
    }

    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String playerId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/playerVolume/subscription", playerId));
    }
}
