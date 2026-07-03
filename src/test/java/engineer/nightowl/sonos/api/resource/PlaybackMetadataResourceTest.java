package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaybackMetadataResourceTest extends MockedApiTestSetup
{
    private PlaybackMetadataResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new PlaybackMetadataResource(client);
    }

    @Test
    void testSubscribe() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.subscribe("token123", "group1");

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/groups/group1/playbackMetadata/subscription", sent.uri().getPath());
    }

    @Test
    void testSubscribeRejectsNullResourceId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.subscribe("token123", null));
    }

    @Test
    void testUnsubscribe() throws Exception
    {
        stubJsonResponse(new SonosSuccess(true));

        resource.unsubscribe("token123", "group1");

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/groups/group1/playbackMetadata/subscription", sent.uri().getPath());
    }

    @Test
    void testUnsubscribeRejectsNullResourceId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.unsubscribe("token123", null));
    }
}
