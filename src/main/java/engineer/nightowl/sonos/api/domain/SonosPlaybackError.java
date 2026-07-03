package engineer.nightowl.sonos.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engineer.nightowl.sonos.api.enums.SonosErrorBase;
import engineer.nightowl.sonos.api.enums.SonosPlaybackErrorCode;
import engineer.nightowl.sonos.api.exception.SonosApiError;

public class SonosPlaybackError extends SonosApiError
{
    private Integer httpStatus;
    private String itemId;
    private String queueVersion;
    private SonosPlaybackErrorCode errorCode;

    public Integer getHttpStatus()
    {
        return httpStatus;
    }

    public void setHttpStatus(final Integer httpStatus)
    {
        this.httpStatus = httpStatus;
    }

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(final String itemId)
    {
        this.itemId = itemId;
    }

    public String getQueueVersion()
    {
        return queueVersion;
    }

    public void setQueueVersion(final String queueVersion)
    {
        this.queueVersion = queueVersion;
    }

    // Explicit @JsonProperty is required on both accessors below, not just the name-convention match -
    // without it, the sibling @JsonIgnore on setErrorCode(SonosErrorBase) causes Jackson to ignore the
    // whole logical "errorCode" property, including these.
    @Override
    @JsonProperty("errorCode")
    public SonosPlaybackErrorCode getErrorCode()
    {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(final SonosPlaybackErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    /**
     * Not used directly - {@link SonosApiError#setErrorCode(SonosErrorBase)} is widened here purely so
     * this class has a single field of the correct type; Jackson deserializes via
     * {@link #setErrorCode(SonosPlaybackErrorCode)} instead, which is the only setter it sees for this property.
     *
     * @param errorCode must be a {@link SonosPlaybackErrorCode}
     */
    @Override
    @JsonIgnore
    public void setErrorCode(final SonosErrorBase errorCode)
    {
        this.errorCode = (SonosPlaybackErrorCode) errorCode;
    }
}
