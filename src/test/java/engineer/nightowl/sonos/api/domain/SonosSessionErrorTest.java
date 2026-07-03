package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.nightowl.sonos.api.enums.SonosSessionErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SonosSessionErrorTest
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testDeserializesTypedErrorCode() throws Exception
    {
        final String json = "{\"sessionId\":\"sess1\",\"errorCode\":\"ERROR_SESSION_EVICTED\",\"reason\":\"bye\"}";

        final SonosSessionError error = objectMapper.readValue(json, SonosSessionError.class);

        assertEquals(SonosSessionErrorCode.ERROR_SESSION_EVICTED, error.getErrorCode());
        assertEquals("sess1", error.getSessionId());
        assertEquals("bye", error.getReason());
    }

    @Test
    void testToStringUsesActualClassName()
    {
        final SonosSessionError error = new SonosSessionError();
        error.setErrorCode(SonosSessionErrorCode.ERROR_SESSION_JOIN_FAILED);

        assertEquals("SonosSessionError{errorCode=ERROR_SESSION_JOIN_FAILED, reason='null'}", error.toString());
    }
}
