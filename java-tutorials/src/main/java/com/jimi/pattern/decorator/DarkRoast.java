package com.jimi.pattern.decorator;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/26 14:01
 */
public class DarkRoast extends Beverage {

    //获取饮料的描述
    public DarkRoast() {
        description = "DarkRoast Coffee";
    }

    //饮料的销售价格
    public double cost() {
        return .89;
    }

    ;
    @Override
    public String toString() {
        return "DarkRoast{" +
                "description='" + description + '\'' +
                '}' ;
    }
}
