package com.mnayef.compiler;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
public class StringUtil {

    public static String makeFirstCharToUpper(String name) {
        String s1 = name.substring(0, 1).toUpperCase();
        return s1 + name.substring(1);
    }

}
