package com.jimi.pattern.strategy;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:14
 */
public class TestStrategyMain {

    public static void main(String[] args) {
        IStrategy strategy = new ConcreateStrategy1();

        StrategyContext context = new StrategyContext(strategy);

        context.doAnyThing();
    }

}
