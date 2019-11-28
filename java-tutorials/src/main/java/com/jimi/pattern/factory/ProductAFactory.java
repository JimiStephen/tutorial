package com.jimi.pattern.factory;

public class ProductAFactory extends AbstractFactory{

    @Override
    public Product createProduct() {
        return new ProductA();
    }
}
