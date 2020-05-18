package com.jimi.pattern.intermediary;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 15:52
 */
public class TestMis {

    public static void main(String[] args) {
        System.out.println("----------------采购人员采购电脑-------------------");

        Purchase purchase = new Purchase();
        purchase.buyIBMPc(100);

        System.out.println("----------------销售人员销售电脑-------------------");
        Sale sale = new Sale();
        sale.sellIBMComputer(2);

        System.out.println("----------------仓库人员清理库存-------------------");
        Stock stock = new Stock();
        stock.clearStock();

    }
}
