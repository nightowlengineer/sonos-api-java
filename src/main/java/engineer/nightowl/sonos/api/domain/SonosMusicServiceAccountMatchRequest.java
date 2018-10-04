package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.specs.Validatable;
import engineer.nightowl.sonos.api.util.SonosUtilityHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>SonosMusicServiceAccountMatchRequest class.</p>
 */
public class SonosMusicServiceAccountMatchRequest implements Validatable
{
    private String userIdHashCode;
    private String nickname;
    private String serviceId;
    private String linkCode;
    private String linkDeviceId;

    /**
     * <p>Constructor for SonosMusicServiceAccountMatchRequest.</p>
     */
    public SonosMusicServiceAccountMatchRequest()
    {
    }

    /**
     * <p>Constructor for SonosMusicServiceAccountMatchRequest.</p>
     *
     * @param userIdHashCode a {@link java.lang.String} object.
     * @param nickname a {@link java.lang.String} object.
     * @param serviceId a {@link java.lang.String} object.
     * @param linkCode a {@link java.lang.String} object.
     * @param linkDeviceId a {@link java.lang.String} object.
     */
    public SonosMusicServiceAccountMatchRequest(final String userIdHashCode, final String nickname, final String serviceId, final String linkCode, final String linkDeviceId)
    {
        this.userIdHashCode = userIdHashCode;
        this.nickname = nickname;
        this.serviceId = serviceId;
        this.linkCode = linkCode;
        this.linkDeviceId = linkDeviceId;
    }

    /**
     * <p>Getter for the field <code>userIdHashCode</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUserIdHashCode()
    {
        return userIdHashCode;
    }

    /**
     * <p>Setter for the field <code>userIdHashCode</code>.</p>
     *
     * @param userIdHashCode a {@link java.lang.String} object.
     */
    public void setUserIdHashCode(final String userIdHashCode)
    {
        this.userIdHashCode = userIdHashCode;
    }

    /**
     * <p>Getter for the field <code>nickname</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNickname()
    {
        return nickname;
    }

    /**
     * <p>Setter for the field <code>nickname</code>.</p>
     *
     * @param nickname a {@link java.lang.String} object.
     */
    public void setNickname(final String nickname)
    {
        this.nickname = nickname;
    }

    /**
     * <p>Getter for the field <code>serviceId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getServiceId()
    {
        return serviceId;
    }

    /**
     * <p>Setter for the field <code>serviceId</code>.</p>
     *
     * @param serviceId a {@link java.lang.String} object.
     */
    public void setServiceId(final String serviceId)
    {
        this.serviceId = serviceId;
    }

    /**
     * <p>Getter for the field <code>linkCode</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLinkCode()
    {
        return linkCode;
    }

    /**
     * <p>Setter for the field <code>linkCode</code>.</p>
     *
     * @param linkCode a {@link java.lang.String} object.
     */
    public void setLinkCode(final String linkCode)
    {
        this.linkCode = linkCode;
    }

    /**
     * <p>Getter for the field <code>linkDeviceId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLinkDeviceId()
    {
        return linkDeviceId;
    }

    /**
     * <p>Setter for the field <code>linkDeviceId</code>.</p>
     *
     * @param linkDeviceId a {@link java.lang.String} object.
     */
    public void setLinkDeviceId(final String linkDeviceId)
    {
        this.linkDeviceId = linkDeviceId;
    }

    /** {@inheritDoc} */
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
     * {@inheritDoc}
     *
     * Validate this request before sending, to avoid a needless API call.
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
