package engineer.nightowl.sonos.api.util;

import java.util.ArrayList;
import java.util.List;

public enum SignatureHeader {
    SEQUENCE_ID("X-Sonos-Event-Seq-Id"),
    NAMESPACE("X-Sonos-Namespace"),
    TYPE("X-Sonos-Type"),
    TARGET_TYPE("X-Sonos-Target-Type"),
    TARGET_VALUE("X-Sonos-Target-Value");

    private String headerKey;

    SignatureHeader(final String headerKey)
    {
        this.headerKey = headerKey;
    }

    public String getHeaderKey()
    {
        return headerKey;
    }

    public static List<String> getAllHeaders()
    {
        final List<String> headers = new ArrayList<>(5);
        headers.add(SEQUENCE_ID.headerKey);
        headers.add(NAMESPACE.headerKey);
        headers.add(TYPE.headerKey);
        headers.add(TARGET_TYPE.headerKey);
        headers.add(TARGET_VALUE.headerKey);
        return headers;
    }
}
