package com.jimi.pattern.strategy.demo;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:19
 */
public class Caculator {

    private static final String ADD_SYMBOL = "+";
    private static final String SUB_SYMBOL = "-";

    public int exec(int a, int b, String symbol) {
        int result = 0;


        if (ADD_SYMBOL.equals(symbol)) {
            result = this.add(a, b);
        } else if (SUB_SYMBOL.equals(symbol)) {
            result = this.sub(a, b);
        }
        return result;
    }

    private int sub(int a, int b) {
        return a - b;
    }

    private int add(int a, int b) {
        return a + b;
    }

}
