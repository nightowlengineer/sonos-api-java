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

/**
 * Control playback of groups in a household - play, pause, seek etc.
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/playback/">Sonos docs</a>
 */
public class PlaybackResource extends BaseResource implements Subscribable
{
    /**
     * <p>Constructor for PlaybackResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public PlaybackResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Get playback status for the specified group
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/getplaybackstatus/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId     for the group you want to fetch the status for
     * @return the favorites for the specified household
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosPlaybackStatus getPlaybackStatus(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosPlaybackStatus.class, clientToken, String.format("/v1/groups/%s/playback", groupId));
    }

    /**
     * Set a device in a group to use the line-in source.
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/loadlinein/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId     for the group the device belongs to
     * @param deviceId of the device with a line-in source connected
     * @param playOnCompletion whether to start or continue playback once switched to line-in (if not provided, false)
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
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

    /**
     * Set a group to 'play' (not a toggle, see {@link #togglePlayPause(String, String)}
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/play">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to play
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess play(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/play", groupId));
    }

    /**
     * Set a group to 'pause' (not a toggle, see {@link #togglePlayPause(String, String)}
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/pause/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to pause
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess pause(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/pause", groupId));
    }

    /**
     * Go to a specific point in a track
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/seek/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId of the group to seek
     * @param itemId which should be targeted for the seek (optional - to ensure the right item is targeted)
     * @param positionMillis to seek to
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
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

    /**
     * Seek to a relative position in a track. See {@link #seek(String, String, String, Integer)}
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/seekrelative/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId of the group to seek
     * @param itemId to (optionally) target the seek
     * @param deltaMillis to seek by
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
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

    /**
     * Set playback options for a group (e.g. repeat, crossfade etc)
     *
     * @see engineer.nightowl.sonos.api.domain.SonosPlayMode
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/set-play-modes/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to set play mods for
     * @param playMode a {@link engineer.nightowl.sonos.api.domain.SonosPlayMode} object containing partial or full settings
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess setPlayModes(final String clientToken, final String groupId, final SonosPlayMode playMode) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(playMode);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("playModes", playMode);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/playMode", groupId), payload);
    }

    /**
     * Skip to the next track
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/skip-to-next-track/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to skip within
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess skipToNextTrack(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/skipToNextTrack", groupId));
    }

    /**
     * Skip to the previous track
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/skip-to-previous-track/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to skip within
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess skipToPreviousTrack(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/skipToPreviousTrack", groupId));
    }

    /** {@inheritDoc} */
    @Override
    public SonosSuccess subscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/subscription", groupId));
    }

    /**
     * Toggle the play/pause state of a group
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playback/toggleplaypause/">Sonos docs</a>
     * @see #play(String, String)
     * @see #pause(String, String)
     * @param clientToken for the user
     * @param groupId to play/pause
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess togglePlayPause(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/togglePlayPause", groupId));
    }

    /** {@inheritDoc} */
    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playback/subscription", groupId));
    }
}
