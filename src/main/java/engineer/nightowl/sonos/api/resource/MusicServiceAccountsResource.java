package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosMusicServiceAccount;
import engineer.nightowl.sonos.api.domain.SonosMusicServiceAccountMatchRequest;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

/**
 * Match accounts stored within a household with those on a music service
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/musicserviceaccounts/">Sonos docs</a>
 */
public class MusicServiceAccountsResource extends BaseResource
{
    /**
     * <p>Constructor for MusicServiceAccountsResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public MusicServiceAccountsResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Match a music service account using provided information. Note: untested, as author doesn't have access to SMAPI
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/musicserviceaccounts/match/">Sonos docs</a>
     * @param clientToken for the user
     * @param householdId to match accounts with
     * @param request     containing the details to match the account
     * @return a matching {@link engineer.nightowl.sonos.api.domain.SonosMusicServiceAccount} if found
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosMusicServiceAccount match(final String clientToken, final String householdId,
                                          final SonosMusicServiceAccountMatchRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosMusicServiceAccount.class, clientToken, String.format("/v1/household/%s/musicServiceAccounts/match", householdId), request);
    }
}
