package com.jimi.pattern.template;

/**
 * 这是一个烧菜的程序
 */
public abstract class AbsCook {

    /**
     * 准备材料
     */
    public abstract void readyForMaterial();

    /**
     * 清洗材料
     */
    public abstract void washForMaterial();

    /**
     * 切好材料
     */
    public abstract void cutForMaterial();

    /**
     * 对材料烹调
     */
    public abstract void cook();

    /**
     * 开始做一个道菜
     */
    public void cookForADish(){
        this.readyForMaterial();
        this.washForMaterial();
        this.cutForMaterial();
        this.cook();
    }


}
