package engineer.nightowl.sonos.api.exception;

import engineer.nightowl.sonos.api.enums.SonosErrorCode;

public class SonosApiError extends Exception
{
    private SonosErrorCode errorCode;
    private String reason;

    public SonosApiError()
    {
    }

    public SonosApiError(final SonosErrorCode errorCode, final String reason)
    {
        this.errorCode = errorCode;
        this.reason = reason;
    }

    public SonosErrorCode getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(final SonosErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(final String reason)
    {
        this.reason = reason;
    }

    @Override
    public String toString()
    {
        return "SonosGlobalError{" +
                "errorCode=" + errorCode +
                ", reason='" + reason + '\'' +
                '}';
    }
}
