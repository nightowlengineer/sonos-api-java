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
    audioClip(SonosAudioClip.class),
    favoritesList(SonosFavoriteList.class),
    globalError(SonosApiError.class),
    groups(SonosGroups.class),
    groupInfo(SonosGroupInfo.class),
    groupVolume(SonosGroupVolume.class),
    homeTheaterOptions(SonosHomeTheaterOptions.class),
    households(SonosHouseholdList.class),
    // Deliberately UpperCamelCase, unlike its siblings above - this is the exact literal value Sonos
    // documents for the X-Sonos-Type header (see docs.sonos.com/docs/types), not a naming inconsistency.
    MusicServiceAccount(SonosMusicServiceAccount.class),
    playbackError(SonosPlaybackError.class),
    playbackStatus(SonosPlaybackStatus.class),
    metadataStatus(SonosMetadataStatus.class),
    sessionError(SonosSessionError.class),
    sessionStatus(SonosPlaybackSessionStatus.class),
    playerVolume(SonosPlayerVolume.class);

    private final Class<?> clazz;

    SonosType(final Class<?> clazz)
    {
        this.clazz = clazz;
    }

    /**
     * <p>getErrorTypes.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public static List<SonosType> getErrorTypes()
    {
        return Arrays.asList(globalError, playbackError, sessionError);
    }

    /**
     * <p>Getter for the field <code>clazz</code>.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    public Class<?> getClazz()
    {
        return clazz;
    }
}
