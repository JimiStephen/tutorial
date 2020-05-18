package com.jimi.pattern.intermediary.optimization;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 16:14
 */
public abstract  class AbstractMediator {
    protected OPPurchase purchase;

    protected OPSaler saler;

    protected OPStock stock;

    public AbstractMediator(){
        purchase = new OPPurchase(this);
        saler = new OPSaler(this);
        stock = new OPStock(this);
    }

    public abstract void execute(String str ,Object ... objects);

}
