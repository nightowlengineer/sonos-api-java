package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosHomeTheaterOptions;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.enums.SonosTvPowerState;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HomeTheaterResourceTest extends MockedApiTestSetup
{
    private HomeTheaterResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new HomeTheaterResource(client);
    }

    @Test
    void testLoadHomeTheaterPlayback() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.loadHomeTheaterPlayback("token123", "player1");

        final HttpUriRequest sent = captureRequest();
        assertEquals("/control/api/v1/players/player1/homeTheater", sent.getURI().getPath());
    }

    @Test
    void testLoadHomeTheaterPlaybackRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.loadHomeTheaterPlayback("token123", null));
    }

    @Test
    void testSetTvPowerState() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.setTvPowerState("token123", "player1", SonosTvPowerState.ON);

        final HttpUriRequest sent = captureRequest();
        assertEquals("/control/api/v1/players/player1/homeTheater/tvPowerState", sent.getURI().getPath());
    }

    @Test
    void testSetTvPowerStateRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.setTvPowerState("token123", null, SonosTvPowerState.ON));
    }

    @Test
    void testGetOptions() throws Exception
    {
        stubJsonResponse(new SonosHomeTheaterOptions());

        resource.getOptions("token123", "player1");

        final HttpUriRequest sent = captureRequest();
        assertEquals("/control/api/v1/players/player1/homeTheater/options", sent.getURI().getPath());
    }

    @Test
    void testGetOptionsRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getOptions("token123", null));
    }

    @Test
    void testSetOptions() throws Exception
    {
        stubJsonResponse(new SonosHomeTheaterOptions());

        resource.setOptions("token123", "player1", new SonosHomeTheaterOptions());

        final HttpUriRequest sent = captureRequest();
        assertEquals("/control/api/v1/players/player1/homeTheater/options", sent.getURI().getPath());
    }

    @Test
    void testSetOptionsRejectsNullPlayerId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.setOptions("token123", null, new SonosHomeTheaterOptions()));
    }
}
