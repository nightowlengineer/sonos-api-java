package engineer.nightowl.sonos.api.util;

import java.util.Collection;
import java.util.Map;

public class SonosUtilityHelper
{
    private SonosUtilityHelper()
    {
        // Default private constructor
    }

    /**
     * This method returns true if the object is null.
     *
     * @param object to check if null
     * @return true | false
     */
    public static boolean isEmpty(final Object object)
    {
        if (object == null)
        {
            return true;
        }
        if (object instanceof Map)
        {
            return ((Map<?, ?>) object).isEmpty();
        }
        if (object instanceof String)
        {
            return ((String) object).isEmpty();
        }
        if (object instanceof Collection)
        {
            return ((Collection<?>) object).isEmpty();
        }
        return false;
    }

}
