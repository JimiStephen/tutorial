package com.jimi.javase.lang;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/6/8 10:27
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s1 == s5.intern());
        System.out.println(s2 == s2.intern());
        System.out.println(s5 == s6);

//        false
//        true
//        false
//        true
//        true
//        false
//        false
    }
}
