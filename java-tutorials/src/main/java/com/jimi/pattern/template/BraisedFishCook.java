package com.jimi.pattern.template;

/**
 * 开始做一道红烧鱼
 */
public class BraisedFishCook extends AbsCook{
    @Override
    public void readyForMaterial() {
        System.out.println("准备好一条石斑鱼");
    }

    @Override
    public void washForMaterial() {
        System.out.println("我们把这条鱼清清洗一下");
    }

    @Override
    public void cutForMaterial() {
        System.out.println("切好，片好这条鱼");
    }

    @Override
    public void cook() {
        System.out.println("开始进行红烧！");
    }
}
