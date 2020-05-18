package com.jimi.pattern.intermediary;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 15:33
 */
public class Purchase {

    public void buyIBMPc(int number){
        Stock stock =  new Stock();

        Sale sale = new Sale();

        int saleStatus = sale.getSaleStaus();

        if(saleStatus > 80){
            System.out.println("采购IBM 电脑："+ number + "台");
            stock.increase(number);
        }else {

            int buyNumber = number/2; //折半采购
            System.out.println("折半采购IBM 电脑："+ buyNumber + "台");
            stock.increase(buyNumber);
        }

    }

    public void refuseBuyIBM(){
        System.out.println("不再采购IBM电脑");
    }

}
