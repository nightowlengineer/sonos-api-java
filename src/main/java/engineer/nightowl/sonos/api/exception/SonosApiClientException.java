package engineer.nightowl.sonos.api.exception;

public class SonosApiClientException extends Exception
{

    SonosApiClientException()
    {
    }

    public SonosApiClientException(final String message)
    {
        super(message);
    }

    public SonosApiClientException(final Object obj)
    {
        super(obj.toString());
    }

    public SonosApiClientException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
