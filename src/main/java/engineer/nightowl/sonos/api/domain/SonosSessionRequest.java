package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.specs.Validatable;
import engineer.nightowl.sonos.api.util.SonosUtilityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SonosSessionRequest implements Validatable
{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String accountId;
    private String appContext;
    private String appId;
    private String customData;

    public SonosSessionRequest()
    {
    }

    public SonosSessionRequest(final String accountId, final String appContext, final String appId, final String customData)
    {
        this.accountId = accountId;
        this.appContext = appContext;
        this.appId = appId;
        this.customData = customData;
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(final String accountId)
    {
        this.accountId = accountId;
    }

    public String getAppContext()
    {
        return appContext;
    }

    public void setAppContext(final String appContext)
    {
        this.appContext = appContext;
    }

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(final String appId)
    {
        this.appId = appId;
    }

    public String getCustomData()
    {
        return customData;
    }

    public void setCustomData(final String customData)
    {
        if (!SonosUtilityHelper.isEmpty(customData) && customData.getBytes().length > 1023)
        {
            if (logger.isWarnEnabled())
            {
                logger.warn("customData is over 1023 bytes - Sonos API will truncate the data");
            }
        }
        this.customData = customData;
    }

    @Override
    public String toString()
    {
        return "SonosSessionRequest{" +
                "accountId='" + accountId + '\'' +
                ", appContext='" + appContext + '\'' +
                ", appId='" + appId + '\'' +
                ", customData='" + customData + '\'' +
                '}';
    }

    /**
     * Validate this request before sending, to avoid a needless API call.
     * <p>
     * As per documentation, only appId & appContext are required for joining or creating a session request.
     *
     * @throws SonosApiClientException
     */
    @Override
    public void validate() throws SonosApiClientException
    {
        final List<String> validationErrors = new ArrayList<>();
        if (appId == null)
        {
            validationErrors.add("appId cannot be null when creating/joining a session");
        }
        if (appContext == null)
        {
            validationErrors.add("appContext cannot be null when creating/joining a session");
        }
        if (!validationErrors.isEmpty())
        {
            throw new SonosApiClientException(validationErrors);
        }
    }
}
