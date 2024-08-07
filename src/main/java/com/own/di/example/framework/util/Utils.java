package com.own.di.example.framework.util;

import lombok.experimental.UtilityClass;

/**
 * The Utils class provides utility methods for the application.
 */
@UtilityClass
public class Utils {
    /**
     * Checks if a string contains another string, ignoring case considerations.
     *
     * @param str       the string to search in
     * @param searchStr the string to search for
     * @return true if the searchStr is found within str, false otherwise
     */
    public boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
}
