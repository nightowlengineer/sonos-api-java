package engineer.nightowl.sonos.api.util;

import java.util.function.BiPredicate;

public class HeaderFilter implements BiPredicate<String, String>{

    @Override
    public boolean test(String t, String u) {
        return true;
    }
    
}
