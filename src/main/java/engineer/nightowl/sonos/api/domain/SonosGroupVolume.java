package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosType;

public class SonosGroupVolume extends SonosVolume
{
    @Override
    SonosType getSonosType()
    {
        return SonosType.groupVolume;
    }
}
