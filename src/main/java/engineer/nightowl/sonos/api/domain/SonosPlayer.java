package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosCapability;

import java.net.URI;
import java.util.List;

/**
 * <p>SonosPlayer class.</p>
 */
public class SonosPlayer
{
    private String apiVersion;
    private List<String> deviceIds;
    private String icon;
    private String id;
    private String minApiVersion;
    private String name;
    private URI restUrl;
    private String softwareVersion;
    private URI websocketUrl;
    private List<SonosCapability> capabilities;

    /**
     * <p>Constructor for SonosPlayer.</p>
     */
    public SonosPlayer()
    {
    }

    /**
     * <p>Constructor for SonosPlayer.</p>
     *
     * @param apiVersion a {@link java.lang.String} object.
     * @param deviceIds a {@link java.util.List} object.
     * @param icon a {@link java.lang.String} object.
     * @param id a {@link java.lang.String} object.
     * @param minApiVersion a {@link java.lang.String} object.
     * @param name a {@link java.lang.String} object.
     * @param restUrl a {@link java.net.URI} object.
     * @param softwareVersion a {@link java.lang.String} object.
     * @param websocketUrl a {@link java.net.URI} object.
     * @param capabilities a {@link java.util.List} object.
     */
    public SonosPlayer(final String apiVersion, final List<String> deviceIds, final String icon, final String id, final String minApiVersion, final String name, final URI restUrl, final String softwareVersion, final URI websocketUrl, final List<SonosCapability> capabilities)
    {
        this.apiVersion = apiVersion;
        this.deviceIds = deviceIds;
        this.icon = icon;
        this.id = id;
        this.minApiVersion = minApiVersion;
        this.name = name;
        this.restUrl = restUrl;
        this.softwareVersion = softwareVersion;
        this.websocketUrl = websocketUrl;
        this.capabilities = capabilities;
    }

    /**
     * <p>Getter for the field <code>apiVersion</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getApiVersion()
    {
        return apiVersion;
    }

    /**
     * <p>Setter for the field <code>apiVersion</code>.</p>
     *
     * @param apiVersion a {@link java.lang.String} object.
     */
    public void setApiVersion(final String apiVersion)
    {
        this.apiVersion = apiVersion;
    }

    /**
     * <p>Getter for the field <code>deviceIds</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<String> getDeviceIds()
    {
        return deviceIds;
    }

    /**
     * <p>Setter for the field <code>deviceIds</code>.</p>
     *
     * @param deviceIds a {@link java.util.List} object.
     */
    public void setDeviceIds(final List<String> deviceIds)
    {
        this.deviceIds = deviceIds;
    }

    /**
     * <p>Getter for the field <code>icon</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getIcon()
    {
        return icon;
    }

    /**
     * <p>Setter for the field <code>icon</code>.</p>
     *
     * @param icon a {@link java.lang.String} object.
     */
    public void setIcon(final String icon)
    {
        this.icon = icon;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getId()
    {
        return id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id a {@link java.lang.String} object.
     */
    public void setId(final String id)
    {
        this.id = id;
    }

    /**
     * <p>Getter for the field <code>minApiVersion</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMinApiVersion()
    {
        return minApiVersion;
    }

    /**
     * <p>Setter for the field <code>minApiVersion</code>.</p>
     *
     * @param minApiVersion a {@link java.lang.String} object.
     */
    public void setMinApiVersion(final String minApiVersion)
    {
        this.minApiVersion = minApiVersion;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * <p>Getter for the field <code>restUrl</code>.</p>
     *
     * @return a {@link java.net.URI} object.
     */
    public URI getRestUrl()
    {
        return restUrl;
    }

    /**
     * <p>Setter for the field <code>restUrl</code>.</p>
     *
     * @param restUrl a {@link java.net.URI} object.
     */
    public void setRestUrl(final URI restUrl)
    {
        this.restUrl = restUrl;
    }

    /**
     * <p>Getter for the field <code>softwareVersion</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSoftwareVersion()
    {
        return softwareVersion;
    }

    /**
     * <p>Setter for the field <code>softwareVersion</code>.</p>
     *
     * @param softwareVersion a {@link java.lang.String} object.
     */
    public void setSoftwareVersion(final String softwareVersion)
    {
        this.softwareVersion = softwareVersion;
    }

    /**
     * <p>Getter for the field <code>websocketUrl</code>.</p>
     *
     * @return a {@link java.net.URI} object.
     */
    public URI getWebsocketUrl()
    {
        return websocketUrl;
    }

    /**
     * <p>Setter for the field <code>websocketUrl</code>.</p>
     *
     * @param websocketUrl a {@link java.net.URI} object.
     */
    public void setWebsocketUrl(final URI websocketUrl)
    {
        this.websocketUrl = websocketUrl;
    }

    /**
     * <p>Getter for the field <code>capabilities</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<SonosCapability> getCapabilities()
    {
        return capabilities;
    }

    /**
     * <p>Setter for the field <code>capabilities</code>.</p>
     *
     * @param capabilities a {@link java.util.List} object.
     */
    public void setCapabilities(final List<SonosCapability> capabilities)
    {
        this.capabilities = capabilities;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "SonosPlayer{" +
                "apiVersion='" + apiVersion + '\'' +
                ", deviceIds=" + deviceIds +
                ", icon='" + icon + '\'' +
                ", id='" + id + '\'' +
                ", minApiVersion='" + minApiVersion + '\'' +
                ", name='" + name + '\'' +
                ", restUrl=" + restUrl +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", websocketUrl=" + websocketUrl +
                ", capabilities=" + capabilities +
                '}';
    }
}
