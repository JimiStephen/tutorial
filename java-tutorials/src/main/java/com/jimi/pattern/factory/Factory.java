package com.jimi.pattern.factory;

public abstract class Factory {

    /**
     * 定义一个输入产品参数就生成一个相应种类的产品；
     * @param c
     * @param <T>
     * @return
     */
    public abstract <T extends Product> T createProduct(Class<T> c);
}
