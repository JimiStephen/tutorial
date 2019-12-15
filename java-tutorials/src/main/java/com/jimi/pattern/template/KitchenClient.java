package com.jimi.pattern.template;

/**
 * 开始做菜
 */
public class KitchenClient {
    public static void main(String[] args) {
        BraisedFishCook braisedFishCook = new BraisedFishCook();
        PotstewedPigFeetCook potstewedPigFeetCook = new PotstewedPigFeetCook();

        braisedFishCook.cookForADish();
        System.out.println("---------------------");
        potstewedPigFeetCook.cookForADish();
    }
}
