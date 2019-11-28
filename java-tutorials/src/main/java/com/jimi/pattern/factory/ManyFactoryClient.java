package com.jimi.pattern.factory;

public class ManyFactoryClient {
    public static void main(String[] args) {

        //生产B产品
        Product productA = new ProductAFactory().createProduct();
        //创建b产品
        Product productB = new ProductBFactory().createProduct();
    }
}
