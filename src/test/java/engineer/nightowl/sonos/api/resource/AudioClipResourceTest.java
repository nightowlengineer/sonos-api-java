package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosAudioClip;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.enums.SonosPriority;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AudioClipResourceTest extends MockedApiTestSetup
{
    private AudioClipResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new AudioClipResource(client);
    }

    @Test
    void testLoadAudioClip() throws Exception
    {
        final SonosAudioClip responseClip = new SonosAudioClip();
        responseClip.setId("clip1");
        stubJsonResponse(responseClip);

        final SonosAudioClip request = new SonosAudioClip();
        request.setName("clip");
        request.setAppId("com.acme.app");
        request.setPriority(SonosPriority.LOW);

        final SonosAudioClip result = resource.loadAudioClip("token123", "player1", request);

        assertEquals("clip1", result.getId());
        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/players/player1/audioClip", sent.uri().getPath());
    }

    @Test
    void testLoadAudioClipRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.loadAudioClip("token123", null, new SonosAudioClip()));
    }

    @Test
    void testCancelAudioClip() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        final SonosSuccess result = resource.cancelAudioClip("token123", "player1", "clip1");

        assertTrue(result.getSuccess());
        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/players/player1/audioClip/clip1", sent.uri().getPath());
    }

    @Test
    void testCancelAudioClipRejectsNullClipId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.cancelAudioClip("token123", "player1", null));
    }
}
