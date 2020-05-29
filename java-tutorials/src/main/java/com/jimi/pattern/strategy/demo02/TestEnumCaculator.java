package com.jimi.pattern.strategy.demo02;

import com.jimi.pattern.strategy.demo01.CaculatorContext;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:44
 */
public class TestEnumCaculator {

    private static final String ADD_SYMBOL = "+";
    private static final String SUB_SYMBOL = "-";

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        String symbol = "-";
        int result = 0;

        CaculatorContext caculatorContext = null;
        if (ADD_SYMBOL.equals(symbol)) {
            result = EnumCaculator.ADD.exec(a,b);
        } else if (SUB_SYMBOL.equals(symbol)) {

            result = EnumCaculator.SUB.exec(a,b);
        }


        System.out.println("执行结果是：" + result);
    }
}
