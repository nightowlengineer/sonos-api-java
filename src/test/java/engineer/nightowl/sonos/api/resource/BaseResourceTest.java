package engineer.nightowl.sonos.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.SonosApiConfiguration;
import engineer.nightowl.sonos.api.domain.SonosHomeTheaterOptions;
import engineer.nightowl.sonos.api.enums.SonosErrorCode;
import engineer.nightowl.sonos.api.enums.SonosType;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicStatusLine;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseResourceTest
{
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    private static BaseResource baseResource;
    private static SonosApiClient client;
    private static SonosApiConfiguration configuration;
    private static CloseableHttpClient mockedClient;

    @BeforeClass
    public static void setUp() throws Exception
    {
        client = mock(SonosApiClient.class);
        configuration = mock(SonosApiConfiguration.class);
        mockedClient = mock(CloseableHttpClient.class);
        baseResource = new BaseResource(client);

        when(client.getConfiguration()).thenReturn(configuration);
        when(client.getHttpClient()).thenReturn(mockedClient);
        when(configuration.getControlBaseUrl()).thenReturn("/control/api");
    }

    @Test
    public void getTypeFromHeader() throws SonosApiClientException
    {
        final CloseableHttpResponse response = mock(CloseableHttpResponse.class);
        when(response.getFirstHeader(BaseResource.SONOS_TYPE_HEADER))
                .thenReturn(new BasicHeader(BaseResource.SONOS_TYPE_HEADER, "homeTheaterOptions"));
        final SonosType type = baseResource.getTypeFromHeader(response);

        Assert.assertEquals(SonosType.homeTheaterOptions, type);
    }

    @Test
    public void getInvalidTypeFromHeader() throws SonosApiClientException
    {
        final CloseableHttpResponse response = mock(CloseableHttpResponse.class);
        when(response.getFirstHeader(BaseResource.SONOS_TYPE_HEADER))
                .thenReturn(new BasicHeader(BaseResource.SONOS_TYPE_HEADER, "unexpectedValue"));

        try
        {
            baseResource.getTypeFromHeader(response);
            Assert.fail("Did not fail as expected");
        }
        catch(SonosApiClientException sace)
        {
            Assert.assertEquals(IllegalArgumentException.class, sace.getCause().getClass());
        }
    }

    @Test
    public void testThatMainApiCallWorks() throws IOException, SonosApiClientException, SonosApiError
    {
        // Test data
        final SonosHomeTheaterOptions options = new SonosHomeTheaterOptions();
        options.setNightMode(true);
        options.setEnhanceDialog(true);
        options.setGroupingLatency(50);

        // Mocks
        final HttpGet req = baseResource.getGetRequest("token123", "some/test");
        final HttpEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(options), ContentType.APPLICATION_JSON);

        final CloseableHttpResponse mockedResponse = mock(CloseableHttpResponse.class);
        when(mockedResponse.getEntity()).thenReturn(entity);
        final StatusLine sl = new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, null);
        when(mockedResponse.getStatusLine()).thenReturn(sl);
        when(client.getHttpClient().execute(any())).thenReturn(mockedResponse);
        final SonosHomeTheaterOptions responseOptions = baseResource.getFromApi(SonosHomeTheaterOptions.class,
                "token123", "some/test");

        Assert.assertEquals(options, responseOptions);
    }

    @Test
    public void testSonosNotDeclaringTypeStillWorks() throws IOException, SonosApiClientException, SonosApiError
    {
        // Test data
        final SonosHomeTheaterOptions options = new SonosHomeTheaterOptions();
        options.setNightMode(true);
        options.setEnhanceDialog(true);
        options.setGroupingLatency(50);

        // Mocks
        final HttpGet req = baseResource.getGetRequest("token123", "some/test");
        final HttpEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(options), ContentType.APPLICATION_JSON);

        final CloseableHttpResponse mockedResponse = mock(CloseableHttpResponse.class);
        when(mockedResponse.getEntity()).thenReturn(entity);
        final StatusLine sl = new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, null);
        when(mockedResponse.getStatusLine()).thenReturn(sl);
        when(mockedResponse.getFirstHeader(BaseResource.SONOS_TYPE_HEADER))
                .thenReturn(null);
        when(client.getHttpClient().execute(any())).thenReturn(mockedResponse);
        final SonosHomeTheaterOptions responseOptions = baseResource.getFromApi(SonosHomeTheaterOptions.class,
                "token123", "some/test");

        Assert.assertEquals(options, responseOptions);
    }

    @Test
    public void testAuthErrorThrown() throws IOException, SonosApiClientException, SonosApiError
    {
        // Mocks
        final HttpGet req = baseResource.getGetRequest("token123", "some/test");

        final CloseableHttpResponse mockedResponse = mock(CloseableHttpResponse.class);
        final StatusLine sl = new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 401, null);
        when(mockedResponse.getStatusLine()).thenReturn(sl);
        when(client.getHttpClient().execute(any())).thenReturn(mockedResponse);
        try
        {
            baseResource.getFromApi(SonosHomeTheaterOptions.class, "token123", "some/test");
            Assert.fail("Did not handle error response correctly");
        }
        catch (final SonosApiClientException e)
        {
            Assert.assertEquals("Invalid token", e.getMessage());
        }
    }

    @Test
    public void testApiErrorHandledCorrectly() throws IOException, SonosApiClientException, SonosApiError
    {
        // Test data
        final SonosApiError error = new SonosApiError();
        error.setErrorCode(SonosErrorCode.ERROR_NOT_CAPABLE);
        error.setReason("Some test information");

        // Mocks
        final HttpGet req = baseResource.getGetRequest("token123", "some/test");
        final HttpEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(error), ContentType.APPLICATION_JSON);

        final CloseableHttpResponse mockedResponse = mock(CloseableHttpResponse.class);
        when(mockedResponse.getEntity()).thenReturn(entity);
        final StatusLine sl = new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 500, null);
        when(mockedResponse.getStatusLine()).thenReturn(sl);
        when(mockedResponse.getFirstHeader(BaseResource.SONOS_TYPE_HEADER))
                .thenReturn(new BasicHeader(BaseResource.SONOS_TYPE_HEADER, "globalError"));
        when(client.getHttpClient().execute(any())).thenReturn(mockedResponse);
        try
        {
            baseResource.getFromApi(SonosHomeTheaterOptions.class, "token123", "some/test");
            Assert.fail("Did not handle error response correctly");
        }
        catch (final SonosApiError e)
        {
            Assert.assertEquals(SonosErrorCode.ERROR_NOT_CAPABLE, e.getErrorCode());
        }
    }

    @Test
    public void testApiMismatchHandledCorrectly() throws IOException, SonosApiClientException, SonosApiError
    {
        // Test data
        final SonosHomeTheaterOptions options = new SonosHomeTheaterOptions();
        options.setNightMode(true);
        options.setEnhanceDialog(true);
        options.setGroupingLatency(50);

        // Mocks
        final HttpGet req = baseResource.getGetRequest("token123", "some/test");
        final HttpEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(options), ContentType.APPLICATION_JSON);

        final CloseableHttpResponse mockedResponse = mock(CloseableHttpResponse.class);
        when(mockedResponse.getEntity()).thenReturn(entity);
        final StatusLine sl = new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, null);
        when(mockedResponse.getStatusLine()).thenReturn(sl);
        when(mockedResponse.getFirstHeader(BaseResource.SONOS_TYPE_HEADER))
                .thenReturn(new BasicHeader(BaseResource.SONOS_TYPE_HEADER, "audioClip"));
        when(client.getHttpClient().execute(any())).thenReturn(mockedResponse);
        try
        {
            baseResource.getFromApi(SonosHomeTheaterOptions.class, "token123", "some/test");
            Assert.fail("Did not handle error response correctly");
        }
        catch (final SonosApiClientException e)
        {
            Assert.assertEquals("Sonos declared SonosAudioClip as the response type, but the integration requested SonosHomeTheaterOptions", e.getMessage());
        }
    }

    @Test
    public void getStandardRequest() throws SonosApiClientException
    {
        final HttpGet req = baseResource.getStandardRequest(HttpGet.class, "token123", "/some/path");
        Assert.assertEquals(HttpGet.METHOD_NAME,
                req.getMethod());

        Assert.assertEquals("Bearer token123",
                req.getFirstHeader("Authorization").getValue());

        Assert.assertEquals(
                "/control/api/some/path",
                req.getURI().getPath());
    }

    @Test
    public void testValidateNotNullNoFieldName() throws SonosApiClientException
    {
        baseResource.validateNotNull("nonEmptyString");
    }

    @Test(expected=SonosApiClientException.class)
    public void testValidateNotNullBothNulls() throws SonosApiClientException
    {
        baseResource.validateNotNull(null, null);
    }

    @Test(expected=SonosApiClientException.class)
    public void testValidateNotNullObjectNull() throws SonosApiClientException
    {
        baseResource.validateNotNull(null, "sonos-api-java");
    }

    @Test(expected=SonosApiClientException.class)
    public void testValidateNotNullEmptyObject() throws SonosApiClientException
    {
        baseResource.validateNotNull("", "sonos-api-java");
    }

    @Test
    public void testValidateNotNullNonEmptyObject() throws SonosApiClientException
    {
        baseResource.validateNotNull("nonEmptyString", "sonos-api-java");
    }
}