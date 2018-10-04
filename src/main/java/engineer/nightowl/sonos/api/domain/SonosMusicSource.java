package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonCreator
    public static SonosMusicSource fromString(final String key)
    {
        return key == null ? null : SonosMusicSource.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String getKey()
    {
        return key;
    }
}
