package engineer.nightowl.sonos.api.enums;

public enum SonosClipState
{
    ACTIVE("Currently playing"),
    DISMISSED("Dismissed"),
    DONE("Playback complete"),
    INTERRUPTED("Playback interrupted, for example, by a high priority audio clip"),
    PENDING("Scheduled for playback, but not active");

    private String extraInfo;

    SonosClipState(final String extraInfo)
    {
        this.extraInfo = extraInfo;
    }

    public String getExtraInfo()
    {
        return extraInfo;
    }
}
