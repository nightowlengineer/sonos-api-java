package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosSessionErrorCode;
import engineer.nightowl.sonos.api.exception.SonosApiError;

public class SonosSessionError extends SonosApiError
{
    private String sessionId;
    private SonosSessionErrorCode errorCode;
}
