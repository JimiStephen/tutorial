package com.jimi.pattern.factory;

public abstract class  AbstractYellowMan implements IHuman {

    @Override
    public void getColor() {
        System.out.println("我是黄种人");
    }

    @Override
    public void getLanguage() {
        System.out.println("说的是中国话");
    }
}
