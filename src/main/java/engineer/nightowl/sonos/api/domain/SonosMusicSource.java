package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>SonosMusicSource class.</p>
 */
public enum SonosMusicSource
{
    ALBUM("album"), ARTIST("artist"), AUDIOBOOK("audiobook"), CONTAINER("container"), EPISODE("episode"), ITEM("item"),
    LINEIN("linein"), PLAYLIST("playlist"), SHOW("show"), STATION("station"), STATION_BROADCAST("station.broadcast"),
    TRACK("track"), TRACKLIST("tracklist");

    private final String key;

    SonosMusicSource(final String key)
    {
        this.key = key;
    }

    /**
     * <p>fromString.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @return a {@link engineer.nightowl.sonos.api.domain.SonosMusicSource} object.
     */
    @JsonCreator
    public static SonosMusicSource fromString(final String key)
    {
        return key == null ? null : SonosMusicSource.valueOf(key.toUpperCase());
    }

    /**
     * <p>Getter for the field <code>key</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @JsonValue
    public String getKey()
    {
        return key;
    }
}
