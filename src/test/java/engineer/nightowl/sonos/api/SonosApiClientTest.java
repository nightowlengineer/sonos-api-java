package engineer.nightowl.sonos.api;

import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class SonosApiClientTest
{
    private static SonosApiConfiguration testConfiguration()
    {
        final SonosApiConfiguration configuration = new SonosApiConfiguration();
        configuration.setApiKey("testApiKey");
        configuration.setApiSecret("testApiSecret");
        configuration.setApplicationId("test");
        return configuration;
    }

    @Test
    void closeDoesNotCloseCallerSuppliedClient()
    {
        final HttpClient suppliedClient = mock(HttpClient.class);
        final SonosApiClient client = new SonosApiClient(testConfiguration(), suppliedClient);

        client.close();

        verify(suppliedClient, never()).close();
    }

    @Test
    void closeDoesNotCloseClientSuppliedViaSetter()
    {
        final SonosApiClient client = new SonosApiClient(testConfiguration());
        final HttpClient suppliedClient = mock(HttpClient.class);
        client.setHttpClient(suppliedClient);

        client.close();

        verify(suppliedClient, never()).close();
    }
}
