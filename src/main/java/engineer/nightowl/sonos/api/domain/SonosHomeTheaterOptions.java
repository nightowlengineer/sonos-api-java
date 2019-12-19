package engineer.nightowl.sonos.api.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SonosHomeTheaterOptions
{
    private Boolean nightMode;
    private Boolean enhanceDialog;
    private Integer groupingLatency;

    public SonosHomeTheaterOptions()
    {
    }

    public SonosHomeTheaterOptions(Boolean nightMode, Boolean enhanceDialog, Integer groupingLatency)
    {
        this.nightMode = nightMode;
        this.enhanceDialog = enhanceDialog;
        this.groupingLatency = groupingLatency;
    }

    public Boolean getNightMode()
    {
        return nightMode;
    }

    public void setNightMode(Boolean nightMode)
    {
        this.nightMode = nightMode;
    }

    public Boolean getEnhanceDialog()
    {
        return enhanceDialog;
    }

    public void setEnhanceDialog(Boolean enhanceDialog)
    {
        this.enhanceDialog = enhanceDialog;
    }

    public Integer getGroupingLatency()
    {
        return groupingLatency;
    }

    public void setGroupingLatency(Integer groupingLatency)
    {
        this.groupingLatency = groupingLatency;
    }

    @Override
    public String toString()
    {
        return "SonosHomeTheaterOptions{" +
                "nightMode=" + nightMode +
                ", enhanceDialog=" + enhanceDialog +
                ", groupingLatency=" + groupingLatency +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SonosHomeTheaterOptions that = (SonosHomeTheaterOptions) o;

        return new EqualsBuilder()
                .append(nightMode, that.nightMode)
                .append(enhanceDialog, that.enhanceDialog)
                .append(groupingLatency, that.groupingLatency)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(nightMode)
                .append(enhanceDialog)
                .append(groupingLatency)
                .toHashCode();
    }
}
