package com.jimi.pattern.intermediary.optimization;


/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 15:52
 */
public class TestOPMis {

    public static void main(String[] args) {

        AbstractMediator mediator = new Mediator();
        System.out.println("----------------采购人员采购电脑-------------------");

        OPPurchase purchase = new OPPurchase(mediator);
        purchase.buyIBMPc(100);

        System.out.println("----------------销售人员销售电脑-------------------");
        OPSaler sale = new OPSaler(mediator);
        sale.sellIBMComputer(2);

        System.out.println("----------------仓库人员清理库存-------------------");
        OPStock stock = new OPStock(mediator);
        stock.clearStock();

    }
}
