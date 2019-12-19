package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;

/**
 * <p>PlaybackMetadataResource class.</p>
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/playback-metadata/">Sonos docs</a>
 */
public class PlaybackMetadataResource extends SubscribableResource
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
    String getSubscriptionPath()
    {
        return "/v1/groups/%s/playbackMetadata/subscription";
    }
}
