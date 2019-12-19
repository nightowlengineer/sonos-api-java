package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;

abstract class SubscribableResource extends BaseResource implements Subscribable
{
    /**
     * Provide the path to the subscription endpoint
     *
     * @return a formattable URI which can take in a resource ID
     */
    abstract String getSubscriptionPath();

    SubscribableResource(SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /** {@inheritDoc} */
    @Override
    public SonosSuccess subscribe(final String clientToken, final String resourceId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format(getSubscriptionPath(), resourceId));
    }

    /** {@inheritDoc} */
    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String resourceId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format(getSubscriptionPath(), resourceId));
    }
}
