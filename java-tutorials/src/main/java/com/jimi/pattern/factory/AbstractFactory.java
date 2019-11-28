package com.jimi.pattern.factory;

public abstract class AbstractFactory {
    /**
     * 定义一个输入产品参数就生成一个相应种类的产品；
     * @return
     */
    public abstract Product createProduct();
}
