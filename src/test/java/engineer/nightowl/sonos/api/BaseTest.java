package engineer.nightowl.sonos.api;

import org.junit.BeforeClass;

public class BaseTest
{
    protected static final SonosApiConfiguration configuration = new SonosApiConfiguration();

    protected final SonosApiClient apiClient = new SonosApiClient(configuration);

    @BeforeClass
    public static void setup()
    {
        configuration.setApiKey("testApiKey");
        configuration.setApiSecret("testApiSecret");
        configuration.setApplicationId("test");
    }
}
