package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.SonosApiConfiguration;
import engineer.nightowl.sonos.api.domain.SonosToken;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Authorization flow is dependent on sending your user back to a pre-registered, and user-accessible
 * web service address.
 *
 * @see <a href="https://developer.sonos.com/reference/authorization-api/">Sonos docs</a>
 */
public class AuthorizeResource extends BaseResource
{

    private static final String HTTPS = "https";

    /**
     * <p>Constructor for AuthorizeResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public AuthorizeResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Return the URI that a user needs to visit, in order to begin the OAuth process
     *
     * @see <a href="https://developer.sonos.com/reference/authorization-api/create-authorization-code/">Sonos docs</a>
     * @param redirectUri - the URI (registered with Sonos in the developer portal) to redirect the user to
     * @param state       - strongly recommended, but ultimately optional, state value to pass to Sonos to reduce
     *                    chance of CSRF
     * @return URI that you should send the user to
     * @throws java.net.URISyntaxException if you've passed something in that's invalid
     */
    public URI getAuthorizeCodeUri(final String redirectUri, final String state) throws URISyntaxException
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final Map<String, String> params = new HashMap<>(5);
        params.put("client_id", configuration.getApiKey());
        params.put("redirect_uri", redirectUri);
        // Only currently supported values by Sonos
        params.put("response_type", "code");
        params.put("scope", "playback-control-all");
        // State is optional, only include it if set.
        if (state != null)
        {
            params.put("state", state);
        }

        final String query = params.keySet().stream().map(key -> (key + "=" + params.get(key))).collect(Collectors.joining("&"));

        return new URI(HTTPS, configuration.getAuthBaseUrl(), "/login/v3/oauth", query, null);
    }

    /**
     * Return the URI that a user needs to visit, in order to begin the OAuth process
     *
     * @see <a href="https://developer.sonos.com/reference/authorization-api/create-authorization-code/">Sonos docs</a>
     * @param redirectUri - the URI (registered with Sonos in the developer portal) to redirect the user to
     * @return URI that you should send the user to
     * @throws URISyntaxException if you've passed something in that's invalid
     */
    public URI getAuthorizeCodeUri(final String redirectUri) throws URISyntaxException
    {
        return getAuthorizeCodeUri(redirectUri, null);
    }

    /**
     * Generate a token from an authorization code (see getAuthorizeCode methods in the same class)
     *
     * @see <a href="https://developer.sonos.com/reference/authorization-api/create-token/">Sonos docs</a>
     * @param redirectUri   - the user-accessible web service URI that Sonos will redirect back to
     * @param authorizeCode - an authorization code obtained before calling this method
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosToken} object containing the user's token information
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if unable to build the request due to invalid content
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error generating the token (from Sonos)
     */
    public SonosToken createToken(final String redirectUri, final String authorizeCode) throws SonosApiClientException,
            SonosApiError
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final HttpRequest.Builder request = HttpRequest.newBuilder();
        URI uri;
        try {
            uri = new URI(HTTPS, configuration.getAuthBaseUrl(), "/login/v3/oauth/access", null, null);
        } catch (URISyntaxException e) {
            throw new SonosApiClientException("Invalid URI constructed", e);
        }

        request.setHeader("Authorization", configuration.getAuthorizationHeaderValue());
        request.uri(uri);

        final Map<String, String> params = new HashMap<>(3);
        params.put("redirect_uri", redirectUri);
        params.put("code", authorizeCode);
        params.put("grant_type", "authorization_code");

        request.POST(BodyPublishers.ofString(params.keySet().stream().map(key -> (key + "=" + params.get(key))).collect(Collectors.joining("&"))));

        return callApi(request.build(), SonosToken.class);
    }

    /**
     * Generate a token from a previously issued refresh token
     *
     * @see <a href="https://developer.sonos.com/reference/authorization-api/refresh-token/">Sonos docs</a>
     * @param refreshToken - the refresh token to use
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosToken} object containing the user's token information
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if unable to build the request due to invalid content or the call fails
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error generating the token (from Sonos)
     */
    public SonosToken refreshToken(final String refreshToken) throws SonosApiClientException, SonosApiError
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final HttpRequest.Builder request = HttpRequest.newBuilder();
        URI uri;
        try {
            uri = new URI(HTTPS, configuration.getAuthBaseUrl(), "/login/v3/oauth/access", null, null);
        } catch (URISyntaxException e) {
            throw new SonosApiClientException("Invalid URI constructed", e);
        }

        request.setHeader("Authorization", configuration.getAuthorizationHeaderValue());
        request.uri(uri);

        final Map<String, String> params = new HashMap<>(3);
        params.put("refresh_token", refreshToken);
        params.put("grant_type", "refresh_token");

        request.POST(BodyPublishers.ofString(params.keySet().stream().map(key -> (key + "=" + params.get(key))).collect(Collectors.joining("&"))));

        return callApi(request.build(), SonosToken.class);
    }

    /**
     * Noddy implementation for generating a random state value.
     *
     * @return a random string to be used to reduce CSRF
     */
    public String generateStateValue()
    {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
