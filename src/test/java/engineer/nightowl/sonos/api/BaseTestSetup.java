package engineer.nightowl.sonos.api;

import org.junit.jupiter.api.BeforeAll;

public class BaseTestSetup
{
    protected static final SonosApiConfiguration configuration = new SonosApiConfiguration();

    protected final SonosApiClient apiClient = new SonosApiClient(configuration);

    @BeforeAll
    static void setup()
    {
        configuration.setApiKey("testApiKey");
        configuration.setApiSecret("testApiSecret");
        configuration.setApplicationId("test");
    }
}
