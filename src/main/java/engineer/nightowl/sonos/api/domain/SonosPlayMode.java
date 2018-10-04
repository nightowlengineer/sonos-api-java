package engineer.nightowl.sonos.api.domain;

public class SonosPlayMode
{
    private Boolean repeat;
    private Boolean repeatOne;
    private Boolean shuffle;
    private Boolean crossfade;

    public SonosPlayMode()
    {
    }

    public SonosPlayMode(final Boolean repeat, final Boolean repeatOne, final Boolean shuffle, final Boolean crossfade)
    {
        this.repeat = repeat;
        this.repeatOne = repeatOne;
        this.shuffle = shuffle;
        this.crossfade = crossfade;
    }

    public Boolean getRepeat()
    {
        return repeat;
    }

    public void setRepeat(final Boolean repeat)
    {
        this.repeat = repeat;
    }

    public Boolean getRepeatOne()
    {
        return repeatOne;
    }

    public void setRepeatOne(final Boolean repeatOne)
    {
        this.repeatOne = repeatOne;
    }

    public Boolean getShuffle()
    {
        return shuffle;
    }

    public void setShuffle(final Boolean shuffle)
    {
        this.shuffle = shuffle;
    }

    public Boolean getCrossfade()
    {
        return crossfade;
    }

    public void setCrossfade(final Boolean crossfade)
    {
        this.crossfade = crossfade;
    }

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
