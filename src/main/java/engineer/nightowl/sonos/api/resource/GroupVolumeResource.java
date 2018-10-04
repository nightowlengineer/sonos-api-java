package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.SonosApiClient;
import engineer.nightowl.sonos.api.domain.SonosGroup;
import engineer.nightowl.sonos.api.domain.SonosGroupVolume;
import engineer.nightowl.sonos.api.domain.SonosSuccess;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.exception.SonosApiError;
import engineer.nightowl.sonos.api.specs.Subscribable;

import java.util.HashMap;
import java.util.Map;

public class GroupVolumeResource extends BaseResource implements Subscribable
{
    public GroupVolumeResource(final SonosApiClient apiClient)
    {
        super(apiClient);
    }

    /**
     * Get the volume properties for the specified group
     *
     * @param clientToken for the user
     * @param groupId     to fetch the volume properties for
     * @return {@link SonosGroupVolume} for the specified {@link SonosGroup}
     */
    public SonosGroupVolume getVolume(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return getFromApi(SonosGroupVolume.class, clientToken, String.format("/v1/groups/%s/groupVolume", groupId));
    }

    public SonosSuccess setVolume(final String clientToken, final String groupId, final Integer volume) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(volume);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("volume", volume);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume", groupId), payload);
    }

    public SonosSuccess setRelativeVolume(final String clientToken, final String groupId, final Integer volumeDelta) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(volumeDelta);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("volumeDelta", volumeDelta);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume/relative", groupId), payload);
    }

    public SonosSuccess setMute(final String clientToken, final String groupId, final Boolean isMuted) throws SonosApiClientException, SonosApiError
    {
        validateNotNull(isMuted);
        final Map<String, Object> payload = new HashMap<>();
        payload.put("muted", isMuted);
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume/mute", groupId), payload);
    }

    @Override
    public SonosSuccess subscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return postToApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume/subscription", groupId));
    }

    @Override
    public SonosSuccess unsubscribe(final String clientToken, final String groupId) throws SonosApiClientException, SonosApiError
    {
        return deleteFromApi(SonosSuccess.class, clientToken, String.format("/v1/groups/%s/groupVolume/subscription", groupId));
    }
}
