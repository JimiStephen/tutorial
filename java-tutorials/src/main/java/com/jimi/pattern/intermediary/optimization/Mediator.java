package com.jimi.pattern.intermediary.optimization;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/18 16:19
 */
public class Mediator extends AbstractMediator {

    @Override
    public void execute(String str, Object... objects) {

        if(str.equals("purchase.buy")){
            this.buyComputer((Integer) objects[0]);
        }else if(str.equals("sale.sell")){
            this.sellComputer((Integer) objects[0]);
        }else if(str.equals("sale.offsell")){
            this.offSellComputer();
        }else if(str.equals("stock.clear")){
            this.clearStock();
        }


    }

    private void buyComputer(Integer number) {
        int saleStatus = super.saler.getSaleStaus();

        if(saleStatus > 80){
            System.out.println("采购IBM 电脑："+ number + "台");
            super.stock.increase(number);
        }else {

            int buyNumber = number/2; //折半采购
            System.out.println("折半采购IBM 电脑："+ buyNumber + "台");
            super.stock.increase(buyNumber);
        }
    }


    private void sellComputer(Integer num) {
        //先采购再进行销售
        if (super.stock.getStockNum() < num){
            super.purchase.buyIBMPc(num);
        }
            super.stock.decrease(num);


    }
    private void offSellComputer() {
        System.out.println("折价销售IBM电脑" + super.stock.getStockNum() + "台");

    }
    private void clearStock() {


        super.saler.offSale();

        super.purchase.refuseBuyIBM();

    }

}
