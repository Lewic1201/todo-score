package com.lewic.todoscore.utils;

/**
 * @author lewic
 * @since 2020/1/18 21:49
 */

public class StringUtil {

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return str.isEmpty();
        }
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }
}
