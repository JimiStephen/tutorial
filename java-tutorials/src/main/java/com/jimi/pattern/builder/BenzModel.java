package com.jimi.pattern.builder;

/**
 * 奔驰模型的实现
 */
public class BenzModel extends CarModel {
    @Override
    public void start() {
        System.out.println("这是奔驰在启动");
    }

    @Override
    public void stop() {
        System.out.println("这是奔驰在刹车");

    }

    @Override
    public void alarm() {
        System.out.println("这是奔驰的喇叭声音 嘟 嘟 嘟");
    }

    @Override
    public void engineBoom() {
        System.out.println("这是奔驰引擎在启动 哼 哼 哼");
    }
}
