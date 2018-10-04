package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosPlayMode;
import engineer.nightowl.sonos.api.domain.SonosPlaybackStatus;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;

import java.util.HashMap;
import java.util.Map;

public class PlaybackResource extends BaseResource implements Subscribable
{
    public PlaybackResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Get playback status for the specified group
     *
     * @param clientToken for the user
     * @param groupId     for the group you want to fetch the status for
     * @return the favorites for the specified household
     * @throws SonosApiClientException if an error occurs during the call
     */
    public SonosPlaybackStatus getPlaybackStatus(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosPlaybackStatus.class, clientToken, String.format("/v1/groups/%s/playback", groupId));
    }

    /**
     * Get playback status for the specified group
     *
     * @param clientToken for the user
     * @param groupId     for the group you want to fetch the status for
     * @return the favorites for the specified household
     * @throws SonosApiClientException if an error occurs during the call
     */
    public SonosSuccess loadLineIn(final String clientToken, final String groupId, final String deviceId,
                                   final Boolean playOnCompletion) throws SonosApiClientException, SonosApiError
    {
        final Map<String, Object> payload = new HashMap<>();
        if (deviceId != null)
        {
            payload.put("deviceId", deviceId);
        }
        if (playOnCompletion != null)
        {
            payload.put("playOnCompletion", playOnCompletion);
        }

        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/lineIn", groupId), payload);
    }

    public SonosSuccess play(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/play", groupId));
    }

    public SonosSuccess pause(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/pause", groupId));
    }

    public SonosSuccess seek(final String clientToken, final String groupId, final String itemId,
                             final Integer positionMillis) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(positionMillis);
        final Map<String, Object> payload = new HashMap<>();
        if (itemId != null)
        {
            payload.put("itemId", itemId);
        }
        payload.put("positionMillis", positionMillis);

        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/seek", groupId), payload);
    }

    public SonosSuccess seekRelative(final String clientToken, final String groupId, final String itemId,
                                     final Integer deltaMillis) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(deltaMillis);
        final Map<String, Object> payload = new HashMap<>();
        if (itemId != null)
        {
            payload.put("itemId", itemId);
        }
        payload.put("positionMillis", deltaMillis);

        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/seekRelative", groupId), payload);
    }

    public SonosSuccess setPlayModes(final String clientToken, final String groupId, final SonosPlayMode playMode) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(playMode);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("playModes", playMode);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/playMode", groupId), payload);
    }

    public SonosSuccess skipToNextTrack(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/skipToNextTrack", groupId));
    }

    public SonosSuccess skipToPreviousTrack(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/skipToPreviousTrack", groupId));
    }

    @Override
    public SonosSuccess subscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/subscription", groupId));
    }

    public SonosSuccess togglePlayPause(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/togglePlayPause", groupId));
    }

    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/subscription", groupId));
    }
}
