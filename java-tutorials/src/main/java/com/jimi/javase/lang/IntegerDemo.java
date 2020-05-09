package com.jimi.javase.lang;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/9 16:38
 */
public class IntegerDemo {

    public static void main(String[] args) {

        //表示几进制的字符串
        int num = 128;
        int radix = 12;
        String toString = Integer.toString(num,radix);
        System.out.println(toString);

    }
}
