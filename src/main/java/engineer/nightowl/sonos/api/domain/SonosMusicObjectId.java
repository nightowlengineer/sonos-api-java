package engineer.nightowl.sonos.api.domain;

/**
 * <p>SonosMusicObjectId class.</p>
 */
public class SonosMusicObjectId
{
    private String serviceId;
    private String objectId;
    private String accountId;

    /**
     * <p>Constructor for SonosMusicObjectId.</p>
     */
    public SonosMusicObjectId()
    {
    }

    /**
     * <p>Constructor for SonosMusicObjectId.</p>
     *
     * @param serviceId a {@link java.lang.String} object.
     * @param objectId a {@link java.lang.String} object.
     * @param accountId a {@link java.lang.String} object.
     */
    public SonosMusicObjectId(final String serviceId, final String objectId, final String accountId)
    {
        this.serviceId = serviceId;
        this.objectId = objectId;
        this.accountId = accountId;
    }

    /**
     * <p>Getter for the field <code>serviceId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getServiceId()
    {
        return serviceId;
    }

    /**
     * <p>Setter for the field <code>serviceId</code>.</p>
     *
     * @param serviceId a {@link java.lang.String} object.
     */
    public void setServiceId(final String serviceId)
    {
        this.serviceId = serviceId;
    }

    /**
     * <p>Getter for the field <code>objectId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getObjectId()
    {
        return objectId;
    }

    /**
     * <p>Setter for the field <code>objectId</code>.</p>
     *
     * @param objectId a {@link java.lang.String} object.
     */
    public void setObjectId(final String objectId)
    {
        this.objectId = objectId;
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

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SonosMusicObjectId{" +
                "serviceId='" + serviceId + '\'' +
                ", objectId='" + objectId + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
