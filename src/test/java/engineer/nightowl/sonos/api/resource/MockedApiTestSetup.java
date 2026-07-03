package engineer.nightowl.sonos.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.SonosApiConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Shared Mockito-based harness for resource tests - mocks the {@link HttpClient} at the seam
 * {@link BaseResource} uses, so no real network calls are made. Subclasses get a configured
 * {@link SonosApiClient} plus helpers to stub a JSON response and capture the request that was sent.
 */
abstract class MockedApiTestSetup
{
    static final String CONTROL_BASE_URL = "api.ws.sonos.com/control/api";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    SonosApiClient client;
    SonosApiConfiguration configuration;
    HttpClient mockedClient;

    @BeforeEach
    void setUpMockedApiClient()
    {
        client = mock(SonosApiClient.class);
        configuration = mock(SonosApiConfiguration.class);
        mockedClient = mock(HttpClient.class);

        when(client.getConfiguration()).thenReturn(configuration);
        when(client.getHttpClient()).thenReturn(mockedClient);
        when(client.getUserAgent()).thenReturn("sonos-api-java-test");
        when(configuration.getControlBaseUrl()).thenReturn(CONTROL_BASE_URL);
    }

    /**
     * Stub the mocked HTTP client to return a 200 response with the given body serialized as JSON.
     *
     * @param body the object to serialize as the response body
     * @return the mocked response, in case further stubbing (e.g. a type header) is needed
     */
    @SuppressWarnings("unchecked")
    HttpResponse<byte[]> stubJsonResponse(final Object body) throws IOException, InterruptedException
    {
        final HttpResponse<byte[]> response = mock(HttpResponse.class);
        final byte[] bytes = OBJECT_MAPPER.writeValueAsString(body).getBytes(StandardCharsets.UTF_8);
        when(response.statusCode()).thenReturn(200);
        when(response.body()).thenReturn(bytes);
        // Unlike Apache's getFirstHeader() (tolerant of an unstubbed mock returning null), an unstubbed
        // headers() call here returns null by default, which would NPE inside BaseResource.getTypeFromHeader.
        when(response.headers()).thenReturn(HttpHeaders.of(Map.of(), (a, b) -> true));
        when(mockedClient.send(any(HttpRequest.class), ArgumentMatchers.<HttpResponse.BodyHandler<byte[]>>any()))
                .thenReturn(response);
        return response;
    }

    /**
     * Capture the request that was actually sent to the mocked HTTP client, so its method/URI/body can be
     * asserted on.
     *
     * @return the captured request
     */
    HttpRequest captureRequest() throws IOException, InterruptedException
    {
        final ArgumentCaptor<HttpRequest> captor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockedClient).send(captor.capture(), any());
        return captor.getValue();
    }

    /**
     * Read the body of a request (e.g. a POST built with {@code BodyPublishers.ofString(...)}) as a UTF-8
     * string. {@link HttpRequest} has no direct string/byte accessor for its body - only a reactive
     * {@link Flow.Publisher}&lt;{@link ByteBuffer}&gt; - so this drains it via a small ad-hoc subscriber.
     *
     * @param request the request to read the body of
     * @return the body content, or null if the request has no body
     */
    static String readRequestBody(final HttpRequest request)
    {
        return request.bodyPublisher()
                .map(MockedApiTestSetup::drain)
                .orElse(null);
    }

    private static String drain(final HttpRequest.BodyPublisher publisher)
    {
        final CompletableFuture<String> result = new CompletableFuture<>();
        final List<ByteBuffer> buffers = new ArrayList<>();
        publisher.subscribe(new Flow.Subscriber<>()
        {
            @Override
            public void onSubscribe(final Flow.Subscription subscription)
            {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(final ByteBuffer item)
            {
                buffers.add(item);
            }

            @Override
            public void onError(final Throwable throwable)
            {
                result.completeExceptionally(throwable);
            }

            @Override
            public void onComplete()
            {
                final int totalBytes = buffers.stream().mapToInt(ByteBuffer::remaining).sum();
                final ByteBuffer combined = ByteBuffer.allocate(totalBytes);
                buffers.forEach(combined::put);
                combined.flip();
                result.complete(StandardCharsets.UTF_8.decode(combined).toString());
            }
        });
        return result.join();
    }
}
