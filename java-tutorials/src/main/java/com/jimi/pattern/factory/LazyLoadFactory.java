package com.jimi.pattern.factory;

import java.util.HashMap;
import java.util.Map;

public class LazyLoadFactory {
    private static  final Map<String,Product> productMap = new HashMap<>();

    public static synchronized Product createProduct(String productType){
        Product product = null;

        if(productMap.containsKey(productType)){
            product = productMap.get(productType);
        }else{

            if("productA".equals(productType)){
                product = new ProductA();
            }else{
                product = new ProductB();
            }
            productMap.put(productType,product);
        }

        return product;
    }

}
