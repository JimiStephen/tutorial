package com.jimi.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/14 10:05
 */
public class TestDynomicProxy {


    public static void main(String[] args) {
        optimizationDynomicProxy();
    }

    private static void primaryDynomicProxy(){
        ISubject subject = new RealSubject();

        InvocationHandler handler = new DynamicInvocationHandle(subject);

        ISubject proxy = DynamicProxy.newProxyInstance(subject.getClass().getClassLoader(),subject.getClass().getInterfaces(),handler);

        proxy.doWork("jimi");
    }

    private static void optimizationDynomicProxy(){
        ISubject subject = new RealSubject();
        ISubject proxy =  SubjectDynamicProxy.newProxyInstance(subject);

        proxy.doWork("jack");
    }

}
