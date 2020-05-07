package com.jimi.pattern.builder;

/**
 * 宝马模型的实现
 */
public class BMWModel extends CarModel {
    @Override
    public void start() {
        System.out.println("这是宝马在启动");
    }

    @Override
    public void stop() {
        System.out.println("这是宝马在刹车");

    }

    @Override
    public void alarm() {
        System.out.println("这是宝马的喇叭声音 吡 吡 吡");
    }

    @Override
    public void engineBoom() {
        System.out.println("这是宝马引擎在启动 哄 哄 哄");
    }
}
