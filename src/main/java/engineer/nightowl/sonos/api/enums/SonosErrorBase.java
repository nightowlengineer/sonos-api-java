package engineer.nightowl.sonos.api.enums;

/**
 * <p>Marker interface implemented by every Sonos error-code enum ({@link SonosErrorCode},
 * {@link SonosPlaybackErrorCode}, {@link SonosSessionErrorCode}, {@link SonosAudioClipErrorCode}),
 * allowing {@link engineer.nightowl.sonos.api.exception.SonosApiError} subclasses to expose a
 * covariant, correctly-typed {@code errorCode} accessor.</p>
 */
public interface SonosErrorBase
{
}
