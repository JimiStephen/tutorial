package com.jimi.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/14 9:45
 */
public class DynamicProxy<T> {

    public static <T> T newProxyInstance(ClassLoader classLoader, Class<?>[] interfaces, InvocationHandler invocationHandler){
        if (true){
            (new BeforeAdvice()).exec();
        }

        return (T) Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }
}
