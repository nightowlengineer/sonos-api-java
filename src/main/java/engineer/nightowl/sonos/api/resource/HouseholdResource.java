package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosHousehold;
import engineer.nightowl.sonos.api.domain.SonosHouseholdList;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

/**
 * A Household represents a parent object containing a number of players/groups, and favorites.
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/households/">Sonos docs</a>
 * @see SonosHousehold
 */
public class HouseholdResource extends BaseResource
{
    /**
     * <p>Constructor for HouseholdResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public HouseholdResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Get a user's households.
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/households/">Sonos docs</a>
     * @param clientToken for the user
     * @return SonosHouseholdList containing a nested list of households.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call.
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosHouseholdList getHouseholds(final String clientToken) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosHouseholdList.class, clientToken, "/v1/households");
    }
}
