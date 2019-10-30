package com.jimi.pattern.singleton;

/**
 * 饿汉式单例模式
 */
public class Singleton {

    private static final Singleton singleton = new Singleton();

    //限制产生多个实例
    private Singleton() {

    }

    //对外提供获取实例的方法；
    public static Singleton getInstance() {
        return singleton;
    }

    //该实例拥有的功能；
    public static void doSometing() {

    }
}
