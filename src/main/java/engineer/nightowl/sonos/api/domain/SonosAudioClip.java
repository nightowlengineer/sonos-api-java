package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosClipType;
import engineer.nightowl.sonos.api.enums.SonosPriority;

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
    private URI streamUrl;

    /**
     * Default constructor
     */
    public SonosAudioClip() {
    }

    /**
     * Full args constructor
     *
     * @param id        Sonos generates this unique identifier and assigned to the audio clip.
     * @param name      User identifiable string.
     * @param appId     This string identifies the app that created the audioClip. Companies should use their reversed Internet domain name as the identifier, similar to com.acme.app.
     * @param priority  (Optional) Clip priority. Clips are low priority by default (not yet implemented).
     * @param clipType  (Optional) Sonos plays a built-in sound when this option is provided. The default value is CHIME.
     * @param streamUrl (Optional) Sonos will play this URL when you provide one. The caller does not need to specify a CUSTOM clipType in addition to providing the streamUrl. Sonos supports only MP3 or WAV files as audio clips.
     */
    public SonosAudioClip(final String id, final String name, final String appId, final SonosPriority priority,
                          final SonosClipType clipType, final URI streamUrl) {
        this.id = id;
        this.name = name;
        this.appId = appId;
        this.priority = priority;
        this.clipType = clipType;
        this.streamUrl = streamUrl;
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

    @Override
    public String toString() {
        return "SonosAudioClip{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", appId='" + appId + '\'' +
                ", priority=" + priority +
                ", clipType=" + clipType +
                ", streamUrl=" + streamUrl +
                '}';
    }
}
