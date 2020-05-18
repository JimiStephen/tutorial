package com.jimi.pattern.intermediary.optimization;


/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 15:33
 */
public class OPPurchase extends AbstractColeague {

    public OPPurchase(AbstractMediator mediator) {
        super(mediator);
    }

    public void buyIBMPc(int number){
        super.mediator.execute("purchase.buy",number);
    }

    public void refuseBuyIBM(){
        System.out.println("不再采购IBM电脑");
    }

}
