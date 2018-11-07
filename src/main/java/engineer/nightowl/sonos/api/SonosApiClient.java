package engineer.nightowl.sonos.api;

import engineer.nightowl.sonos.api.resource.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class SonosApiClient
{
    // Resources
    private final AudioClipResource audioClipResource;
    private final AuthorizeResource authorizeResource;
    private final FavoriteResource favoriteResource;
    private final GroupResource groupResource;
    private final GroupVolumeResource groupVolumeResource;
    private final HomeTheaterResource homeTheaterResource;
    private final HouseholdResource householdResource;
    private final MusicServiceAccountsResource musicServiceAccountsResource;
    private final PlaybackMetadataResource playbackMetadataResource;
    private final PlaybackResource playbackResource;
    private final PlaybackSessionResource playbackSessionResource;
    private final PlayerVolumeResource playerVolumeResource;

    // Internal classes
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Properties properties = new Properties();
    // Can be overridden by implementing applications
    private SonosApiConfiguration configuration;
    private CloseableHttpClient httpClient;

    /**
     * Main client for Sonos API.
     *
     * @param configuration - a {@link engineer.nightowl.sonos.api.SonosApiConfiguration} containing integration
     *                      information such as API keys
     */
    public SonosApiClient(final SonosApiConfiguration configuration)
    {
        loadProperties();
        if (logger.isInfoEnabled())
        {
            logger.info("Initialising sonos-api-java:{}", properties.getProperty("sonosapijava.version"));
        }
        this.configuration = configuration;
        httpClient = generateHttpClient();

        // Setup resources
        audioClipResource = new AudioClipResource(this);
        authorizeResource = new AuthorizeResource(this);
        favoriteResource = new FavoriteResource(this);
        groupResource = new GroupResource(this);
        groupVolumeResource = new GroupVolumeResource(this);
        homeTheaterResource = new HomeTheaterResource(this);
        householdResource = new HouseholdResource(this);
        musicServiceAccountsResource = new MusicServiceAccountsResource(this);
        playbackMetadataResource = new PlaybackMetadataResource(this);
        playbackResource = new PlaybackResource(this);
        playbackSessionResource = new PlaybackSessionResource(this);
        playerVolumeResource = new PlayerVolumeResource(this);
    }

    /**
     * Generate a default HTTP client.
     *
     * @return a default HTTP client.
     */
    private CloseableHttpClient generateHttpClient()
    {
        return HttpClientBuilder.create().build();
    }

    /**
     * Close the HTTP client.
     */
    public void closeHttpClient()
    {
        try
        {
            httpClient.close();
        } catch (final IOException ioe)
        {
            logger.warn("Unable to close HTTP client", ioe);
        }
    }

    /**
     * Get the configured HTTP client for this API client instance.
     *
     * @return the configured HTTP client
     */
    public CloseableHttpClient getHttpClient()
    {
        return httpClient;
    }

    /**
     * Set a custom HTTP client.
     *
     * @param httpClient custom client to set
     */
    public void setHttpClient(final CloseableHttpClient httpClient)
    {
        this.httpClient = httpClient;
    }

    private void loadProperties()
    {
        try
        {
            properties.load(getClass().getClassLoader().getResourceAsStream("sonosapijava.properties"));
        } catch (final IOException | NullPointerException e)
        {
            logger.error("Unable to load project.properties from classpath");
        }
    }

    /**
     * Get the configured options for this client.
     *
     * @return the SonosApiConfiguration for this client
     */
    public SonosApiConfiguration getConfiguration()
    {
        return configuration;
    }

    /**
     * Set a new {@link engineer.nightowl.sonos.api.SonosApiConfiguration}.
     *
     * @param configuration - the new {@link engineer.nightowl.sonos.api.SonosApiConfiguration}
     */
    public void setConfiguration(final SonosApiConfiguration configuration)
    {
        this.configuration = configuration;
    }

    /**
     * Schedule audio clips.
     *
     * @return the AudioClipResource
     */
    public AudioClipResource audioClip() {
        return audioClipResource;
    }

    /**
     * Authorization methods to connect a user with the Sonos API.
     *
     * @return the AuthorizationResource
     */
    public AuthorizeResource authorize()
    {
        return authorizeResource;
    }

    /**
     * Manage a household's favorites.
     *
     * @return the FavoriteResource
     */
    public FavoriteResource favorite()
    {
        return favoriteResource;
    }

    /**
     * Manage a household's groups.
     *
     * @return the GroupResource
     */
    public GroupResource group()
    {
        return groupResource;
    }

    /**
     * Manage the volume of a group
     *
     * @return the GroupVolumeResource
     */
    public GroupVolumeResource groupVolumeResource()
    {
        return groupVolumeResource;
    }

    /**
     * Manage home theater features
     *
     * @return the HomeTheaterResource
     */
    public HomeTheaterResource homeTheater()
    {
        return homeTheaterResource;
    }

    /**
     * Manage a user's household(s)
     *
     * @return the HouseholdResource
     */
    public HouseholdResource household()
    {
        return householdResource;
    }

    /**
     * Match music service accounts
     *
     * @return the MusicServiceAccountsResource
     */
    public MusicServiceAccountsResource musicServiceAccounts()
    {
        return musicServiceAccountsResource;
    }

    /**
     * Subscribe/unsubscribe to playback events
     *
     * @return the PlaybackMetadataResource
     */
    public PlaybackMetadataResource playbackMetadata()
    {
        return playbackMetadataResource;
    }

    /**
     * Manage the playback of the system.
     *
     * @return the PlaybackResource
     */
    public PlaybackResource playback()
    {
        return playbackResource;
    }

    /**
     * Manage playback sessions.
     *
     * @return the PlaybackSessionResource
     */
    public PlaybackSessionResource playbackSession()
    {
        return playbackSessionResource;
    }

    /**
     * Manage player volume.
     *
     * @return the PlayerVolumeResource
     */
    public PlayerVolumeResource playerVolume()
    {
        return playerVolumeResource;
    }
}
