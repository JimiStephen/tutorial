package com.jimi.pattern.decorator;

/**
 *
 *  调料
 * @author jimi
 * @version 1.0
 * @date 2020/5/26 14:09
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() +", Mocha";
    }

    @Override
    public double cost() {
        return 0.2 + beverage.cost();
    }
}
