package com.arvin.feifeiyu.win;

public class StringUtils {

    static public boolean isNullOrEmpty(final String string) {
        return (string == null || string.trim().length() <= 0);
    }
}
