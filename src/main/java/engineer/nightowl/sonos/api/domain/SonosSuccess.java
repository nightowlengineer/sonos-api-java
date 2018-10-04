package engineer.nightowl.sonos.api.domain;

public class SonosSuccess
{
    private Boolean success;

    public SonosSuccess()
    {
    }

    public SonosSuccess(final Boolean success)
    {
        this.success = success;
    }

    public Boolean getSuccess()
    {
        return success;
    }

    public void setSuccess(final Boolean success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "SonosSuccess{" +
                "success=" + success +
                '}';
    }
}
