package com.jimi.pattern.strategy;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:11
 */
public class ConcreateStrategy2 implements IStrategy {

    @Override
    public void doSomeThing() {
        System.out.println("策略2 的执行方法");
    }
}
