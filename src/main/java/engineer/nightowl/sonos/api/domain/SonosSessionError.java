package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engineer.nightowl.sonos.api.enums.SonosErrorBase;
import engineer.nightowl.sonos.api.enums.SonosSessionErrorCode;
import engineer.nightowl.sonos.api.exception.SonosApiError;

/**
 * <p>SonosSessionError class.</p>
 */
public class SonosSessionError extends SonosApiError
{
    private String sessionId;
    private SonosSessionErrorCode errorCode;

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(final String sessionId)
    {
        this.sessionId = sessionId;
    }

    // Explicit @JsonProperty is required on both accessors below, not just the name-convention match -
    // without it, the sibling @JsonIgnore on setErrorCode(SonosErrorBase) causes Jackson to ignore the
    // whole logical "errorCode" property, including these.
    @Override
    @JsonProperty("errorCode")
    public SonosSessionErrorCode getErrorCode()
    {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(final SonosSessionErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    /**
     * Not used directly - {@link SonosApiError#setErrorCode(SonosErrorBase)} is widened here purely so
     * this class has a single field of the correct type; Jackson deserializes via
     * {@link #setErrorCode(SonosSessionErrorCode)} instead, which is the only setter it sees for this property.
     *
     * @param errorCode must be a {@link SonosSessionErrorCode}
     */
    @Override
    @JsonIgnore
    public void setErrorCode(final SonosErrorBase errorCode)
    {
        this.errorCode = (SonosSessionErrorCode) errorCode;
    }
}
