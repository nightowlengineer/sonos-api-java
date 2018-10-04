package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosType;

public class SonosMusicServiceAccount extends SonosDomainObject
{
    private String userIdHashCode;
    private String nickname;
    private String id;
    private Boolean isGuest;
    private SonosService service;

    public SonosMusicServiceAccount()
    {
    }

    public SonosMusicServiceAccount(final String userIdHashCode, final String nickname, final String id, final Boolean isGuest, final SonosService service)
    {
        this.userIdHashCode = userIdHashCode;
        this.nickname = nickname;
        this.id = id;
        this.isGuest = isGuest;
        this.service = service;
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

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public Boolean getGuest()
    {
        return isGuest;
    }

    public void setGuest(final Boolean guest)
    {
        isGuest = guest;
    }

    public SonosService getService()
    {
        return service;
    }

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
