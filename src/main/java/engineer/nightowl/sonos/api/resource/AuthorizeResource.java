package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.SonosApiConfiguration;
import engineer.nightowl.sonos.api.domain.SonosToken;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

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
        final URIBuilder uri = new URIBuilder();
        uri.setScheme(HTTPS);
        uri.setHost(configuration.getAuthBaseUrl());
        uri.setPath("/login/v3/oauth");
        uri.setParameter("client_id", configuration.getApiKey());
        uri.setParameter("redirect_uri", redirectUri);
        // Only currently supported values by Sonos
        uri.setParameter("response_type", "code");
        uri.setParameter("scope", "playback-control-all");
        // State is optional, only include it if set.
        if (state != null)
        {
            uri.setParameter("state", state);
        }

        try
        {
            return uri.build();
        } catch (final URISyntaxException e)
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
        final List<BasicNameValuePair> postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
        postParameters.add(new BasicNameValuePair("code", authorizeCode));
        postParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));

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
        final List<BasicNameValuePair> postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("refresh_token", refreshToken));
        postParameters.add(new BasicNameValuePair("grant_type", "refresh_token"));

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
    private HttpPost buildOauthAccessRequest(final List<BasicNameValuePair> postParameters) throws SonosApiClientException
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final URIBuilder uri = new URIBuilder();
        uri.setScheme(HTTPS);
        uri.setHost(configuration.getAuthBaseUrl());
        uri.setPath("/login/v3/oauth/access");

        final HttpPost request = new HttpPost();
        try
        {
            request.setEntity(new UrlEncodedFormEntity(postParameters));
        } catch (final UnsupportedEncodingException e)
        {
            throw new SonosApiClientException("Unable to generate auth request content", e);
        }
        request.setHeader(configuration.getAuthorizationHeader());

        try
        {
            request.setURI(uri.build());
        } catch (final URISyntaxException e)
        {
            throw new SonosApiClientException("Invalid URI built", e);
        }

        return request;
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
