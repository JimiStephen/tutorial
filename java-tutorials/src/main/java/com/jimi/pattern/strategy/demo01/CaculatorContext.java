package com.jimi.pattern.strategy.demo01;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/29 14:32
 */
public class CaculatorContext {

    private ICaculator cal = null;

    public CaculatorContext(ICaculator cal) {
        this.cal = cal;
    }

    public int exec (int a,int b ,String symbol){
        return this.cal.exec(a,b);
    }
}
