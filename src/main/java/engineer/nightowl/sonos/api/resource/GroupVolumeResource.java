package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosGroupVolume;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;

import java.util.HashMap;
import java.util.Map;

/**
 * Manage the volume of groups in a household
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/group-volume/">Sonos docs</a>
 */
public class GroupVolumeResource extends BaseResource implements Subscribable
{
    /**
     * <p>Constructor for GroupVolumeResource.</p>
     *
     * @param apiClient a {@link engineer.nightowl.sonos.api.SonosApiClient} object.
     */
    public GroupVolumeResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Get the volume properties for the specified group
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/group-volume/getvolume/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId     to fetch the volume properties for
     * @return {@link engineer.nightowl.sonos.api.domain.SonosGroupVolume} for the specified {@link engineer.nightowl.sonos.api.domain.SonosGroup}
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosGroupVolume getVolume(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosGroupVolume.class, clientToken, String.format("/v1/groups/%s/groupVolume", groupId));
    }

    /**
     * Set a specific volume for a group
     *
     * <a href="https://developer.sonos.com/reference/control-api/group-volume/set-volume/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to modify the volume of
     * @param volume to set the group to
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess setVolume(final String clientToken, final String groupId, final Integer volume) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(volume);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("volume", volume);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume", groupId), payload);
    }

    /**
     * Adjust the volume of a group by the specified amount
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/group-volume/set-relative-volume/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId to set the volume for
     * @param volumeDelta to adjust the volume by
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess setRelativeVolume(final String clientToken, final String groupId, final Integer volumeDelta) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(volumeDelta);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("volumeDelta", volumeDelta);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume/relative", groupId), payload);
    }

    /**
     * Set the mute status of a group (not a toggle)
     *
     * @see <a href="https://developer.sonos.com/reference/control-api/group-volume/set-mute/">Sonos docs</a>
     * @param clientToken for the user
     * @param groupId of the group to mute/unmute
     * @param isMuted whether the group should be muted/unmuted
     * @return whether the call was successful
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if an error occurs during the call
     * @throws engineer.nightowl.sonos.api.exception.SonosApiError if there is an error from the API
     */
    public SonosSuccess setMute(final String clientToken, final String groupId, final Boolean isMuted) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(isMuted);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("muted", isMuted);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume/mute", groupId), payload);
    }

    /**
     * {@inheritDoc}
     *
     * Subscribe to volume events for a group
     */
    @Override
    public SonosSuccess subscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume/subscription", groupId));
    }

    /**
     * {@inheritDoc}
     *
     * Unsubscribe from volume events for a group
     */
    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume/subscription", groupId));
    }
}
