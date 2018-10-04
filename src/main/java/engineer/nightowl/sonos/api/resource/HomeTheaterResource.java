package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.enums.SonosTvPowerState;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

import java.util.HashMap;
import java.util.Map;

/**
 * Send commands to compatible devices
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/hometheater/">Sonos docs</a>
 */
public class HomeTheaterResource extends BaseResource
{
    /**
     * <p>Constructor for HomeTheaterResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public HomeTheaterResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Set the specified player to use its optical or HDMI input.
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/hometheater/load-home-theater-playback/">Sonos docs</a>
     * @param clientToken for the user
     * @param playerId    to set to home theater mode
     * @return that the action was successful (will always be true, otherwise an exception is thrown)
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess loadHomeTheater(final String clientToken, final String playerId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/homeTheater", playerId));
    }

    /**
     * Instruct the specified player to set the attached device to the specified power state using HDMI.
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/hometheater/set-tv-power-state/">Sonos docs</a>
     * @param clientToken  for the user
     * @param playerId     to transmit the power state through
     * @param tvPowerState chosen {@link engineer.nightowl.sonos.api.enums.SonosTvPowerState}
     * @return if the client was successful or not (see Sonos docs for how this works)
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess setTvPowerState(final String clientToken, final String playerId, final SonosTvPowerState tvPowerState) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(tvPowerState);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("tvPowerState", tvPowerState);

        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/homeTheater/tvPowerState", playerId), payload);
    }
}
