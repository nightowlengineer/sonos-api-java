package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosCapability;

import java.net.URI;
import java.util.List;

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

    public SonosPlayer()
    {
    }

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

    public String getApiVersion()
    {
        return apiVersion;
    }

    public void setApiVersion(final String apiVersion)
    {
        this.apiVersion = apiVersion;
    }

    public List<String> getDeviceIds()
    {
        return deviceIds;
    }

    public void setDeviceIds(final List<String> deviceIds)
    {
        this.deviceIds = deviceIds;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(final String icon)
    {
        this.icon = icon;
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public String getMinApiVersion()
    {
        return minApiVersion;
    }

    public void setMinApiVersion(final String minApiVersion)
    {
        this.minApiVersion = minApiVersion;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public URI getRestUrl()
    {
        return restUrl;
    }

    public void setRestUrl(final URI restUrl)
    {
        this.restUrl = restUrl;
    }

    public String getSoftwareVersion()
    {
        return softwareVersion;
    }

    public void setSoftwareVersion(final String softwareVersion)
    {
        this.softwareVersion = softwareVersion;
    }

    public URI getWebsocketUrl()
    {
        return websocketUrl;
    }

    public void setWebsocketUrl(final URI websocketUrl)
    {
        this.websocketUrl = websocketUrl;
    }

    public List<SonosCapability> getCapabilities()
    {
        return capabilities;
    }

    public void setCapabilities(final List<SonosCapability> capabilities)
    {
        this.capabilities = capabilities;
    }

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
