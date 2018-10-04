package engineer.nightowl.sonos.api.domain;

abstract class SonosVolume extends SonosDomainObject
{
    private Integer volume;
    private Boolean muted;
    private Boolean fixed;

    SonosVolume()
    {
    }

    public SonosVolume(final Integer volume, final Boolean muted, final Boolean fixed)
    {
        this.volume = volume;
        this.muted = muted;
        this.fixed = fixed;
    }

    public Integer getVolume()
    {
        return volume;
    }

    public void setVolume(final Integer volume)
    {
        this.volume = volume;
    }

    public Boolean getMuted()
    {
        return muted;
    }

    public void setMuted(final Boolean muted)
    {
        this.muted = muted;
    }

    public Boolean getFixed()
    {
        return fixed;
    }

    public void setFixed(final Boolean fixed)
    {
        this.fixed = fixed;
    }
}
