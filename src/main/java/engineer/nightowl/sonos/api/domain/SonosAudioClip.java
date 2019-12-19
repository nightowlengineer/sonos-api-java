package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosAudioClipErrorCode;
import engineer.nightowl.sonos.api.enums.SonosClipState;
import engineer.nightowl.sonos.api.enums.SonosClipType;
import engineer.nightowl.sonos.api.enums.SonosPriority;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.net.URI;

/**
 * <p>SonosAudioClip domain object</p>
 *
 * @see <a href="https://developer.sonos.com/reference/control-api/audioclip/audioclip/">Sonos docs</a>
 */
public class SonosAudioClip {
    private String id;
    private String name;
    private String appId;
    private SonosPriority priority;
    private SonosClipType clipType;
    private SonosClipState status;
    private SonosAudioClipErrorCode errorCode;
    private String httpAuthorization;
    private URI streamUrl;
    private Integer volume;

    /**
     * Default constructor
     */
    public SonosAudioClip() {
    }

    /**
     * Full args constructor
     * @param id                Sonos generates this unique identifier and assigned to the audio clip.
     * @param name              User identifiable string.
     * @param appId             This string identifies the app that created the audioClip. Companies should use their reversed Internet domain name as the identifier, similar to com.acme.app.
     * @param priority          (Optional) Clip priority. Clips are low priority by default (not yet implemented).
     * @param clipType          (Optional) Sonos plays a built-in sound when this option is provided. The default value is CHIME.
     * @param status            This field indicates the state of the audio clip, for example, if it’s active, it’s currently playing. Audio clips transition from pending (on load) to active to done. Sonos returns the state only in events.
     * @param errorCode         (Optional) Custom error code for audio clips.
     * @param streamUrl         (Optional) Sonos will play this URL when you provide one. The caller does not need to specify a CUSTOM clipType in addition to providing the streamUrl. Sonos supports only MP3 or WAV files as audio clips.
     * @param httpAuthorization (Optional) Set a string to pass in the Authorization header when fetching the streamUrl. Omit this parameter to omit the Authorization header. Sonos includes the Authorization header when the streamUrl is secure (HTTPS). Sonos supports an httpAuthorization value up to 512 bytes.
     * @param volume            (Optional) Audio Clip playback volume, between 0 and 100. There are internal upper and lower limits for the audio clip volume level in order to prevent the audio clip from being too loud or inaudible.
     *                  If the parameter is beyond those limits, Sonos automatically adjusts the audio clip volume to the lower or upper limit. The default behavior is to playback at the current player volume.
     */
    public SonosAudioClip(final String id, final String name, final String appId, final SonosPriority priority,
                          final SonosClipType clipType, final SonosClipState status, final SonosAudioClipErrorCode errorCode,
                          final URI streamUrl, final String httpAuthorization, final Integer volume) {
        this.id = id;
        this.name = name;
        this.appId = appId;
        this.priority = priority;
        this.clipType = clipType;
        this.status = status;
        this.errorCode = errorCode;
        this.streamUrl = streamUrl;
        this.httpAuthorization = httpAuthorization;
        this.volume = volume;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(final String appId) {
        this.appId = appId;
    }

    public SonosPriority getPriority() {
        return priority;
    }

    public void setPriority(final SonosPriority priority) {
        this.priority = priority;
    }

    public SonosAudioClipErrorCode getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(SonosAudioClipErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    public SonosClipType getClipType() {
        return clipType;
    }

    public void setClipType(final SonosClipType clipType) {
        this.clipType = clipType;
    }

    public URI getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(final URI streamUrl) {
        this.streamUrl = streamUrl;
    }

    public SonosClipState getStatus()
    {
        return status;
    }

    public void setStatus(SonosClipState status)
    {
        this.status = status;
    }

    public String getHttpAuthorization()
    {
        return httpAuthorization;
    }

    public void setHttpAuthorization(String httpAuthorization)
    {
        this.httpAuthorization = httpAuthorization;
    }

    public Integer getVolume()
    {
        return volume;
    }

    public void setVolume(Integer volume)
    {
        this.volume = volume;
    }

    @Override
    public String toString()
    {
        return "SonosAudioClip{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", appId='" + appId + '\'' +
                ", priority=" + priority +
                ", clipType=" + clipType +
                ", status=" + status +
                ", errorCode=" + errorCode +
                ", httpAuthorization='" + httpAuthorization + '\'' +
                ", streamUrl=" + streamUrl +
                ", volume=" + volume +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SonosAudioClip that = (SonosAudioClip) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(appId, that.appId)
                .append(priority, that.priority)
                .append(clipType, that.clipType)
                .append(status, that.status)
                .append(errorCode, that.errorCode)
                .append(httpAuthorization, that.httpAuthorization)
                .append(streamUrl, that.streamUrl)
                .append(volume, that.volume)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(appId)
                .append(priority)
                .append(clipType)
                .append(status)
                .append(errorCode)
                .append(httpAuthorization)
                .append(streamUrl)
                .append(volume)
                .toHashCode();
    }
}
