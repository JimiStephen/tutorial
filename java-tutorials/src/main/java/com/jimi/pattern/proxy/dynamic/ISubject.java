package com.jimi.pattern.proxy.dynamic;

/**
 *被代理的抽象主题
 *  @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/14 9:59
 */

public interface ISubject {
    //业务逻辑操作；
    public void doWork(String str);
}
