package com.jimi.pattern.strategy.demo01;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:29
 */
public class AddCaculator implements ICaculator {

    public int exec(int a, int b) {
        return a + b;
    }

}
