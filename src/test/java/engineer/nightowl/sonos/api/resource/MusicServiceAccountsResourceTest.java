package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosMusicServiceAccount;
import engineer.nightowl.sonos.api.domain.SonosMusicServiceAccountMatchRequest;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MusicServiceAccountsResourceTest extends MockedApiTestSetup
{
    private MusicServiceAccountsResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new MusicServiceAccountsResource(client);
    }

    @Test
    void testMatch() throws Exception
    {
        stubJsonResponse(new SonosMusicServiceAccount());
        final SonosMusicServiceAccountMatchRequest request =
                new SonosMusicServiceAccountMatchRequest("hash1", "nickname", "service1", null, null);

        resource.match("token123", "household1", request);

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/households/household1/musicServiceAccounts/match", sent.uri().getPath());
    }

    @Test
    void testMatchRejectsNullHouseholdId()
    {
        final SonosMusicServiceAccountMatchRequest request =
                new SonosMusicServiceAccountMatchRequest("hash1", "nickname", "service1", null, null);

        assertThrows(SonosApiClientException.class, () -> resource.match("token123", null, request));
    }
}
