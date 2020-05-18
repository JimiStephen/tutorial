package com.jimi.pattern.proxy.dynamic;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/14 9:43
 */
public class BeforeAdvice implements IAdvice {

    @Override
    public void exec() {
        System.out.println("前置通知，你现在正在执行方法之前");
    }
}
