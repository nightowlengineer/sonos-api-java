package engineer.nightowl.sonos.api.domain;

public class SonosMusicObjectId
{
    private String serviceId;
    private String objectId;
    private String accountId;

    public SonosMusicObjectId()
    {
    }

    public SonosMusicObjectId(final String serviceId, final String objectId, final String accountId)
    {
        this.serviceId = serviceId;
        this.objectId = objectId;
        this.accountId = accountId;
    }

    public String getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(final String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId(final String objectId)
    {
        this.objectId = objectId;
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(final String accountId)
    {
        this.accountId = accountId;
    }

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
