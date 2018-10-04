package engineer.nightowl.sonos.api.specs;

import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

/**
 * <p>Subscribable interface.</p>
 */
public interface Subscribable
{
    /**
     * <p>Subscribe to events for this resource.</p>
     *
     * @param clientToken for the user
     * @param resourceId of the resource to subscribe to
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    SonosSuccess subscribe(final String clientToken, final String resourceId) throws SonosApiClientException, SonosApiError;

    /**
     * <p>Unsubscribe from events related to this resource.</p>
     *
     * @param clientToken for the user
     * @param resourceId of the resource to unsubscribe from
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    SonosSuccess unsubscribe(final String clientToken, final String resourceId) throws SonosApiClientException, SonosApiError;
}
