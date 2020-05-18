package com.jimi.pattern.intermediary.optimization;

import com.jimi.pattern.intermediary.Purchase;
import com.jimi.pattern.intermediary.Stock;

import java.util.Random;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 15:38
 */
public class OPSaler extends AbstractColeague{


    public OPSaler(AbstractMediator mediator) {
        super(mediator);
    }

    public void sellIBMComputer(int number){

        System.out.println("销售IBM 电脑"+number+"台");
        super.mediator.execute("sale.sell",number);
    }

    public int getSaleStaus() {
        Random random = new Random(System.currentTimeMillis());

        int saleStatus = random.nextInt(100);
        System.out.println("IBM电脑的销售情况为 "+saleStatus);

        return saleStatus;
    }

    public void offSale() {
       super.mediator.execute("sale.offsell");
    }
}
