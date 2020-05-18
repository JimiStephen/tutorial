package com.jimi.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.util.Objects;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/14 10:10
 */
public class SubjectDynamicProxy extends  DynamicProxy {

    public static <T> T newProxyInstance(ISubject subject){
        Objects.requireNonNull(subject);
        ClassLoader loader = subject.getClass().getClassLoader();
        Class<?> [] interfaces = subject.getClass().getInterfaces();

        InvocationHandler handler = new DynamicInvocationHandle(subject);

        return newProxyInstance(loader,interfaces,handler);
    }
}
