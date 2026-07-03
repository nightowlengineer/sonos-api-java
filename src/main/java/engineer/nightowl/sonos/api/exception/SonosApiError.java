package engineer.nightowl.sonos.api.exception;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import engineer.nightowl.sonos.api.enums.SonosErrorBase;
import engineer.nightowl.sonos.api.enums.SonosErrorCode;

/**
 * <p>SonosApiError class.</p>
 */
public class SonosApiError extends Exception
{

    private static final long serialVersionUID = 7034540163075468346L;
    private SonosErrorBase errorCode;
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
     * @return a {@link engineer.nightowl.sonos.api.enums.SonosErrorBase} object - subclasses (e.g.
     * {@link engineer.nightowl.sonos.api.domain.SonosPlaybackError}, {@link engineer.nightowl.sonos.api.domain.SonosSessionError})
     * override this with a covariant return type for their own error-code enum.
     */
    public SonosErrorBase getErrorCode()
    {
        return errorCode;
    }

    /**
     * <p>Setter for the field <code>errorCode</code>.</p>
     *
     * <p>{@code SonosErrorCode} is used as the deserialization target here (via {@code @JsonDeserialize(as=...)})
     * since this is the concrete type used for a plain (non-subclassed) {@code SonosApiError}, i.e. a
     * {@link engineer.nightowl.sonos.api.enums.SonosType#globalError}. Subclasses that need a different
     * concrete error-code enum override this method and mark it {@code @JsonIgnore}, providing their own
     * concretely-typed setter instead.</p>
     *
     * @param errorCode a {@link engineer.nightowl.sonos.api.enums.SonosErrorBase} object.
     */
    @JsonDeserialize(as = SonosErrorCode.class)
    public void setErrorCode(final SonosErrorBase errorCode)
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
        return getClass().getSimpleName() + "{" +
                "errorCode=" + errorCode +
                ", reason='" + reason + '\'' +
                '}';
    }
}
