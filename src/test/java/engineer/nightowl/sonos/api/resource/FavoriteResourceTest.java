package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosFavoriteList;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FavoriteResourceTest extends MockedApiTestSetup
{
    private FavoriteResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new FavoriteResource(client);
    }

    @Test
    void testGetFavorites() throws Exception
    {
        stubJsonResponse(new SonosFavoriteList());

        resource.getFavorites("token123", "household1");

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/households/household1/favorites", sent.uri().getPath());
    }

    @Test
    void testGetFavoritesRejectsNullHouseholdId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getFavorites("token123", null));
    }

    @Test
    void testLoadFavorite() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        final SonosSuccess result = resource.loadFavorite("token123", "group1", "favorite1", true, null);

        assertTrue(result.getSuccess());
        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/groups/group1/favorites", sent.uri().getPath());
        assertTrue(readRequestBody(sent).contains("favorite1"));
    }

    @Test
    void testLoadFavoriteRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.loadFavorite("token123", null, "favorite1", true, null));
    }

    @Test
    void testLoadFavoriteRejectsNullFavoriteId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.loadFavorite("token123", "group1", null, true, null));
    }
}
