package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosPlaylist;
import engineer.nightowl.sonos.api.domain.SonosPlaylistList;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaylistResourceTest extends MockedApiTestSetup
{
    private PlaylistResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new PlaylistResource(client);
    }

    @Test
    void testGetPlaylists() throws Exception
    {
        stubJsonResponse(new SonosPlaylistList());

        resource.getPlaylists("token123", "household1");

        assertEquals("/control/api/v1/households/household1/playlists", captureRequest().getURI().getPath());
    }

    @Test
    void testGetPlaylistsRejectsNullHouseholdId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getPlaylists("token123", null));
    }

    @Test
    void testGetPlaylist() throws Exception
    {
        stubJsonResponse(new SonosPlaylist());

        resource.getPlaylist("token123", "household1", "playlist1");

        assertEquals("/control/api/v1/households/household1/playlists/playlist1", captureRequest().getURI().getPath());
    }

    @Test
    void testGetPlaylistRejectsNullHouseholdId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getPlaylist("token123", null, "playlist1"));
    }

    @Test
    void testGetPlaylistRejectsNullPlaylistId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getPlaylist("token123", "household1", null));
    }

    @Test
    void testLoadPlaylist() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.loadPlaylist("token123", "group1", "playlist1", true, null);

        assertEquals("/control/api/v1/groups/group1/playlists", captureRequest().getURI().getPath());
    }

    @Test
    void testLoadPlaylistRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.loadPlaylist("token123", null, "playlist1", true, null));
    }

    @Test
    void testLoadPlaylistRejectsNullPlaylistId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.loadPlaylist("token123", "group1", null, true, null));
    }
}
