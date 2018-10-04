package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.specs.Validatable;
import engineer.nightowl.sonos.api.util.SonosUtilityHelper;

import java.util.ArrayList;
import java.util.List;

public class SonosMusicServiceAccountMatchRequest implements Validatable
{
    private String userIdHashCode;
    private String nickname;
    private String serviceId;
    private String linkCode;
    private String linkDeviceId;

    public SonosMusicServiceAccountMatchRequest()
    {
    }

    public SonosMusicServiceAccountMatchRequest(final String userIdHashCode, final String nickname, final String serviceId, final String linkCode, final String linkDeviceId)
    {
        this.userIdHashCode = userIdHashCode;
        this.nickname = nickname;
        this.serviceId = serviceId;
        this.linkCode = linkCode;
        this.linkDeviceId = linkDeviceId;
    }

    public String getUserIdHashCode()
    {
        return userIdHashCode;
    }

    public void setUserIdHashCode(final String userIdHashCode)
    {
        this.userIdHashCode = userIdHashCode;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(final String nickname)
    {
        this.nickname = nickname;
    }

    public String getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(final String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getLinkCode()
    {
        return linkCode;
    }

    public void setLinkCode(final String linkCode)
    {
        this.linkCode = linkCode;
    }

    public String getLinkDeviceId()
    {
        return linkDeviceId;
    }

    public void setLinkDeviceId(final String linkDeviceId)
    {
        this.linkDeviceId = linkDeviceId;
    }

    @Override
    public String toString()
    {
        return "SonosMusicServiceAccountMatchRequest{" +
                "userIdHashCode='" + userIdHashCode + '\'' +
                ", nickname='" + nickname + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", linkCode='" + linkCode + '\'' +
                ", linkDeviceId='" + linkDeviceId + '\'' +
                '}';
    }

    /**
     * Validate this request before sending, to avoid a needless API call.
     *
     * @throws SonosApiClientException
     */
    @Override
    public void validate() throws SonosApiClientException
    {
        final List<String> validationErrors = new ArrayList<>();
        if (SonosUtilityHelper.isEmpty(userIdHashCode))
        {
            validationErrors.add("userIdHashCode cannot be empty when creating/joining a session");
        }
        if (SonosUtilityHelper.isEmpty(nickname))
        {
            validationErrors.add("nickname cannot be empty when creating/joining a session");
        }
        if (SonosUtilityHelper.isEmpty(serviceId))
        {
            validationErrors.add("serviceId cannot be empty when creating/joining a session");
        }
        if (!validationErrors.isEmpty())
        {
            throw new SonosApiClientException(validationErrors);
        }
    }
}
