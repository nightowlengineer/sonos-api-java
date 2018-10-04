package engineer.nightowl.sonos.api.exception;

import engineer.nightowl.sonos.api.enums.SonosErrorCode;

/**
 * <p>SonosApiError class.</p>
 */
public class SonosApiError extends Exception
{
    private SonosErrorCode errorCode;
    private String reason;

    /**
     * <p>Constructor for SonosApiError.</p>
     */
    public SonosApiError()
    {
    }

    /**
     * <p>Constructor for SonosApiError.</p>
     *
     * @param errorCode a {@link engineer.nightowl.sonos.api.enums.SonosErrorCode} object.
     * @param reason a {@link java.lang.String} object.
     */
    public SonosApiError(final SonosErrorCode errorCode, final String reason)
    {
        this.errorCode = errorCode;
        this.reason = reason;
    }

    /**
     * <p>Getter for the field <code>errorCode</code>.</p>
     *
     * @return a {@link engineer.nightowl.sonos.api.enums.SonosErrorCode} object.
     */
    public SonosErrorCode getErrorCode()
    {
        return errorCode;
    }

    /**
     * <p>Setter for the field <code>errorCode</code>.</p>
     *
     * @param errorCode a {@link engineer.nightowl.sonos.api.enums.SonosErrorCode} object.
     */
    public void setErrorCode(final SonosErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    /**
     * <p>Getter for the field <code>reason</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getReason()
    {
        return reason;
    }

    /**
     * <p>Setter for the field <code>reason</code>.</p>
     *
     * @param reason a {@link java.lang.String} object.
     */
    public void setReason(final String reason)
    {
        this.reason = reason;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SonosGlobalError{" +
                "errorCode=" + errorCode +
                ", reason='" + reason + '\'' +
                '}';
    }
}
