package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosPlaybackErrorCode;
import engineer.nightowl.sonos.api.exception.SonosApiError;

public class SonosPlaybackError extends SonosApiError
{
    private Integer httpStatus;
    private String itemId;
    private String queueVersion;
    private SonosPlaybackErrorCode errorCode;
}
