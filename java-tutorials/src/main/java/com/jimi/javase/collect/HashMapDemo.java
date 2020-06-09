package com.jimi.javase.collect;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  jimi
 * @version 1.0
 * @date 2020/6/1 16:22
 */
public class HashMapDemo {

    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < 100000; i++) {
            map.put(i,i);
        }


        System.out.println(map);
    }
}
