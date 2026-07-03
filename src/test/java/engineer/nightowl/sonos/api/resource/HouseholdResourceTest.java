package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosHouseholdList;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseholdResourceTest extends MockedApiTestSetup
{
    private HouseholdResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new HouseholdResource(client);
    }

    @Test
    void testGetHouseholds() throws Exception
    {
        stubJsonResponse(new SonosHouseholdList());

        resource.getHouseholds("token123");

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/households", sent.uri().getPath());
    }
}
