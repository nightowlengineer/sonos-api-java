package engineer.nightowl.sonos.api.domain;

/**
 * <p>SonosPlayMode class.</p>
 */
public class SonosPlayMode
{
    private Boolean repeat;
    private Boolean repeatOne;
    private Boolean shuffle;
    private Boolean crossfade;

    /**
     * <p>Constructor for SonosPlayMode.</p>
     */
    public SonosPlayMode()
    {
    }

    /**
     * <p>Constructor for SonosPlayMode.</p>
     *
     * @param repeat a {@link java.lang.Boolean} object.
     * @param repeatOne a {@link java.lang.Boolean} object.
     * @param shuffle a {@link java.lang.Boolean} object.
     * @param crossfade a {@link java.lang.Boolean} object.
     */
    public SonosPlayMode(final Boolean repeat, final Boolean repeatOne, final Boolean shuffle, final Boolean crossfade)
    {
        this.repeat = repeat;
        this.repeatOne = repeatOne;
        this.shuffle = shuffle;
        this.crossfade = crossfade;
    }

    /**
     * <p>Getter for the field <code>repeat</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getRepeat()
    {
        return repeat;
    }

    /**
     * <p>Setter for the field <code>repeat</code>.</p>
     *
     * @param repeat a {@link java.lang.Boolean} object.
     */
    public void setRepeat(final Boolean repeat)
    {
        this.repeat = repeat;
    }

    /**
     * <p>Getter for the field <code>repeatOne</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getRepeatOne()
    {
        return repeatOne;
    }

    /**
     * <p>Setter for the field <code>repeatOne</code>.</p>
     *
     * @param repeatOne a {@link java.lang.Boolean} object.
     */
    public void setRepeatOne(final Boolean repeatOne)
    {
        this.repeatOne = repeatOne;
    }

    /**
     * <p>Getter for the field <code>shuffle</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getShuffle()
    {
        return shuffle;
    }

    /**
     * <p>Setter for the field <code>shuffle</code>.</p>
     *
     * @param shuffle a {@link java.lang.Boolean} object.
     */
    public void setShuffle(final Boolean shuffle)
    {
        this.shuffle = shuffle;
    }

    /**
     * <p>Getter for the field <code>crossfade</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCrossfade()
    {
        return crossfade;
    }

    /**
     * <p>Setter for the field <code>crossfade</code>.</p>
     *
     * @param crossfade a {@link java.lang.Boolean} object.
     */
    public void setCrossfade(final Boolean crossfade)
    {
        this.crossfade = crossfade;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SonosPlayMode{" +
                "repeat=" + repeat +
                ", repeatOne=" + repeatOne +
                ", shuffle=" + shuffle +
                ", crossfade=" + crossfade +
                '}';
    }
}
