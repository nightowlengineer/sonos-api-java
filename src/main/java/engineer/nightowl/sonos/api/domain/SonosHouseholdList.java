package engineer.nightowl.sonos.api.domain;

import java.util.List;

public class SonosHouseholdList
{
    private List<SonosHousehold> households;

    public SonosHouseholdList()
    {
    }

    public SonosHouseholdList(final List<SonosHousehold> households)
    {
        this.households = households;
    }

    public List<SonosHousehold> getHouseholds()
    {
        return households;
    }

    public void setHouseholds(final List<SonosHousehold> households)
    {
        this.households = households;
    }
}
