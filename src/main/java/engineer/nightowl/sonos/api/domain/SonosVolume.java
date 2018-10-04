package engineer.nightowl.sonos.api.domain;

abstract class SonosVolume extends SonosDomainObject
{
    private Integer volume;
    private Boolean muted;
    private Boolean fixed;

    SonosVolume()
    {
    }

    /**
     * <p>Constructor for SonosVolume.</p>
     *
     * @param volume a {@link java.lang.Integer} object.
     * @param muted a {@link java.lang.Boolean} object.
     * @param fixed a {@link java.lang.Boolean} object.
     */
    public SonosVolume(final Integer volume, final Boolean muted, final Boolean fixed)
    {
        this.volume = volume;
        this.muted = muted;
        this.fixed = fixed;
    }

    /**
     * <p>Getter for the field <code>volume</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getVolume()
    {
        return volume;
    }

    /**
     * <p>Setter for the field <code>volume</code>.</p>
     *
     * @param volume a {@link java.lang.Integer} object.
     */
    public void setVolume(final Integer volume)
    {
        this.volume = volume;
    }

    /**
     * <p>Getter for the field <code>muted</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getMuted()
    {
        return muted;
    }

    /**
     * <p>Setter for the field <code>muted</code>.</p>
     *
     * @param muted a {@link java.lang.Boolean} object.
     */
    public void setMuted(final Boolean muted)
    {
        this.muted = muted;
    }

    /**
     * <p>Getter for the field <code>fixed</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getFixed()
    {
        return fixed;
    }

    /**
     * <p>Setter for the field <code>fixed</code>.</p>
     *
     * @param fixed a {@link java.lang.Boolean} object.
     */
    public void setFixed(final Boolean fixed)
    {
        this.fixed = fixed;
    }
}
