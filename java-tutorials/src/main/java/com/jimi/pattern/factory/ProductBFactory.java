package com.jimi.pattern.factory;

public class ProductBFactory extends AbstractFactory{

    @Override
    public Product createProduct() {
        return new ProductB();
    }
}
