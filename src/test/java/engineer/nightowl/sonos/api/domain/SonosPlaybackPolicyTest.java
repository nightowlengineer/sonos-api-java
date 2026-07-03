package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SonosPlaybackPolicyTest
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testDeserializesIsVisibleField() throws Exception
    {
        final String json = "{\"isVisible\":true}";

        final SonosPlaybackPolicy policy = objectMapper.readValue(json, SonosPlaybackPolicy.class);

        assertTrue(policy.getVisible());
    }

    @Test
    void testSerializesAsIsVisibleField() throws Exception
    {
        final SonosPlaybackPolicy policy = new SonosPlaybackPolicy();
        policy.setVisible(true);

        final String json = objectMapper.writeValueAsString(policy);

        assertTrue(json.contains("\"isVisible\":true"));
    }
}
