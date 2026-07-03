package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import engineer.nightowl.sonos.api.enums.SonosPlaybackErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SonosPlaybackErrorTest
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testDeserializesTypedErrorCode() throws Exception
    {
        final String json = "{\"httpStatus\":404,\"errorCode\":\"ERROR_PLAYBACK_FAILED\",\"reason\":\"nope\","
                + "\"itemId\":\"abc\",\"queueVersion\":\"v1\"}";

        final SonosPlaybackError error = objectMapper.readValue(json, SonosPlaybackError.class);

        assertEquals(SonosPlaybackErrorCode.ERROR_PLAYBACK_FAILED, error.getErrorCode());
        assertEquals(404, error.getHttpStatus());
        assertEquals("abc", error.getItemId());
        assertEquals("v1", error.getQueueVersion());
        assertEquals("nope", error.getReason());
    }

    @Test
    void testToStringUsesActualClassName()
    {
        final SonosPlaybackError error = new SonosPlaybackError();
        error.setErrorCode(SonosPlaybackErrorCode.ERROR_SKIP_LIMIT_REACHED);

        assertEquals("SonosPlaybackError{errorCode=ERROR_SKIP_LIMIT_REACHED, reason='null'}", error.toString());
    }
}
