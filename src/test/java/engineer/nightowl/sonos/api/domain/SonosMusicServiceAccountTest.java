package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SonosMusicServiceAccountTest
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testDeserializesIsGuestField() throws Exception
    {
        final String json = "{\"id\":\"1\",\"isGuest\":true}";

        final SonosMusicServiceAccount account = objectMapper.readValue(json, SonosMusicServiceAccount.class);

        assertTrue(account.getGuest());
    }

    @Test
    void testSerializesAsIsGuestField() throws Exception
    {
        final SonosMusicServiceAccount account = new SonosMusicServiceAccount();
        account.setGuest(true);

        final String json = objectMapper.writeValueAsString(account);

        assertTrue(json.contains("\"isGuest\":true"));
    }
}
