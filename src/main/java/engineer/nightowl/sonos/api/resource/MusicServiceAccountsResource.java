package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosMusicServiceAccount;
import engineer.nightowl.sonos.api.domain.SonosMusicServiceAccountMatchRequest;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

public class MusicServiceAccountsResource extends BaseResource
{
    public MusicServiceAccountsResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Match a music service account using provided information.
     * <p>
     * Note: untested, as author doesn't have access to SMAPI
     * <p>
     * See the <a href="https://developer.sonos.com/reference/control-api/musicserviceaccounts/match/">Sonos API docs</a>
     * for more information on how this command works
     *
     * @param clientToken for the user
     * @param householdId to match accounts with
     * @param request     containing the details to match the account
     * @return a matching {@link SonosMusicServiceAccount} if found
     * @throws SonosApiClientException if an error occurs during the call
     */
    public SonosMusicServiceAccount match(final String clientToken, final String householdId,
                                          final SonosMusicServiceAccountMatchRequest request)
            throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosMusicServiceAccount.class, clientToken, String.format("/v1/household/%s/musicServiceAccounts/match", householdId), request);
    }
}
