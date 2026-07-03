package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosPlayerSettings;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SettingsResourceTest extends MockedApiTestSetup
{
    private SettingsResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new SettingsResource(client);
    }

    @Test
    void testGetSettings() throws Exception
    {
        stubJsonResponse(new SonosPlayerSettings());

        resource.getSettings("token123", "player1");

        assertEquals("/control/api/v1/players/player1/settings/player", captureRequest().uri().getPath());
    }

    @Test
    void testGetSettingsRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getSettings("token123", null));
    }

    @Test
    void testSetSettings() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.setSettings("token123", "player1", new SonosPlayerSettings());

        assertEquals("/control/api/v1/players/player1/settings/player", captureRequest().uri().getPath());
    }

    @Test
    void testSetSettingsRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.setSettings("token123", null, new SonosPlayerSettings()));
    }
}
