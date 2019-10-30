package com.jimi.pattern.singleton;

/**
 * 懒汉式单例模式
 */
public class LazySingleton {

    private static LazySingleton singleton = null;

    //限制产生多个实例
    private LazySingleton() {

    }

    //对外提供获取实例的方法；
    public static LazySingleton getInstance() {
        if(singleton == null){
            singleton = new LazySingleton();
        }
        return singleton;
    }

    //该实例拥有的功能；
    public static void doSometing() {

    }
}
