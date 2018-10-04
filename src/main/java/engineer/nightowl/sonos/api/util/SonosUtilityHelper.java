package engineer.nightowl.sonos.api.util;

import java.util.Collection;
import java.util.Map;

public class SonosUtilityHelper
{
    // null/empty check code from: https://dzone.com/articles/consistent-way-doing-null-0

    /**
     * This method returns true if the collection is null or is empty.
     *
     * @param collection
     * @return true | false
     */
    public static boolean isEmpty(final Collection<?> collection)
    {
        if (collection == null || collection.isEmpty())
        {
            return true;
        }
        return false;
    }

    /**
     * This method returns true of the map is null or is empty.
     *
     * @param map
     * @return true | false
     */
    public static boolean isEmpty(final Map<?, ?> map)
    {
        if (map == null || map.isEmpty())
        {
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the objet is null.
     *
     * @param object
     * @return true | false
     */
    public static boolean isEmpty(final Object object)
    {
        if (object == null)
        {
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the input array is null or its length is zero.
     *
     * @param array
     * @return true | false
     */
    public static boolean isEmpty(final Object[] array)
    {
        if (array == null || array.length == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the input string is null or its length is zero.
     *
     * @param string
     * @return true | false
     */
    public static boolean isEmpty(final String string)
    {
        if (string == null || string.trim().length() == 0)
        {
            return true;
        }
        return false;
    }
}
