package engineer.nightowl.sonos.api.enums;

/**
 * <p>SonosSessionErrorCode class.</p>
 */
public enum SonosAudioClipErrorCode implements SonosErrorBase
{
    ERROR_AUDIO_CLIP_DO_NOT_DISTURB("The user has enabled “do not disturb” mode, which temporarily disables audio clips (not yet implemented)."),
    ERROR_AUDIO_CLIP_ID_NOT_FOUND("The specified audio clip id was not recognized by the player. This may be because the player purged it from memory. Players purge audio clips from memory after playing them."),
    ERROR_AUDIO_CLIP_MEDIA_ERROR("The media type is not supported by Sonos."),
    ERROR_AUDIO_CLIP_CANCEL("The clip was canceled before activation. When canceled, the status of the clip moves from pending to dismissed."),
    ERROR_AUDIO_CLIP_EXPIRE("The clip expired prior to activation.");

    private String errorMessage;

    SonosAudioClipErrorCode(final String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}
