package com.jimi.pattern.intermediary.optimization;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 15:38
 */
public class OPStock extends AbstractColeague {

    //起始库存
    private static int COMPUTER_NUM = 100;

    public OPStock(AbstractMediator mediator) {
        super(mediator);
    }

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
        System.out.println("清理的库存量是 ：" + COMPUTER_NUM);
        super.mediator.execute("stock.clear");
    }
}
