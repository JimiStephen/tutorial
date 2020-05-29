package com.jimi.pattern.strategy;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:14
 */
public class StrategyContext {

    private IStrategy iStrategy = null;

    public StrategyContext(IStrategy iStrategy) {
        this.iStrategy = iStrategy;
    }

    public void doAnyThing(){
        this.iStrategy.doSomeThing();
    }
}
