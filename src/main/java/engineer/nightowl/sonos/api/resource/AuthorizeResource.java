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
 * <p>
 * Reference: <a href="https://developer.sonos.com/reference/authorization-api/">Authorization API</a>
 */
public class AuthorizeResource extends BaseResource
{

    public AuthorizeResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Return the URI that a user needs to visit, in order to begin the OAuth process
     *
     * @param redirectUri - the URI (registered with Sonos in the developer portal) to redirect the user to
     * @param state       - strongly recommended, but ultimately optional, state value to pass to Sonos to reduce
     *                    chance of CSRF
     * @return URI that you should send the user to
     * @throws URISyntaxException if you've passed something in that's invalid
     */
    public URI getAuthorizeCodeUri(final String redirectUri, final String state) throws URISyntaxException
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
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

        return uri.build();
    }

    /**
     * Return the URI that a user needs to visit, in order to begin the OAuth process
     *
     * @param redirectUri - the URI (registered with Sonos in the developer portal) to redirect the user to
     * @return URI that you should send the user to
     * @throws URISyntaxException if you've passed something in that's invalid
     */
    URI getAuthorizeCodeUri(final String redirectUri) throws URISyntaxException
    {
        return getAuthorizeCodeUri(redirectUri, null);
    }

    /**
     * Generate a token from an authorization code (see getAuthorizeCode methods in the same class)
     *
     * @param redirectUri   - the user-accessible web service URI that Sonos will redirect back to
     * @param authorizeCode - an authorization code obtained before calling this method
     * @return a {@link SonosToken} object containing the user's token information
     * @throws SonosApiClientException if unable to build the request due to invalid content
     */
    public SonosToken createToken(final String redirectUri, final String authorizeCode) throws SonosApiClientException, SonosApiError
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();
        final URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost(configuration.getAuthBaseUrl());
        uri.setPath("/login/v3/oauth/access");

        final BasicNameValuePair redirectUriParameter = new BasicNameValuePair("redirect_uri", redirectUri);
        final BasicNameValuePair code = new BasicNameValuePair("code", authorizeCode);
        final BasicNameValuePair grantType = new BasicNameValuePair("grant_type", "authorization_code");
        final HttpPost request = new HttpPost();

        final List<BasicNameValuePair> postParameters = new ArrayList<>();
        postParameters.add(redirectUriParameter);
        postParameters.add(code);
        postParameters.add(grantType);

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

        return callApi(request, SonosToken.class);
    }

    /**
     * Generate a token from a previously issued refresh token
     *
     * @param refreshToken - the refresh token to use
     * @return a {@link SonosToken} object containing the user's token information
     * @throws SonosApiClientException if unable to build the request due to invalid content or the call fails
     */
    public SonosToken refreshToken(final String refreshToken) throws SonosApiClientException, SonosApiError
    {
        final SonosApiConfiguration configuration = apiClient.getConfiguration();

        // Setup URI
        final URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost(configuration.getAuthBaseUrl());
        uri.setPath("/login/v3/oauth/access");

        // Setup POST contents
        final HttpPost request = new HttpPost();
        final BasicNameValuePair refreshTokenParameter = new BasicNameValuePair("refresh_token", refreshToken);
        final BasicNameValuePair grantType = new BasicNameValuePair("grant_type", "refresh_token");
        final List<BasicNameValuePair> postParameters = new ArrayList<>();
        postParameters.add(refreshTokenParameter);
        postParameters.add(grantType);

        try
        {
            request.setEntity(new UrlEncodedFormEntity(postParameters));
        } catch (final UnsupportedEncodingException e)
        {
            throw new SonosApiClientException("Unable to generate auth request content", e);
        }

        // Set authorization header
        request.setHeader(configuration.getAuthorizationHeader());

        // Execute request
        try
        {
            request.setURI(uri.build());
        } catch (final URISyntaxException e)
        {
            throw new SonosApiClientException("Invalid URI built", e);
        }

        return callApi(request, SonosToken.class);
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
