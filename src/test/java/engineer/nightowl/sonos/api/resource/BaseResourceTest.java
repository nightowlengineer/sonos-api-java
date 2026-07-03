package engineer.nightowl.sonos.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.SonosApiConfiguration;
import engineer.nightowl.sonos.api.domain.SonosHomeTheaterOptions;
import engineer.nightowl.sonos.api.enums.SonosErrorCode;
import engineer.nightowl.sonos.api.enums.SonosType;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseResourceTest
{
    private static BaseResource baseResource;
    private static SonosApiClient client;
    private static SonosApiConfiguration configuration;
    private static HttpClient mockedClient;

    @BeforeAll
    public static void setUp() throws Exception
    {
        client = mock(SonosApiClient.class);
        configuration = mock(SonosApiConfiguration.class);
        mockedClient = mock(HttpClient.class);
        baseResource = new BaseResource(client);

        when(client.getConfiguration()).thenReturn(configuration);
        when(client.getHttpClient()).thenReturn(mockedClient);
        when(client.getUserAgent()).thenReturn("sonos-api-java-test");
        when(configuration.getControlBaseUrl()).thenReturn("api.example.com/control/api");
    }

    private static HttpHeaders headersOf(final String name, final String value)
    {
        return HttpHeaders.of(Map.of(name, List.of(value)), (a, b) -> true);
    }

    @Test
    void getTypeFromHeader() throws SonosApiClientException
    {
        final HttpResponse<?> response = mock(HttpResponse.class);
        when(response.headers()).thenReturn(headersOf(BaseResource.SONOS_TYPE_HEADER, "homeTheaterOptions"));
        final SonosType type = baseResource.getTypeFromHeader(response);

        assertEquals(SonosType.homeTheaterOptions, type);
    }

    @Test
    void getInvalidTypeFromHeader() throws SonosApiClientException
    {
        final HttpResponse<?> response = mock(HttpResponse.class);
        when(response.headers()).thenReturn(headersOf(BaseResource.SONOS_TYPE_HEADER, "unexpectedValue"));

        try
        {
            baseResource.getTypeFromHeader(response);
            fail("Did not fail as expected");
        }
        catch(SonosApiClientException sace)
        {
            assertEquals(IllegalArgumentException.class, sace.getCause().getClass());
        }
    }

    @Test
    void testThatMainApiCallWorks() throws Exception
    {
        // Test data
        final SonosHomeTheaterOptions options = new SonosHomeTheaterOptions();
        options.setNightMode(true);
        options.setEnhanceDialog(true);
        options.setGroupingLatency(50);

        // Mocks
        final byte[] bytes = new ObjectMapper().writeValueAsString(options).getBytes(StandardCharsets.UTF_8);

        final HttpResponse<byte[]> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.body()).thenReturn(bytes);
        when(mockedResponse.statusCode()).thenReturn(200);
        when(mockedResponse.headers()).thenReturn(HttpHeaders.of(Map.of(), (a, b) -> true));
        when(mockedClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<byte[]>>any())).thenReturn(mockedResponse);
        final SonosHomeTheaterOptions responseOptions = baseResource.getFromApi(SonosHomeTheaterOptions.class,
                "token123", "some/test");

        assertEquals(options, responseOptions);
        // No response.close() verification here: java.net.http.HttpResponse has no close()/Closeable
        // contract - with BodyHandlers.ofByteArray() the body is fully buffered before send() returns, so
        // there is no connection/stream left open to leak. This test (added in 8a11601, "Verify response
        // is closed after successful and 401 calls") has nothing left to assert and is intentionally not
        // ported as part of the java.net.http migration.
    }

    @Test
    void testSonosNotDeclaringTypeStillWorks() throws Exception
    {
        // Test data
        final SonosHomeTheaterOptions options = new SonosHomeTheaterOptions();
        options.setNightMode(true);
        options.setEnhanceDialog(true);
        options.setGroupingLatency(50);

        // Mocks
        final byte[] bytes = new ObjectMapper().writeValueAsString(options).getBytes(StandardCharsets.UTF_8);

        final HttpResponse<byte[]> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.body()).thenReturn(bytes);
        when(mockedResponse.statusCode()).thenReturn(200);
        when(mockedResponse.headers()).thenReturn(HttpHeaders.of(Map.of(), (a, b) -> true));
        when(mockedClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<byte[]>>any())).thenReturn(mockedResponse);
        final SonosHomeTheaterOptions responseOptions = baseResource.getFromApi(SonosHomeTheaterOptions.class,
                "token123", "some/test");

        assertEquals(options, responseOptions);
    }

    @Test
    void testAuthErrorThrown() throws Exception
    {
        final HttpResponse<byte[]> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.statusCode()).thenReturn(401);
        when(mockedClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<byte[]>>any())).thenReturn(mockedResponse);
        try
        {
            baseResource.getFromApi(SonosHomeTheaterOptions.class, "token123", "some/test");
            fail("Did not handle error response correctly");
        }
        catch (final SonosApiClientException e)
        {
            assertEquals("Invalid token", e.getMessage());
        }
        // See testThatMainApiCallWorks() - no response.close() verification needed anymore.
    }

    @Test
    void testApiErrorHandledCorrectly() throws Exception
    {
        // Test data
        final SonosApiError error = new SonosApiError();
        error.setErrorCode(SonosErrorCode.ERROR_NOT_CAPABLE);
        error.setReason("Some test information");

        final byte[] bytes = new ObjectMapper().writeValueAsString(error).getBytes(StandardCharsets.UTF_8);

        final HttpResponse<byte[]> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.body()).thenReturn(bytes);
        when(mockedResponse.statusCode()).thenReturn(500);
        when(mockedResponse.headers()).thenReturn(headersOf(BaseResource.SONOS_TYPE_HEADER, "globalError"));
        when(mockedClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<byte[]>>any())).thenReturn(mockedResponse);
        try
        {
            baseResource.getFromApi(SonosHomeTheaterOptions.class, "token123", "some/test");
            fail("Did not handle error response correctly");
        }
        catch (final SonosApiError e)
        {
            assertEquals(SonosErrorCode.ERROR_NOT_CAPABLE, e.getErrorCode());
        }
    }

    @Test
    void testApiMismatchHandledCorrectly() throws Exception
    {
        // Test data
        final SonosHomeTheaterOptions options = new SonosHomeTheaterOptions();
        options.setNightMode(true);
        options.setEnhanceDialog(true);
        options.setGroupingLatency(50);

        final byte[] bytes = new ObjectMapper().writeValueAsString(options).getBytes(StandardCharsets.UTF_8);

        final HttpResponse<byte[]> mockedResponse = mock(HttpResponse.class);
        when(mockedResponse.body()).thenReturn(bytes);
        when(mockedResponse.statusCode()).thenReturn(200);
        when(mockedResponse.headers()).thenReturn(headersOf(BaseResource.SONOS_TYPE_HEADER, "audioClip"));
        when(mockedClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<byte[]>>any())).thenReturn(mockedResponse);
        try
        {
            baseResource.getFromApi(SonosHomeTheaterOptions.class, "token123", "some/test");
            fail("Did not handle error response correctly");
        }
        catch (final SonosApiClientException e)
        {
            assertEquals("Sonos declared SonosAudioClip as the response type, but the integration requested SonosHomeTheaterOptions", e.getMessage());
        }
    }

    @Test
    void getStandardRequest() throws SonosApiClientException
    {
        final HttpRequest req = baseResource.getStandardRequest("token123", "/some/path").GET().build();
        assertEquals("GET", req.method());

        assertEquals("Bearer token123", req.headers().firstValue("Authorization").orElse(null));

        assertEquals("/control/api/some/path", req.uri().getPath());
    }

    @Test
    void testValidateNotNullNoFieldName() throws SonosApiClientException
    {
        baseResource.validateNotNull("nonEmptyString");
    }

    @Test
    void testValidateNotNullBothNulls()
    {
        assertThrows(SonosApiClientException.class, () -> baseResource.validateNotNull(null, null));
    }

    @Test
    void testValidateNotNullObjectNull()
    {
        assertThrows(SonosApiClientException.class, () -> baseResource.validateNotNull(null, "sonos-api-java"));
    }

    @Test
    void testValidateNotNullEmptyObject()
    {
        assertThrows(SonosApiClientException.class, () -> baseResource.validateNotNull("", "sonos-api-java"));
    }

    @Test
    void testValidateNotNullNonEmptyObject() throws SonosApiClientException
    {
        baseResource.validateNotNull("nonEmptyString", "sonos-api-java");
    }
}
