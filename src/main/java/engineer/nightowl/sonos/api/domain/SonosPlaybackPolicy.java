package engineer.nightowl.sonos.api.domain;

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

    public SonosPlaybackPolicy()
    {
    }

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

    public Boolean getCanCrossfade()
    {
        return canCrossfade;
    }

    public void setCanCrossfade(final Boolean canCrossfade)
    {
        this.canCrossfade = canCrossfade;
    }

    public Boolean getCanRepeat()
    {
        return canRepeat;
    }

    public void setCanRepeat(final Boolean canRepeat)
    {
        this.canRepeat = canRepeat;
    }

    public Boolean getCanRepeatOne()
    {
        return canRepeatOne;
    }

    public void setCanRepeatOne(final Boolean canRepeatOne)
    {
        this.canRepeatOne = canRepeatOne;
    }

    public Boolean getCanResume()
    {
        return canResume;
    }

    public void setCanResume(final Boolean canResume)
    {
        this.canResume = canResume;
    }

    public Boolean getCanSeek()
    {
        return canSeek;
    }

    public void setCanSeek(final Boolean canSeek)
    {
        this.canSeek = canSeek;
    }

    public Boolean getCanShuffle()
    {
        return canShuffle;
    }

    public void setCanShuffle(final Boolean canShuffle)
    {
        this.canShuffle = canShuffle;
    }

    public Boolean getCanSkip()
    {
        return canSkip;
    }

    public void setCanSkip(final Boolean canSkip)
    {
        this.canSkip = canSkip;
    }

    public Boolean getCanSkipBack()
    {
        return canSkipBack;
    }

    public void setCanSkipBack(final Boolean canSkipBack)
    {
        this.canSkipBack = canSkipBack;
    }

    public Boolean getCanSkipToItem()
    {
        return canSkipToItem;
    }

    public void setCanSkipToItem(final Boolean canSkipToItem)
    {
        this.canSkipToItem = canSkipToItem;
    }

    public Boolean getCanSkipWhilePaused()
    {
        return canSkipWhilePaused;
    }

    public void setCanSkipWhilePaused(final Boolean canSkipWhilePaused)
    {
        this.canSkipWhilePaused = canSkipWhilePaused;
    }

    public Boolean getLimitedSkips()
    {
        return limitedSkips;
    }

    public void setLimitedSkips(final Boolean limitedSkips)
    {
        this.limitedSkips = limitedSkips;
    }

    public Boolean getNotifyUserIntent()
    {
        return notifyUserIntent;
    }

    public void setNotifyUserIntent(final Boolean notifyUserIntent)
    {
        this.notifyUserIntent = notifyUserIntent;
    }

    public Boolean getVisible()
    {
        return isVisible;
    }

    public void setVisible(final Boolean visible)
    {
        isVisible = visible;
    }

    public Integer getPauseTtlSec()
    {
        return pauseTtlSec;
    }

    public void setPauseTtlSec(final Integer pauseTtlSec)
    {
        this.pauseTtlSec = pauseTtlSec;
    }

    public Integer getPlayTtlSec()
    {
        return playTtlSec;
    }

    public void setPlayTtlSec(final Integer playTtlSec)
    {
        this.playTtlSec = playTtlSec;
    }

    public Integer getShowNNextTracks()
    {
        return showNNextTracks;
    }

    public void setShowNNextTracks(final Integer showNNextTracks)
    {
        this.showNNextTracks = showNNextTracks;
    }

    public Integer getShowNPreviousTracks()
    {
        return showNPreviousTracks;
    }

    public void setShowNPreviousTracks(final Integer showNPreviousTracks)
    {
        this.showNPreviousTracks = showNPreviousTracks;
    }

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
