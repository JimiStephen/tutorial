package com.jimi.javase.lang;

import java.util.Random;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 10:00
 */
public class StringUtils {

    private static final String RAND_SOURCE = "abcefghijklmnopqrstuvwxyz1234567890ABCEFGHIJKLMNOPQRSTUVWXYZ";


    public static String getRandString(int maxLen) {
        StringBuilder sb = new StringBuilder("");

        Random random = new Random();
        for (int i = 0; i < maxLen; i ++){
            sb.append(RAND_SOURCE.charAt(random.nextInt(RAND_SOURCE.length())));
        }
        return sb.toString();
    }
}
