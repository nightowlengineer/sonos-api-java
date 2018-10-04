package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosType;

/**
 * <p>SonosMusicServiceAccount class.</p>
 */
public class SonosMusicServiceAccount extends SonosDomainObject
{
    private String userIdHashCode;
    private String nickname;
    private String id;
    private Boolean isGuest;
    private SonosService service;

    /**
     * <p>Constructor for SonosMusicServiceAccount.</p>
     */
    public SonosMusicServiceAccount()
    {
    }

    /**
     * <p>Constructor for SonosMusicServiceAccount.</p>
     *
     * @param userIdHashCode a {@link java.lang.String} object.
     * @param nickname a {@link java.lang.String} object.
     * @param id a {@link java.lang.String} object.
     * @param isGuest a {@link java.lang.Boolean} object.
     * @param service a {@link engineer.nightowl.sonos.api.domain.SonosService} object.
     */
    public SonosMusicServiceAccount(final String userIdHashCode, final String nickname, final String id, final Boolean isGuest, final SonosService service)
    {
        this.userIdHashCode = userIdHashCode;
        this.nickname = nickname;
        this.id = id;
        this.isGuest = isGuest;
        this.service = service;
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
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getId()
    {
        return id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id a {@link java.lang.String} object.
     */
    public void setId(final String id)
    {
        this.id = id;
    }

    /**
     * <p>getGuest.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getGuest()
    {
        return isGuest;
    }

    /**
     * <p>setGuest.</p>
     *
     * @param guest a {@link java.lang.Boolean} object.
     */
    public void setGuest(final Boolean guest)
    {
        isGuest = guest;
    }

    /**
     * <p>Getter for the field <code>service</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosService} object.
     */
    public SonosService getService()
    {
        return service;
    }

    /**
     * <p>Setter for the field <code>service</code>.</p>
     *
     * @param service a {@link engineer.nightowl.sonos.api.domain.SonosService} object.
     */
    public void setService(final SonosService service)
    {
        this.service = service;
    }

    @Override
    SonosType getSonosType()
    {
        return SonosType.MusicServiceAccount;
    }
}
