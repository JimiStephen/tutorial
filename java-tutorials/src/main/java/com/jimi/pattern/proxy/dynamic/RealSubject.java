package com.jimi.pattern.proxy.dynamic;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/14 10:01
 */
public class RealSubject implements ISubject {

    @Override
    public void doWork(String str) {
        System.out.printf("%s,主题正在执行！！！！！！！",str);
    }
}
