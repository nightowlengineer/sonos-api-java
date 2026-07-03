package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosCloudQueueRequest;
import engineer.nightowl.sonos.api.domain.SonosPlaybackSessionStatus;
import engineer.nightowl.sonos.api.domain.SonosSessionRequest;
import engineer.nightowl.sonos.api.domain.SonosStreamUrlRequest;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaybackSessionResourceTest extends MockedApiTestSetup
{
    private PlaybackSessionResource resource;
    private SonosSessionRequest sessionRequest;

    @BeforeEach
    void initResource()
    {
        resource = new PlaybackSessionResource(client);
        sessionRequest = new SonosSessionRequest(null, "appContext", "appId", null);
    }

    @Test
    void testCreateSession() throws Exception
    {
        stubJsonResponse(new SonosPlaybackSessionStatus());

        resource.createSession("token123", "group1", sessionRequest);

        assertEquals("/control/api/v1/groups/group1/playbackSession", captureRequest().uri().getPath());
    }

    @Test
    void testCreateSessionRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.createSession("token123", null, sessionRequest));
    }

    @Test
    void testJoinSession() throws Exception
    {
        stubJsonResponse(new SonosPlaybackSessionStatus());

        resource.joinSession("token123", "group1", sessionRequest);

        assertEquals("/control/api/v1/groups/group1/playbackSession/join", captureRequest().uri().getPath());
    }

    @Test
    void testJoinSessionRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.joinSession("token123", null, sessionRequest));
    }

    @Test
    void testJoinOrCreateSession() throws Exception
    {
        stubJsonResponse(new SonosPlaybackSessionStatus());

        resource.joinOrCreateSession("token123", "group1", sessionRequest);

        assertEquals("/control/api/v1/groups/group1/playbackSession/joinOrCreate", captureRequest().uri().getPath());
    }

    @Test
    void testJoinOrCreateSessionRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.joinOrCreateSession("token123", null, sessionRequest));
    }

    @Test
    void testLoadCloudQueue() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));
        final SonosCloudQueueRequest request = new SonosCloudQueueRequest();
        request.setQueueBaseUrl(URI.create("https://example.com/queue"));

        resource.loadCloudQueue("token123", "session1", request);

        assertEquals("/control/api/v1/playbackSessions/session1/playbackSession/loadCloudQueue",
                captureRequest().uri().getPath());
    }

    @Test
    void testLoadCloudQueueRejectsNullSessionId()
    {
        final SonosCloudQueueRequest request = new SonosCloudQueueRequest();
        request.setQueueBaseUrl(URI.create("https://example.com/queue"));

        assertThrows(SonosApiClientException.class, () -> resource.loadCloudQueue("token123", null, request));
    }

    @Test
    void testLoadStreamUrl() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));
        final SonosStreamUrlRequest request = new SonosStreamUrlRequest();
        request.setStreamUrl(URI.create("https://example.com/stream"));

        resource.loadStreamUrl("token123", "session1", request);

        assertEquals("/control/api/v1/playbackSessions/session1/playbackSession/loadStreamUrl",
                captureRequest().uri().getPath());
    }

    @Test
    void testLoadStreamUrlRejectsNullSessionId()
    {
        final SonosStreamUrlRequest request = new SonosStreamUrlRequest();
        request.setStreamUrl(URI.create("https://example.com/stream"));

        assertThrows(SonosApiClientException.class, () -> resource.loadStreamUrl("token123", null, request));
    }

    @Test
    void testRefreshCloudQueue() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.refreshCloudQueue("token123", "session1");

        assertEquals("/control/api/v1/playbackSessions/session1/playbackSession/refreshCloudQueue",
                captureRequest().uri().getPath());
    }

    @Test
    void testRefreshCloudQueueRejectsNullSessionId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.refreshCloudQueue("token123", null));
    }

    @Test
    void testSkipToItem() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.skipToItem("token123", "session1", sessionRequest);

        assertEquals("/control/api/v1/playbackSessions/session1/playbackSession/skipToItem",
                captureRequest().uri().getPath());
    }

    @Test
    void testSkipToItemRejectsNullSessionId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.skipToItem("token123", null, sessionRequest));
    }

    @Test
    void testSeek() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.seek("token123", "session1", "item1", 1000);

        assertEquals("/control/api/v1/playbackSessions/session1/playbackSession/seek",
                captureRequest().uri().getPath());
    }

    @Test
    void testSeekRejectsNullSessionId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.seek("token123", null, "item1", 1000));
    }

    @Test
    void testSeekRejectsNullPositionMillis()
    {
        assertThrows(SonosApiClientException.class, () -> resource.seek("token123", "session1", "item1", null));
    }

    @Test
    void testSeekRelative() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.seekRelative("token123", "session1", "item1", 1000);

        assertEquals("/control/api/v1/playbackSessions/session1/playbackSession/seekRelative",
                captureRequest().uri().getPath());
    }

    @Test
    void testSeekRelativeRejectsNullSessionId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.seekRelative("token123", null, "item1", 1000));
    }

    @Test
    void testSuspend() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.suspend("token123", "session1", "v1");

        assertEquals("/control/api/v1/playbackSessions/session1/playbackSession/suspend",
                captureRequest().uri().getPath());
    }

    @Test
    void testSuspendRejectsNullSessionId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.suspend("token123", null, "v1"));
    }
}
