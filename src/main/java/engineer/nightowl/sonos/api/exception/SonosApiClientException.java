package engineer.nightowl.sonos.api.exception;

/**
 * <p>SonosApiClientException class.</p>
 *
 */
public class SonosApiClientException extends Exception
{

    private static final long serialVersionUID = -7303156575625750389L;

    SonosApiClientException()
    {
    }

    /**
     * <p>Constructor for SonosApiClientException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public SonosApiClientException(final String message)
    {
        super(message);
    }

    /**
     * <p>Constructor for SonosApiClientException.</p>
     *
     * @param obj a {@link java.lang.Object} object.
     */
    public SonosApiClientException(final Object obj)
    {
        super(obj.toString());
    }

    /**
     * <p>Constructor for SonosApiClientException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Throwable} object.
     */
    public SonosApiClientException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
