package com.jimi.pattern.factory;

public abstract class AbstractWhiteMan implements IHuman {

    @Override
    public void getColor() {
        System.out.println("我是白种人");
    }

    @Override
    public void getLanguage() {
        System.out.println("说的是英文");
    }
}
