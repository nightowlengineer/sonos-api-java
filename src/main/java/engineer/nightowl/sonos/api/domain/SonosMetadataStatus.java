package engineer.nightowl.sonos.api.domain;

import java.util.Objects;

/**
 * <p>
 * SonosMetadataStatus class.
 * </p>
 */
public class SonosMetadataStatus {
    private SonosMusicContainer container;
    private SonosItem currentItem;
    private SonosItem nextItem;
    private String streamInfo;

    public SonosMetadataStatus() {
    }

    public SonosMetadataStatus(SonosMusicContainer container, SonosItem currentItem, SonosItem nextItem,
            String streamInfo) {
        this.container = container;
        this.currentItem = currentItem;
        this.nextItem = nextItem;
        this.streamInfo = streamInfo;
    }

    public SonosMusicContainer getContainer() {
        return container;
    }

    public void setContainer(SonosMusicContainer container) {
        this.container = container;
    }

    public SonosItem getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(SonosItem currentItem) {
        this.currentItem = currentItem;
    }

    public SonosItem getNextItem() {
        return nextItem;
    }

    public void setNextItem(SonosItem nextItem) {
        this.nextItem = nextItem;
    }

    public String getStreamInfo() {
        return streamInfo;
    }

    public void setStreamInfo(String streamInfo) {
        this.streamInfo = streamInfo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(container, currentItem, nextItem, streamInfo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SonosMetadataStatus))
            return false;
        SonosMetadataStatus other = (SonosMetadataStatus) obj;
        return Objects.equals(container, other.container) && Objects.equals(currentItem, other.currentItem)
                && Objects.equals(nextItem, other.nextItem) && Objects.equals(streamInfo, other.streamInfo);
    }

    @Override
    public String toString() {
        return "SonosMetadataStatus [container=" + container + ", currentItem=" + currentItem + ", nextItem=" + nextItem
                + ", streamInfo=" + streamInfo + "]";
    }

    
}
