package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.SonosApiConfiguration;
import engineer.nightowl.sonos.api.domain.SonosToken;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

import java.math.BigInteger;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
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
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if you've passed something in that's invalid
     */
    public URI getAuthorizeCodeUri(final String redirectUri, final String state) throws SonosApiClientException
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final List<Map.Entry<String, String>> params = new ArrayList<>();
        params.add(Map.entry("client_id", configuration.getApiKey()));
        params.add(Map.entry("redirect_uri", redirectUri));
        // Only currently supported values by Sonos
        params.add(Map.entry("response_type", "code"));
        params.add(Map.entry("scope", "playback-control-all"));
        // State is optional, only include it if set.
        if (state != null)
        {
            params.add(Map.entry("state", state));
        }

        try
        {
            return URI.create(String.format("%s://%s/login/v3/oauth?%s", HTTPS, configuration.getAuthBaseUrl(), encodeParameters(params)));
        } catch (final IllegalArgumentException e)
        {
            throw new SonosApiClientException("Invalid URI built", e);
        }
    }

    /**
     * Return the URI that a user needs to visit, in order to begin the OAuth process
     *
     * @see <a href="https://developer.sonos.com/reference/authorization-api/create-authorization-code/">Sonos docs</a>
     * @param redirectUri - the URI (registered with Sonos in the developer portal) to redirect the user to
     * @return URI that you should send the user to
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if you've passed something in that's invalid
     */
    public URI getAuthorizeCodeUri(final String redirectUri) throws SonosApiClientException
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
        final List<Map.Entry<String, String>> postParameters = new ArrayList<>();
        postParameters.add(Map.entry("redirect_uri", redirectUri));
        postParameters.add(Map.entry("code", authorizeCode));
        postParameters.add(Map.entry("grant_type", "authorization_code"));

        return callApi(buildOauthAccessRequest(postParameters), SonosToken.class);
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
        final List<Map.Entry<String, String>> postParameters = new ArrayList<>();
        postParameters.add(Map.entry("refresh_token", refreshToken));
        postParameters.add(Map.entry("grant_type", "refresh_token"));

        return callApi(buildOauthAccessRequest(postParameters), SonosToken.class);
    }

    /**
     * Build a POST request to the shared OAuth "access" endpoint (used for both {@link #createToken} and
     * {@link #refreshToken}), authenticated via the integration's Basic auth header rather than a Bearer token.
     *
     * @param postParameters form parameters for the request body
     * @return the built request, ready to execute via {@link #callApi}
     * @throws SonosApiClientException if the request could not be built
     */
    private HttpRequest buildOauthAccessRequest(final List<Map.Entry<String, String>> postParameters) throws SonosApiClientException
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final URI uri;
        try
        {
            uri = URI.create(String.format("%s://%s/login/v3/oauth/access", HTTPS, configuration.getAuthBaseUrl()));
        } catch (final IllegalArgumentException e)
        {
            throw new SonosApiClientException("Invalid URI built", e);
        }

        return HttpRequest.newBuilder(uri)
                .setHeader("Authorization", configuration.getAuthorizationHeader())
                .setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .setHeader("User-Agent", apiClient.getUserAgent())
                .POST(HttpRequest.BodyPublishers.ofString(encodeParameters(postParameters), StandardCharsets.UTF_8))
                .build();
    }

    /**
     * URL-encode and join a list of form/query parameters as {@code key=value&key2=value2}.
     *
     * @param parameters the parameters to encode, in order
     * @return the encoded, joined parameter string
     */
    private static String encodeParameters(final List<Map.Entry<String, String>> parameters)
    {
        return parameters.stream()
                .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
                .collect(Collectors.joining("&"));
    }

    private static String encode(final String value)
    {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
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
