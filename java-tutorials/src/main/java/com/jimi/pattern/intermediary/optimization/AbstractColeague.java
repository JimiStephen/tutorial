package com.jimi.pattern.intermediary.optimization;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/18 16:14
 */
public abstract class AbstractColeague {


    protected AbstractMediator mediator;

    public AbstractColeague(AbstractMediator mediator) {
        this.mediator = mediator;
    }
}
