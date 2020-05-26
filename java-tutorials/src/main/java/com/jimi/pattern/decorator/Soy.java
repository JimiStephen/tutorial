package com.jimi.pattern.decorator;

/**
 *
 * 调料 soy
 * @author jimi
 * @version 1.0
 * @date 2020/5/26 14:01
 */
public class Soy  extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() +", Soy";
    }

    @Override
    public double cost() {
        return 0.2 + beverage.cost();
    }
}
