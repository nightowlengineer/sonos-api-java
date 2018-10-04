package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosHousehold;
import engineer.nightowl.sonos.api.domain.SonosHouseholdList;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

/**
 * A Household represents a parent object containing a number of players/groups, and favorites.
 * <p>
 * Reference: <a href="https://developer.sonos.com/reference/control-api/households/">Household API</a>
 *
 * @see SonosHousehold
 */
public class HouseholdResource extends BaseResource
{
    public HouseholdResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Get a user's households.
     *
     * @param clientToken for the user
     * @return SonosHouseholdList containing a nested list of households.
     * @throws SonosApiClientException if an error occurs during the call.
     */
    public SonosHouseholdList getHouseholds(final String clientToken) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosHouseholdList.class, clientToken, "/v1/households");
    }
}
