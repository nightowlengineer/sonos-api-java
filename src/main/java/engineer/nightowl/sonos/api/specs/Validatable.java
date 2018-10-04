package engineer.nightowl.sonos.api.specs;

import engineer.nightowl.sonos.api.exception.SonosApiClientException;

/**
 * <p>Validatable interface.</p>
 */
public interface Validatable
{
    /**
     * <p>Hook to validate an object</p>
     *
     * @throws engineer.nightowl.sonos.api.exception.SonosApiClientException if there are validation errors
     */
    void validate() throws SonosApiClientException;

    /**
     * <p>isValid.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    default Boolean isValid()
    {
        try
        {
            validate();
        } catch (final SonosApiClientException e)
        {
            return false;
        }
        return true;
    }
}
