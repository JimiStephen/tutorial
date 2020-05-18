package com.jimi.javase.lang;

import com.sun.istack.internal.NotNull;

import java.util.Random;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/18 10:00
 */
public class StringUtils {

    private static final String RAND_SOURCE = "abcefghijklmnopqrstuvwxyz1234567890ABCEFGHIJKLMNOPQRSTUVWXYZ";


    public static String getRandString(@NotNull int maxLen) {
        StringBuilder sb = new StringBuilder("");

        Random random = new Random();
        for (int i = 0; i < maxLen; i ++){
            sb.append(RAND_SOURCE.charAt(random.nextInt(RAND_SOURCE.length())));
        }
        return sb.toString();
    }
}
