package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosType;

/**
 * <p>SonosGroupVolume class.</p>
 */
public class SonosGroupVolume extends SonosVolume
{
    @Override
    SonosType getSonosType()
    {
        return SonosType.groupVolume;
    }
}
