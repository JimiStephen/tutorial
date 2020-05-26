package com.jimi.pattern.decorator;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/26 14:01
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    //获取饮料的描述
    public String getDescription() {
        return description;
    }

    //饮料的销售价格
    public abstract double cost();
}
