package engineer.nightowl.sonos.api.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.nightowl.sonos.api.enums.SonosErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class SonosApiErrorTest
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGlobalErrorDeserializesTypedErrorCode() throws Exception
    {
        final String json = "{\"errorCode\":\"ERROR_NOT_CAPABLE\",\"reason\":\"nope\"}";

        final SonosApiError error = objectMapper.readValue(json, SonosApiError.class);

        assertInstanceOf(SonosErrorCode.class, error.getErrorCode());
        assertEquals(SonosErrorCode.ERROR_NOT_CAPABLE, error.getErrorCode());
        assertEquals("nope", error.getReason());
    }

    @Test
    void testToStringUsesActualClassName()
    {
        final SonosApiError error = new SonosApiError(SonosErrorCode.ERROR_NOT_CAPABLE, "nope");

        assertEquals("SonosApiError{errorCode=ERROR_NOT_CAPABLE, reason='nope'}", error.toString());
    }
}
