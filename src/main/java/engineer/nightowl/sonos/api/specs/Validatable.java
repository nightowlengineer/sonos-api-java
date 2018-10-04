package engineer.nightowl.sonos.api.specs;

import engineer.nightowl.sonos.api.exception.SonosApiClientException;

public interface Validatable
{
    void validate() throws SonosApiClientException;

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
