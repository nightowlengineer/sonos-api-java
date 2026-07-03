package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosPlayerVolume;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerVolumeResourceTest extends MockedApiTestSetup
{
    private PlayerVolumeResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new PlayerVolumeResource(client);
    }

    @Test
    void testGetVolume() throws Exception
    {
        stubJsonResponse(new SonosPlayerVolume());

        resource.getVolume("token123", "player1");

        assertEquals("/control/api/v1/players/player1/playerVolume", captureRequest().getURI().getPath());
    }

    @Test
    void testGetVolumeRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getVolume("token123", null));
    }

    @Test
    void testSetMute() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.setMute("token123", "player1", true);

        assertEquals("/control/api/v1/players/player1/playerVolume/mute", captureRequest().getURI().getPath());
    }

    @Test
    void testSetMuteRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.setMute("token123", null, true));
    }

    @Test
    void testSetRelativeVolume() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.setRelativeVolume("token123", "player1", 10, false);

        assertEquals("/control/api/v1/players/player1/playerVolume/relative", captureRequest().getURI().getPath());
    }

    @Test
    void testSetRelativeVolumeRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.setRelativeVolume("token123", null, 10, false));
    }

    @Test
    void testSetVolume() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.setVolume("token123", "player1", 50, false);

        assertEquals("/control/api/v1/players/player1/playerVolume", captureRequest().getURI().getPath());
    }

    @Test
    void testSetVolumeRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.setVolume("token123", null, 50, false));
    }
}
