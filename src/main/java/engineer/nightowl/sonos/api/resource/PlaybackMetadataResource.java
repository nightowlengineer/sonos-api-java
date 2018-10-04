package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;

/**
 * <p>PlaybackMetadataResource class.</p>
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/playback-metadata/">Sonos docs</a>
 */
public class PlaybackMetadataResource extends BaseResource implements Subscribable
{
    /**
     * <p>Constructor for PlaybackMetadataResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public PlaybackMetadataResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /** {@inheritDoc} */
    @Override
    public SonosSuccess subscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playbackMetadata/subscription", groupId));
    }

    /** {@inheritDoc} */
    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/playbackMetadata/subscription", groupId));
    }
}
