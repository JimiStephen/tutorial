package com.jimi.pattern.intermediary;

import javax.print.attribute.standard.NumberUp;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 15:38
 */
public class Stock {

    //起始库存
    private static int COMPUTER_NUM = 100;

    public void increase(int number) {
        COMPUTER_NUM = COMPUTER_NUM + number;

        System.out.println("库存量为：" + COMPUTER_NUM);
    }
    public void decrease(int number) {
        COMPUTER_NUM = COMPUTER_NUM - number;

        System.out.println("库存量为：" + COMPUTER_NUM);
    }
    public int getStockNum() {
        return COMPUTER_NUM;
    }
    public void clearStock() {
        Purchase purchase = new Purchase();

        Sale sale = new Sale();

        System.out.println("清理的库存量是 ：" + COMPUTER_NUM);

        sale.offSale();

        purchase.refuseBuyIBM();
    }
}
