package com.jimi.pattern.proxy;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/9 10:27
 */
public class Driver implements IDriver {

    @Override
    public void setCarKey() {
        System.out.println("拿到车钥匙");
    }

    @Override
    public void startUpCar() {
        System.out.println("启动汽车");
    }

    @Override
    public void drivingCar() {
        System.out.println("开着汽车回家");
    }

    @Override
    public void stopCar() {
        System.out.println("停车到家");
    }
}
