package engineer.nightowl.sonos.api.specs;

import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;

public interface Subscribable
{
    SonosSuccess subscribe(final String clientToken, final String resourceId) throws SonosApiClientException, SonosApiError;

    SonosSuccess unsubscribe(final String clientToken, final String resourceId) throws SonosApiClientException, SonosApiError;
}
