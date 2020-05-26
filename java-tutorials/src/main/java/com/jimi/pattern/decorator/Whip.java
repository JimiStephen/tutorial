package com.jimi.pattern.decorator;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/26 14:01
 */
public class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() +", Whip";
    }

    @Override
    public double cost() {
        return 0.2 + beverage.cost();
    }
}
