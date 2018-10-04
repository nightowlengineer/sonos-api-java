package engineer.nightowl.sonos.api.domain;

/**
 * <p>SonosPlaybackPolicy class.</p>
 */
public class SonosPlaybackPolicy
{
    private Boolean canCrossfade;
    private Boolean canRepeat;
    private Boolean canRepeatOne;
    private Boolean canResume;
    private Boolean canSeek;
    private Boolean canShuffle;
    private Boolean canSkip;
    private Boolean canSkipBack;
    private Boolean canSkipToItem;
    private Boolean canSkipWhilePaused;
    private Boolean limitedSkips;
    private Boolean notifyUserIntent;
    private Boolean isVisible;
    private Integer pauseTtlSec;
    private Integer playTtlSec;
    private Integer showNNextTracks;
    private Integer showNPreviousTracks;

    /**
     * <p>Constructor for SonosPlaybackPolicy.</p>
     */
    public SonosPlaybackPolicy()
    {
    }

    /**
     * <p>Constructor for SonosPlaybackPolicy.</p>
     *
     * @param canCrossfade a {@link java.lang.Boolean} object.
     * @param canRepeat a {@link java.lang.Boolean} object.
     * @param canRepeatOne a {@link java.lang.Boolean} object.
     * @param canResume a {@link java.lang.Boolean} object.
     * @param canSeek a {@link java.lang.Boolean} object.
     * @param canShuffle a {@link java.lang.Boolean} object.
     * @param canSkip a {@link java.lang.Boolean} object.
     * @param canSkipBack a {@link java.lang.Boolean} object.
     * @param canSkipToItem a {@link java.lang.Boolean} object.
     * @param canSkipWhilePaused a {@link java.lang.Boolean} object.
     * @param limitedSkips a {@link java.lang.Boolean} object.
     * @param notifyUserIntent a {@link java.lang.Boolean} object.
     * @param isVisible a {@link java.lang.Boolean} object.
     * @param pauseTtlSec a {@link java.lang.Integer} object.
     * @param playTtlSec a {@link java.lang.Integer} object.
     * @param showNNextTracks a {@link java.lang.Integer} object.
     * @param showNPreviousTracks a {@link java.lang.Integer} object.
     */
    public SonosPlaybackPolicy(final Boolean canCrossfade, final Boolean canRepeat, final Boolean canRepeatOne, final Boolean canResume, final Boolean canSeek, final Boolean canShuffle, final Boolean canSkip, final Boolean canSkipBack, final Boolean canSkipToItem, final Boolean canSkipWhilePaused, final Boolean limitedSkips, final Boolean notifyUserIntent, final Boolean isVisible, final Integer pauseTtlSec, final Integer playTtlSec, final Integer showNNextTracks, final Integer showNPreviousTracks)
    {
        this.canCrossfade = canCrossfade;
        this.canRepeat = canRepeat;
        this.canRepeatOne = canRepeatOne;
        this.canResume = canResume;
        this.canSeek = canSeek;
        this.canShuffle = canShuffle;
        this.canSkip = canSkip;
        this.canSkipBack = canSkipBack;
        this.canSkipToItem = canSkipToItem;
        this.canSkipWhilePaused = canSkipWhilePaused;
        this.limitedSkips = limitedSkips;
        this.notifyUserIntent = notifyUserIntent;
        this.isVisible = isVisible;
        this.pauseTtlSec = pauseTtlSec;
        this.playTtlSec = playTtlSec;
        this.showNNextTracks = showNNextTracks;
        this.showNPreviousTracks = showNPreviousTracks;
    }

    /**
     * <p>Getter for the field <code>canCrossfade</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanCrossfade()
    {
        return canCrossfade;
    }

    /**
     * <p>Setter for the field <code>canCrossfade</code>.</p>
     *
     * @param canCrossfade a {@link java.lang.Boolean} object.
     */
    public void setCanCrossfade(final Boolean canCrossfade)
    {
        this.canCrossfade = canCrossfade;
    }

    /**
     * <p>Getter for the field <code>canRepeat</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanRepeat()
    {
        return canRepeat;
    }

    /**
     * <p>Setter for the field <code>canRepeat</code>.</p>
     *
     * @param canRepeat a {@link java.lang.Boolean} object.
     */
    public void setCanRepeat(final Boolean canRepeat)
    {
        this.canRepeat = canRepeat;
    }

    /**
     * <p>Getter for the field <code>canRepeatOne</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanRepeatOne()
    {
        return canRepeatOne;
    }

    /**
     * <p>Setter for the field <code>canRepeatOne</code>.</p>
     *
     * @param canRepeatOne a {@link java.lang.Boolean} object.
     */
    public void setCanRepeatOne(final Boolean canRepeatOne)
    {
        this.canRepeatOne = canRepeatOne;
    }

    /**
     * <p>Getter for the field <code>canResume</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanResume()
    {
        return canResume;
    }

    /**
     * <p>Setter for the field <code>canResume</code>.</p>
     *
     * @param canResume a {@link java.lang.Boolean} object.
     */
    public void setCanResume(final Boolean canResume)
    {
        this.canResume = canResume;
    }

    /**
     * <p>Getter for the field <code>canSeek</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanSeek()
    {
        return canSeek;
    }

    /**
     * <p>Setter for the field <code>canSeek</code>.</p>
     *
     * @param canSeek a {@link java.lang.Boolean} object.
     */
    public void setCanSeek(final Boolean canSeek)
    {
        this.canSeek = canSeek;
    }

    /**
     * <p>Getter for the field <code>canShuffle</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanShuffle()
    {
        return canShuffle;
    }

    /**
     * <p>Setter for the field <code>canShuffle</code>.</p>
     *
     * @param canShuffle a {@link java.lang.Boolean} object.
     */
    public void setCanShuffle(final Boolean canShuffle)
    {
        this.canShuffle = canShuffle;
    }

