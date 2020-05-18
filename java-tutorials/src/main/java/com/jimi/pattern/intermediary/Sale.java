package com.jimi.pattern.intermediary;

import java.util.Random;
import java.util.Stack;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 15:38
 */
public class Sale {


    public void sellIBMComputer(int number){
        Stock stock = new Stock();

        Purchase purchase = new Purchase();

        if(stock.getStockNum() < number){
            purchase.buyIBMPc(number);
        }

        System.out.println("销售IBM 电脑"+number+"台");

        stock.decrease(number);
    }

    public int getSaleStaus() {
        Random random = new Random(System.currentTimeMillis());

        int saleStatus = random.nextInt(100);
        System.out.println("IBM电脑的销售情况为 "+saleStatus);

        return saleStatus;
    }

    public void offSale() {
        Stock stock = new Stock();
        System.out.println("折价销售IBM电脑 " +stock.getStockNum()+"台");
    }
}
