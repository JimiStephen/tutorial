package com.jimi.pattern.factory;

public class FactoryClient {
    public static void main(String[] args) {
        Factory factory = new ProductFactory();

        Product product = factory.createProduct(ProductA.class);
    }
}
