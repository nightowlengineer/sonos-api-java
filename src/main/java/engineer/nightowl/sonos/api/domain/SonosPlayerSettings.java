package engineer.nightowl.sonos.api.domain;

import engineer.nightowl.sonos.api.enums.SonosVolumeMode;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SonosPlayerSettings
{
    private SonosVolumeMode volumeMode;
    private Float volumeScalingFactor;
    private Boolean monoMode;
    private Boolean wifiDisable;

    public SonosPlayerSettings()
    {
    }

    public SonosPlayerSettings(SonosVolumeMode volumeMode, Float volumeScalingFactor, Boolean monoMode, Boolean wifiDisable)
    {
        this.volumeMode = volumeMode;
        this.volumeScalingFactor = volumeScalingFactor;
        this.monoMode = monoMode;
        this.wifiDisable = wifiDisable;
    }

    public SonosVolumeMode getVolumeMode()
    {
        return volumeMode;
    }

    public void setVolumeMode(SonosVolumeMode volumeMode)
    {
        this.volumeMode = volumeMode;
    }

    public Float getVolumeScalingFactor()
    {
        return volumeScalingFactor;
    }

    public void setVolumeScalingFactor(Float volumeScalingFactor)
    {
        this.volumeScalingFactor = volumeScalingFactor;
    }

    public Boolean getMonoMode()
    {
        return monoMode;
    }

    public void setMonoMode(Boolean monoMode)
    {
        this.monoMode = monoMode;
    }

    public Boolean getWifiDisable()
    {
        return wifiDisable;
    }

    public void setWifiDisable(Boolean wifiDisable)
    {
        this.wifiDisable = wifiDisable;
    }

    @Override
    public String toString()
    {
        return "SonosPlayerSettings{" +
                "volumeMode=" + volumeMode +
                ", volumeScalingFactor=" + volumeScalingFactor +
                ", monoMode=" + monoMode +
                ", wifiDisable=" + wifiDisable +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SonosPlayerSettings that = (SonosPlayerSettings) o;

        return new EqualsBuilder()
                .append(volumeMode, that.volumeMode)
                .append(volumeScalingFactor, that.volumeScalingFactor)
                .append(monoMode, that.monoMode)
                .append(wifiDisable, that.wifiDisable)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(volumeMode)
                .append(volumeScalingFactor)
                .append(monoMode)
                .append(wifiDisable)
                .toHashCode();
    }
}
