package com.jimi.pattern.strategy.demo01;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:34
 */
public class TestICaculator {
    private static final String ADD_SYMBOL = "+";
    private static final String SUB_SYMBOL = "-";

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        String symbol = "-";
        int result = 0;

        CaculatorContext caculatorContext = null;
        if (ADD_SYMBOL.equals(symbol)) {
            caculatorContext = new CaculatorContext(new AddCaculator());
        } else if (SUB_SYMBOL.equals(symbol)) {
            caculatorContext = new CaculatorContext(new SubCaculator());
        }

        result = caculatorContext.exec(a, b, symbol);

        System.out.println("执行结果是：" + result);
    }
}
