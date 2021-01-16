package com.arvin.feifeiyu.win.util;

import java.util.Arrays;
import java.util.Locale;

public class StringUtils {

	private static final String SPLIT_FLAG = "\\s+";
	 
    public static boolean isNullOrEmpty(final String string) {
        return (string == null || string.trim().length() <= 0);
    }
     
     public static boolean isNotBlank(final String string) {
         return (string != null && string.trim().length() > 0);
     }
    
    public static String getSplitDevice(String emtpInfo) {
        String[] splitStr;
        if (StringUtils.isNotBlank(emtpInfo)) {
            splitStr = emtpInfo.split(SPLIT_FLAG);
            if (splitStr.length <= 2) {
            	 return splitStr[0];
            }
        }
        return null;
    }
    
    public static String replace(String path, String source, String target) {
    	return path.replace(source, target);
    }
    
    public static boolean endsWith(String source, String end) {
    	return source.toLowerCase().endsWith(end);
    }
}
