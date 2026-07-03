package engineer.nightowl.sonos.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.SonosApiConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Shared Mockito-based harness for resource tests - mocks the {@link CloseableHttpClient} at the seam
 * {@link BaseResource} uses, so no real network calls are made. Subclasses get a configured
 * {@link SonosApiClient} plus helpers to stub a JSON response and capture the request that was sent.
 */
abstract class MockedApiTestSetup
{
    static final String CONTROL_BASE_URL = "api.ws.sonos.com/control/api";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    SonosApiClient client;
    SonosApiConfiguration configuration;
    CloseableHttpClient mockedClient;

    @BeforeEach
    void setUpMockedApiClient()
    {
        client = mock(SonosApiClient.class);
        configuration = mock(SonosApiConfiguration.class);
        mockedClient = mock(CloseableHttpClient.class);

        when(client.getConfiguration()).thenReturn(configuration);
        when(client.getHttpClient()).thenReturn(mockedClient);
        when(configuration.getControlBaseUrl()).thenReturn(CONTROL_BASE_URL);
    }

    /**
     * Stub the mocked HTTP client to return a 200 response with the given body serialized as JSON.
     *
     * @param body the object to serialize as the response body
     * @return the mocked response, in case further stubbing (e.g. a type header) is needed
     */
    CloseableHttpResponse stubJsonResponse(final Object body) throws IOException
    {
        final CloseableHttpResponse response = mock(CloseableHttpResponse.class);
        final HttpEntity entity = new StringEntity(OBJECT_MAPPER.writeValueAsString(body), ContentType.APPLICATION_JSON);
        when(response.getEntity()).thenReturn(entity);
        when(response.getStatusLine()).thenReturn(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, null));
        when(mockedClient.execute(any())).thenReturn(response);
        return response;
    }

    /**
     * Capture the request that was actually sent to the mocked HTTP client, so its method/URI/body can be
     * asserted on.
     *
     * @return the captured request
     */
    HttpUriRequest captureRequest() throws IOException
    {
        final ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);
        verify(mockedClient).execute(captor.capture());
        return captor.getValue();
    }

    /**
     * Read the entity content of a request (e.g. an {@link org.apache.http.client.methods.HttpPost}) as a
     * UTF-8 string.
     *
     * @param request the request to read the body of
     * @return the body content, or null if the request has no entity
     */
    static String readRequestBody(final HttpUriRequest request) throws IOException
    {
        if (!(request instanceof HttpEntityEnclosingRequestBase))
        {
            return null;
        }
        final HttpEntity entity = ((HttpEntityEnclosingRequestBase) request).getEntity();
        if (entity == null)
        {
            return null;
        }
        return new String(entity.getContent().readAllBytes());
    }
}
