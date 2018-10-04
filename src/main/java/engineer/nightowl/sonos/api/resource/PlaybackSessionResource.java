package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.*;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;
import engineer.nightowl.sonos.api.util.SonosUtilityHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>PlaybackSessionResource class.</p>
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/">Sonos docs</a>
 */
public class PlaybackSessionResource extends BaseResource implements Subscribable
{
    /**
     * <p>Constructor for PlaybackSessionResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public PlaybackSessionResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Create a new playback session (removing any existing playback session)
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/create-session/">Sonos docs</a>
     * @see SonosSessionRequest
     * @see #joinOrCreateSession(String, String, SonosSessionRequest)
     * @param clientToken for the user
     * @param groupId to create the session in
     * @param request a {@link engineer.nightowl.sonos.api.domain.SonosSessionRequest} object.
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosPlaybackSessionStatus} object.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosPlaybackSessionStatus createSession(final String clientToken, final String groupId, final SonosSessionRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosPlaybackSessionStatus.class, clientToken, String.format("/v1/groups/%s/playbackSession", groupId), request);
    }

    /**
     * Join an existing playback session
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/join-session/">Sonos docs</a>
     * @see #createSession(String, String, SonosSessionRequest)
     * @see #joinOrCreateSession(String, String, SonosSessionRequest)
     * @param clientToken for the user
     * @param groupId to join the session in
     * @param request of the session you're joining - appId and appContext
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosPlaybackSessionStatus} object.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosPlaybackSessionStatus joinSession(final String clientToken, final String groupId, final SonosSessionRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosPlaybackSessionStatus.class, clientToken, String.format("/v1/groups/%s/playbackSession/join", groupId), request);
    }

    /**
     * Join an existing session, or create a new one if there is no existing session
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/join-or-create-session/">Sonos docs</a>
     * @see #createSession(String, String, SonosSessionRequest)
     * @see #joinSession(String, String, SonosSessionRequest)
     * @param clientToken for the user
     * @param groupId a {@link java.lang.String} object.
     * @param request a {@link engineer.nightowl.sonos.api.domain.SonosSessionRequest} object.
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosPlaybackSessionStatus} object.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosPlaybackSessionStatus joinOrCreateSession(final String clientToken, final String groupId, final SonosSessionRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosPlaybackSessionStatus.class, clientToken, String.format("/v1/groups/%s/playbackSession/joinOrCreate", groupId), request);
    }

    /**
     * Load a cloud queue into a playback session (see docs)
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/load-cloud-queue/">Sonos docs</a>
     * @param clientToken for the user
     * @param sessionId a {@link java.lang.String} object.
     * @param request a {@link engineer.nightowl.sonos.api.domain.SonosCloudQueueRequest} object.
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess loadCloudQueue(final String clientToken, final String sessionId, final SonosCloudQueueRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/loadCloudQueue", sessionId), request);
    }

    /**
     * Load a URL as a playback session (see docs)
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/loadstreamurl/">Sonos docs</a>
     * @param clientToken for the user
     * @param sessionId a {@link java.lang.String} object.
     * @param request a {@link engineer.nightowl.sonos.api.domain.SonosStreamUrlRequest} object.
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess loadStreamUrl(final String clientToken, final String sessionId, final SonosStreamUrlRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/loadStreamUrl", sessionId), request);
    }

    /**
     * Trigger a re-fetch of items from the cloud queue server
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/refresh-cloud-queue/">Sonos docs</a>
     * @param clientToken for the user
     * @param sessionId a {@link java.lang.String} object.
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess refreshCloudQueue(final String clientToken, final String sessionId)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/refreshCloudQueue", sessionId));
    }

    /**
     * Skip to a specific item
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/skip-to-item/">Sonos docs</a>
     * @param clientToken for the user
     * @param sessionId a {@link java.lang.String} object.
     * @param request a {@link engineer.nightowl.sonos.api.domain.SonosSessionRequest} object.
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess skipToItem(final String clientToken, final String sessionId, final SonosSessionRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/skipToItem", sessionId), request);
    }

    /**
     * Seek within a track
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/seek/">Sonos docs</a>
     * @param clientToken for the user
     * @param sessionId a {@link java.lang.String} object.
     * @param itemId a {@link java.lang.String} object.
     * @param positionMillis a {@link java.lang.String} object.
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess seek(final String clientToken, final String sessionId, final String itemId, final String positionMillis)
            throws SonosApiClientException, SonosApiError
    {
        validateNotNull(itemId);
        validateNotNull(positionMillis);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("itemId", itemId);
        payload.put("positionMillis", positionMillis);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/seek", sessionId), payload);
    }

    /**
     * Seek by a relative amount (optionally target a specific track)
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/seekrelative/">Sonos docs</a>
     * @param clientToken for the user
     * @param sessionId a {@link java.lang.String} object.
     * @param itemId a {@link java.lang.String} object.
     * @param deltaMillis a {@link java.lang.String} object.
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess seekRelative(final String clientToken, final String sessionId, final String itemId, final String deltaMillis)
            throws SonosApiClientException, SonosApiError
    {
        validateNotNull(itemId);
        validateNotNull(deltaMillis);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("itemId", itemId);
        payload.put("deltaMillis", deltaMillis);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/seekRelative", sessionId), payload);
    }

    /** {@inheritDoc} */
    @Override
    public SonosSuccess subscribe(final String clientToken, final String sessionId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/subscription", sessionId));
    }

    /**
     * Suspend a named session (feature appears to still be in development)
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/playbacksession/suspend/">Sonos docs</a>
     * @param clientToken for the user
     * @param sessionId a {@link java.lang.String} object.
     * @param queueVersion a {@link java.lang.String} object.
     * @return if the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API.
     */
    public SonosSuccess suspend(final String clientToken, final String sessionId, final String queueVersion) throws
            SonosApiClientException, SonosApiError
    {
        final Map<String, Object> payload = new HashMap<>();
        if (!SonosUtilityHelper.isEmpty(queueVersion))
        {
            payload.put("queueVersion", queueVersion);
        }
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/suspend", sessionId), payload);
    }

    /** {@inheritDoc} */
    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String sessionId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/subscription", sessionId));
    }
}
