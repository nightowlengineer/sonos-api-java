package engineer.nightowl.sonos.api.domain;

/**
 * <p>SonosSuccess class.</p>
 */
public class SonosSuccess
{
    private Boolean success;

    /**
     * <p>Constructor for SonosSuccess.</p>
     */
    public SonosSuccess()
    {
    }

    /**
     * <p>Constructor for SonosSuccess.</p>
     *
     * @param success a {@link java.lang.Boolean} object.
     */
    public SonosSuccess(final Boolean success)
    {
        this.success = success;
    }

    /**
     * <p>Getter for the field <code>success</code>.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getSuccess()
    {
        return success;
    }

    /**
     * <p>Setter for the field <code>success</code>.</p>
     *
     * @param success a {@link java.lang.Boolean} object.
     */
    public void setSuccess(final Boolean success)
    {
        this.success = success;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SonosSuccess{" +
                "success=" + success +
                '}';
    }
}
