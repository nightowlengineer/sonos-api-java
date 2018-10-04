package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.enums.SonosTvPowerState;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

import java.util.HashMap;
import java.util.Map;

public class HomeTheaterResource extends BaseResource
{
    public HomeTheaterResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Set the specified player to use its optical or HDMI input.
     *
     * @param clientToken for the user
     * @param playerId    to set to home theater mode
     * @return that the action was successful (will always be true, otherwise an exception is thrown)
     * @throws SonosApiClientException if an error occurs during the call
     */
    public SonosSuccess loadHomeTheater(final String clientToken, final String playerId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/homeTheater", playerId));
    }

    /**
     * Instruct the specified player to set the attached device to the specified power state using HDMI.
     * <p>
     * See the <a href="https://developer.sonos.com/reference/control-api/hometheater/set-tv-power-state/">Sonos API docs</a>
     * for more information on the return value(s) of this command
     *
     * @param clientToken  for the user
     * @param playerId     to transmit the power state through
     * @param tvPowerState chosen {@link SonosTvPowerState}
     * @return if the client was successful or not (see Sonos docs for how this works)
     * @throws SonosApiClientException if an error occurs during the call
     */
    public SonosSuccess setTvPowerState(final String clientToken, final String playerId, final SonosTvPowerState tvPowerState) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(tvPowerState);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("tvPowerState", tvPowerState);

        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/players/%s/homeTheater/tvPowerState", playerId), payload);
    }
}
