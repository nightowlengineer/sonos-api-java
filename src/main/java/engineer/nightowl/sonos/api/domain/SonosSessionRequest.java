package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import engineer.nightowl.sonos.api.specs.Validatable;
import engineer.nightowl.sonos.api.util.SonosUtilityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>SonosSessionRequest class.</p>
 */
public class SonosSessionRequest implements Validatable
{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String accountId;
    private String appContext;
    private String appId;
    private String customData;

    /**
     * <p>Constructor for SonosSessionRequest.</p>
     */
    public SonosSessionRequest()
    {
    }

    /**
     * <p>Constructor for SonosSessionRequest.</p>
     *
     * @param accountId a {@link java.lang.String} object.
     * @param appContext a {@link java.lang.String} object.
     * @param appId a {@link java.lang.String} object.
     * @param customData a {@link java.lang.String} object.
     */
    public SonosSessionRequest(final String accountId, final String appContext, final String appId, final String customData)
    {
        this.accountId = accountId;
        this.appContext = appContext;
        this.appId = appId;
        this.customData = customData;
    }

    /**
     * <p>Getter for the field <code>accountId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAccountId()
    {
        return accountId;
    }

    /**
     * <p>Setter for the field <code>accountId</code>.</p>
     *
     * @param accountId a {@link java.lang.String} object.
     */
    public void setAccountId(final String accountId)
    {
        this.accountId = accountId;
    }

    /**
     * <p>Getter for the field <code>appContext</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAppContext()
    {
        return appContext;
    }

    /**
     * <p>Setter for the field <code>appContext</code>.</p>
     *
     * @param appContext a {@link java.lang.String} object.
     */
    public void setAppContext(final String appContext)
    {
        this.appContext = appContext;
    }

    /**
     * <p>Getter for the field <code>appId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAppId()
    {
        return appId;
    }

    /**
     * <p>Setter for the field <code>appId</code>.</p>
     *
     * @param appId a {@link java.lang.String} object.
     */
    public void setAppId(final String appId)
    {
        this.appId = appId;
    }

    /**
     * <p>Getter for the field <code>customData</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomData()
    {
        return customData;
    }

    /**
     * <p>Setter for the field <code>customData</code>.</p>
     *
     * @param customData a {@link java.lang.String} object.
     */
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

    /** {@inheritDoc} */
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
     * {@inheritDoc}
     *
     * Validate this request before sending, to avoid a needless API call.
     * <p>
     * As per documentation, only appId and appContext are required for joining or creating a session request.
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
