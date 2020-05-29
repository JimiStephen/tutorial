package com.jimi.pattern.strategy.demo;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:25
 */
public class TestCaculator {

    public static void main(String[] args) {
        int a = 5;
        int b  = 10;

        String symbol = "+";

        Caculator caculator = new Caculator();

       int result =  caculator.exec(a,b,symbol);

        System.out.println("执行结果是："+ result);

    }

}
