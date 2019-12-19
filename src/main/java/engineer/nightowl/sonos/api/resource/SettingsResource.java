package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosPlayerSettings;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

public class SettingsResource extends BaseResource
{
    /**
     * <p>Constructor for SettingsResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public SettingsResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Get a player's settings
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/settings/getplayersettings/">Sonos docs</a>
     * @param clientToken for the user
     * @param playerId to fetch settings for
     * @return SonosPlayerSettings for the specified player
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosPlayerSettings getSettings(final String clientToken, final String playerId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosPlayerSettings.class, clientToken, String.format("/v1/players/%s/settings/player", playerId));
    }

    /**
     * Set settings for a specified player
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/settings/setplayersettings/">Sonos docs</a>
     * @param clientToken  for the user
     * @param playerId     to set the options for
     * @param sonosPlayerSettings configured {@link engineer.nightowl.sonos.api.domain.SonosPlayerSettings}
     * @return if successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess setSettings(final String clientToken, final String playerId, final SonosPlayerSettings sonosPlayerSettings) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/settings/player", playerId), sonosPlayerSettings);
    }
}
