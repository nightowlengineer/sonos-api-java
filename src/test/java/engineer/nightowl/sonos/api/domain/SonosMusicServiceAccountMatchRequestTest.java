package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SonosMusicServiceAccountMatchRequestTest
{
    @Test
    void testValidatePassesWithRequiredFields()
    {
        final SonosMusicServiceAccountMatchRequest request =
                new SonosMusicServiceAccountMatchRequest("hash1", "nickname", "service1", null, null);

        assertTrue(request.isValid());
    }

    @Test
    void testValidateFailsWithClearMessageWhenFieldsMissing()
    {
        final SonosMusicServiceAccountMatchRequest request = new SonosMusicServiceAccountMatchRequest();

        final SonosApiClientException exception = assertThrows(SonosApiClientException.class, request::validate);

        assertFalse(exception.getMessage().contains("session"));
        assertTrue(exception.getMessage().contains("matching a music service account"));
    }
}
