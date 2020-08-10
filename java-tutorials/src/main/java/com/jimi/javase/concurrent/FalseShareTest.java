package com.jimi.javase.concurrent;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/6/10 13:59
 */
public class FalseShareTest {

    public static void main(String[] args) {
        int[] arr = new int[64 * 1024 * 1024];

        // Loop 1
        for (int i = 0; i < arr.length; i++) arr[i] *= 3;

        // Loop 2
        for (int i = 0; i < arr.length; i += 16) arr[i] *= 3;
    }
}
