package com.jimi.pattern.strategy.demo02;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:40
 */
public enum EnumCaculator {

    ADD("+") {
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },
    SUB("+") {
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    },
    ;

    private String value = "";

    EnumCaculator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract int exec(int a, int b);
}
