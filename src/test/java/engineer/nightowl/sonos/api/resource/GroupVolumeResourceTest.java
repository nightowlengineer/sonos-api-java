package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosGroupVolume;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GroupVolumeResourceTest extends MockedApiTestSetup
{
    private GroupVolumeResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new GroupVolumeResource(client);
    }

    @Test
    void testGetVolume() throws Exception
    {
        stubJsonResponse(new SonosGroupVolume());

        resource.getVolume("token123", "group1");

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/groups/group1/groupVolume", sent.uri().getPath());
    }

    @Test
    void testGetVolumeRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getVolume("token123", null));
    }

    @Test
    void testSetVolume() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.setVolume("token123", "group1", 50);

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/groups/group1/groupVolume", sent.uri().getPath());
    }

    @Test
    void testSetVolumeRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.setVolume("token123", null, 50));
    }

    @Test
    void testSetRelativeVolume() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.setRelativeVolume("token123", "group1", 10);

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/groups/group1/groupVolume/relative", sent.uri().getPath());
    }

    @Test
    void testSetRelativeVolumeRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.setRelativeVolume("token123", null, 10));
    }

    @Test
    void testSetMute() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.setMute("token123", "group1", true);

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/groups/group1/groupVolume/mute", sent.uri().getPath());
    }

    @Test
    void testSetMuteRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.setMute("token123", null, true));
    }
}
