package engineer.nightowl.sonos.api.domain;

import java.util.List;

/**
 * <p>SonosHouseholdList class.</p>
 */
public class SonosHouseholdList
{
    private List<SonosHousehold> households;

    /**
     * <p>Constructor for SonosHouseholdList.</p>
     */
    public SonosHouseholdList()
    {
    }

    /**
     * <p>Constructor for SonosHouseholdList.</p>
     *
     * @param households a {@link java.util.List} object.
     */
    public SonosHouseholdList(final List<SonosHousehold> households)
    {
        this.households = households;
    }

    /**
     * <p>Getter for the field <code>households</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<SonosHousehold> getHouseholds()
    {
        return households;
    }

    /**
     * <p>Setter for the field <code>households</code>.</p>
     *
     * @param households a {@link java.util.List} object.
     */
    public void setHouseholds(final List<SonosHousehold> households)
    {
        this.households = households;
    }
}