    /**
     * <p>Getter for the field <code>canSkip</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanSkip()
    {
        return canSkip;
    }

    /**
     * <p>Setter for the field <code>canSkip</code>.</p>
     *
     * @param canSkip a {@link java.lang.Boolean} object.
     */
    public void setCanSkip(final Boolean canSkip)
    {
        this.canSkip = canSkip;
    }

    /**
     * <p>Getter for the field <code>canSkipBack</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanSkipBack()
    {
        return canSkipBack;
    }

    /**
     * <p>Setter for the field <code>canSkipBack</code>.</p>
     *
     * @param canSkipBack a {@link java.lang.Boolean} object.
     */
    public void setCanSkipBack(final Boolean canSkipBack)
    {
        this.canSkipBack = canSkipBack;
    }

    /**
     * <p>Getter for the field <code>canSkipToItem</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanSkipToItem()
    {
        return canSkipToItem;
    }

    /**
     * <p>Setter for the field <code>canSkipToItem</code>.</p>
     *
     * @param canSkipToItem a {@link java.lang.Boolean} object.
     */
    public void setCanSkipToItem(final Boolean canSkipToItem)
    {
        this.canSkipToItem = canSkipToItem;
    }

    /**
     * <p>Getter for the field <code>canSkipWhilePaused</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getCanSkipWhilePaused()
    {
        return canSkipWhilePaused;
    }

    /**
     * <p>Setter for the field <code>canSkipWhilePaused</code>.</p>
     *
     * @param canSkipWhilePaused a {@link java.lang.Boolean} object.
     */
    public void setCanSkipWhilePaused(final Boolean canSkipWhilePaused)
    {
        this.canSkipWhilePaused = canSkipWhilePaused;
    }

    /**
     * <p>Getter for the field <code>limitedSkips</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getLimitedSkips()
    {
        return limitedSkips;
    }

    /**
     * <p>Setter for the field <code>limitedSkips</code>.</p>
     *
     * @param limitedSkips a {@link java.lang.Boolean} object.
     */
    public void setLimitedSkips(final Boolean limitedSkips)
    {
        this.limitedSkips = limitedSkips;
    }

    /**
     * <p>Getter for the field <code>notifyUserIntent</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getNotifyUserIntent()
    {
        return notifyUserIntent;
    }

    /**
     * <p>Setter for the field <code>notifyUserIntent</code>.</p>
     *
     * @param notifyUserIntent a {@link java.lang.Boolean} object.
     */
    public void setNotifyUserIntent(final Boolean notifyUserIntent)
    {
        this.notifyUserIntent = notifyUserIntent;
    }

    /**
     * <p>getVisible.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getVisible()
    {
        return isVisible;
    }

    /**
     * <p>setVisible.</p>
     *
     * @param visible a {@link java.lang.Boolean} object.
     */
    public void setVisible(final Boolean visible)
    {
        isVisible = visible;
    }

    /**
     * <p>Getter for the field <code>pauseTtlSec</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getPauseTtlSec()
    {
        return pauseTtlSec;
    }

    /**
     * <p>Setter for the field <code>pauseTtlSec</code>.</p>
     *
     * @param pauseTtlSec a {@link java.lang.Integer} object.
     */
    public void setPauseTtlSec(final Integer pauseTtlSec)
    {
        this.pauseTtlSec = pauseTtlSec;
    }

    /**
     * <p>Getter for the field <code>playTtlSec</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getPlayTtlSec()
    {
        return playTtlSec;
    }

    /**
     * <p>Setter for the field <code>playTtlSec</code>.</p>
     *
     * @param playTtlSec a {@link java.lang.Integer} object.
     */
    public void setPlayTtlSec(final Integer playTtlSec)
    {
        this.playTtlSec = playTtlSec;
    }

    /**
     * <p>Getter for the field <code>showNNextTracks</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getShowNNextTracks()
    {
        return showNNextTracks;
    }

    /**
     * <p>Setter for the field <code>showNNextTracks</code>.</p>
     *
     * @param showNNextTracks a {@link java.lang.Integer} object.
     */
    public void setShowNNextTracks(final Integer showNNextTracks)
    {
        this.showNNextTracks = showNNextTracks;
    }

    /**
     * <p>Getter for the field <code>showNPreviousTracks</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getShowNPreviousTracks()
    {
        return showNPreviousTracks;
    }

    /**
     * <p>Setter for the field <code>showNPreviousTracks</code>.</p>
     *
     * @param showNPreviousTracks a {@link java.lang.Integer} object.
     */
    public void setShowNPreviousTracks(final Integer showNPreviousTracks)
    {
        this.showNPreviousTracks = showNPreviousTracks;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SonosPlaybackPolicy{" +
                "canCrossfade=" + canCrossfade +
                ", canRepeat=" + canRepeat +
                ", canRepeatOne=" + canRepeatOne +
                ", canResume=" + canResume +
                ", canSeek=" + canSeek +
                ", canShuffle=" + canShuffle +
                ", canSkip=" + canSkip +
                ", canSkipBack=" + canSkipBack +
                ", canSkipToItem=" + canSkipToItem +
                ", canSkipWhilePaused=" + canSkipWhilePaused +
                ", limitedSkips=" + limitedSkips +
                ", notifyUserIntent=" + notifyUserIntent +
                ", isVisible=" + isVisible +
                ", pauseTtlSec=" + pauseTtlSec +
                ", playTtlSec=" + playTtlSec +
                ", showNNextTracks=" + showNNextTracks +
                ", showNPreviousTracks=" + showNPreviousTracks +
                '}';
    }
}
