package engineer.nightowl.sonos.api.enums;

import engineer.nightowl.sonos.api.domain.*;
import engineer.nightowl.sonos.api.exception.SonosApiError;

import java.util.Arrays;
import java.util.List;

/**
 * Enumerates and maps the possible return types from Sonos.
 */
public enum SonosType
{
    favoritesList(SonosFavoriteList.class),
    globalError(SonosApiError.class),
    groups(SonosGroups.class),
    groupVolume(SonosGroupVolume.class),
    households(SonosHouseholdList.class),
    MusicServiceAccount(SonosMusicServiceAccount.class),
    playbackError(SonosPlaybackError.class),
    playbackStatus(SonosPlaybackStatus.class),
    metadataStatus(SonosMetadataStatus.class),
    sessionError(SonosSessionError.class),
    sessionStatus(SonosPlaybackSessionStatus.class),
    playerVolume(SonosPlayerVolume.class);

    private final Class clazz;

    SonosType(final Class clazz)
    {
        this.clazz = clazz;
    }

    public static List<SonosType> getErrorTypes()
    {
        return Arrays.asList(globalError, playbackError, sessionError);
    }

    public Class getClazz()
    {
        return clazz;
    }
}
