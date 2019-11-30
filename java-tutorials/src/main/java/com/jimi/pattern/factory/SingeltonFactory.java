package com.jimi.pattern.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingeltonFactory {
    public  static Singelton singleton;

    static {

        try {
            //获取类
            Class cls = Class.forName(Singelton.class.getName());
            //获取单例的构造器
            Constructor constructor = cls.getDeclaredConstructor();
            //设置访问
            constructor.setAccessible(true);
            //创建实例
            singleton = (Singelton) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static Singelton getSingleton() {
        return singleton;
    }
}
