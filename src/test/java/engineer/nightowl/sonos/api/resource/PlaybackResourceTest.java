package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosPlayMode;
import engineer.nightowl.sonos.api.domain.SonosPlaybackStatus;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaybackResourceTest extends MockedApiTestSetup
{
    private PlaybackResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new PlaybackResource(client);
    }

    @Test
    void testGetPlaybackStatus() throws Exception
    {
        stubJsonResponse(new SonosPlaybackStatus());

        resource.getPlaybackStatus("token123", "group1");

        assertEquals("/control/api/v1/groups/group1/playback", captureRequest().getURI().getPath());
    }

    @Test
    void testGetPlaybackStatusRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getPlaybackStatus("token123", null));
    }

    @Test
    void testLoadLineIn() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.loadLineIn("token123", "group1", "device1", true);

        assertEquals("/control/api/v1/groups/group1/playback/lineIn", captureRequest().getURI().getPath());
    }

    @Test
    void testLoadLineInRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.loadLineIn("token123", null, "device1", true));
    }

    @Test
    void testPlay() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.play("token123", "group1");

        assertEquals("/control/api/v1/groups/group1/playback/play", captureRequest().getURI().getPath());
    }

    @Test
    void testPlayRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.play("token123", null));
    }

    @Test
    void testPause() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.pause("token123", "group1");

        assertEquals("/control/api/v1/groups/group1/playback/pause", captureRequest().getURI().getPath());
    }

    @Test
    void testPauseRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.pause("token123", null));
    }

    @Test
    void testSeek() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.seek("token123", "group1", "item1", 1000);

        assertEquals("/control/api/v1/groups/group1/playback/seek", captureRequest().getURI().getPath());
    }

    @Test
    void testSeekRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.seek("token123", null, "item1", 1000));
    }

    @Test
    void testSeekRejectsNullPositionMillis()
    {
        assertThrows(SonosApiClientException.class, () -> resource.seek("token123", "group1", "item1", null));
    }

    @Test
    void testSeekRelative() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.seekRelative("token123", "group1", "item1", 1000);

        assertEquals("/control/api/v1/groups/group1/playback/seekRelative", captureRequest().getURI().getPath());
    }

    @Test
    void testSeekRelativeRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.seekRelative("token123", null, "item1", 1000));
    }

    @Test
    void testSetPlayModes() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.setPlayModes("token123", "group1", new SonosPlayMode());

        assertEquals("/control/api/v1/groups/group1/playback/playMode", captureRequest().getURI().getPath());
    }

    @Test
    void testSetPlayModesRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.setPlayModes("token123", null, new SonosPlayMode()));
    }

    @Test
    void testSkipToNextTrack() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.skipToNextTrack("token123", "group1");

        assertEquals("/control/api/v1/groups/group1/playback/skipToNextTrack", captureRequest().getURI().getPath());
    }

    @Test
    void testSkipToNextTrackRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.skipToNextTrack("token123", null));
    }

    @Test
    void testSkipToPreviousTrack() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.skipToPreviousTrack("token123", "group1");

        assertEquals("/control/api/v1/groups/group1/playback/skipToPreviousTrack", captureRequest().getURI().getPath());
    }

    @Test
    void testSkipToPreviousTrackRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.skipToPreviousTrack("token123", null));
    }

    @Test
    void testTogglePlayPause() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.togglePlayPause("token123", "group1");

        assertEquals("/control/api/v1/groups/group1/playback/togglePlayPause", captureRequest().getURI().getPath());
    }

    @Test
    void testTogglePlayPauseRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.togglePlayPause("token123", null));
    }
}
