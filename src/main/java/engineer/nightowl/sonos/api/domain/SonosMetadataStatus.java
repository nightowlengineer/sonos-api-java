package engineer.nightowl.sonos.api.domain;

/**
 * <p>SonosMetadataStatus class.</p>
 */
public class SonosMetadataStatus
{
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((container == null) ? 0 : container.hashCode());
        result = prime * result + ((currentItem == null) ? 0 : currentItem.hashCode());
        result = prime * result + ((nextItem == null) ? 0 : nextItem.hashCode());
        result = prime * result + ((streamInfo == null) ? 0 : streamInfo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SonosMetadataStatus other = (SonosMetadataStatus) obj;
        if (container == null) {
            if (other.container != null)
                return false;
        } else if (!container.equals(other.container))
            return false;
        if (currentItem == null) {
            if (other.currentItem != null)
                return false;
        } else if (!currentItem.equals(other.currentItem))
            return false;
        if (nextItem == null) {
            if (other.nextItem != null)
                return false;
        } else if (!nextItem.equals(other.nextItem))
            return false;
        if (streamInfo == null) {
            if (other.streamInfo != null)
                return false;
        } else if (!streamInfo.equals(other.streamInfo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SonosMetadataStatus [container=" + container + ", currentItem=" + currentItem + ", nextItem=" + nextItem
                + ", streamInfo=" + streamInfo + "]";
    }

    
}
