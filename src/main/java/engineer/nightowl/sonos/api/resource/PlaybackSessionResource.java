package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.*;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;
import engineer.nightowl.sonos.api.util.SonosUtilityHelper;

import java.util.HashMap;
import java.util.Map;

public class PlaybackSessionResource extends BaseResource implements Subscribable
{
    public PlaybackSessionResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    public SonosPlaybackSessionStatus createSession(final String clientToken, final String groupId, final SonosSessionRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosPlaybackSessionStatus.class, clientToken, String.format("/v1/groups/%s/playbackSession", groupId), request);
    }

    public SonosPlaybackSessionStatus joinSession(final String clientToken, final String groupId, final SonosSessionRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosPlaybackSessionStatus.class, clientToken, String.format("/v1/groups/%s/playbackSession/join", groupId), request);
    }

    public SonosPlaybackSessionStatus joinOrCreateSession(final String clientToken, final String groupId, final SonosSessionRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosPlaybackSessionStatus.class, clientToken, String.format("/v1/groups/%s/playbackSession/joinOrCreate", groupId), request);
    }

    public SonosSuccess loadCloudQueue(final String clientToken, final String sessionId, final SonosCloudQueueRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/loadCloudQueue", sessionId), request);
    }

    public SonosSuccess loadStreamUrl(final String clientToken, final String sessionId, final SonosStreamUrlRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/loadStreamUrl", sessionId), request);
    }

    public SonosSuccess refreshCloudQueue(final String clientToken, final String sessionId)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/refreshCloudQueue", sessionId));
    }

    public SonosSuccess skipToItem(final String clientToken, final String sessionId, final SonosSessionRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/skipToItem", sessionId), request);
    }

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

    @Override
    public SonosSuccess subscribe(final String clientToken, final String sessionId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/subscription", sessionId));
    }

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

    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String sessionId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/playbackSessions/%s/playbackSession/subscription", sessionId));
    }
}
