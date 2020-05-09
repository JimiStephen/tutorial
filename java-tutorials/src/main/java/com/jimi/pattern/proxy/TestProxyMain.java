package com.jimi.pattern.proxy;

/**
 *
 *
 * 强代理的实现，在被代理人，可以关联指定的代理人才可以进行操作；否则不能进行操作；
 * 代理也可以是有一自己的特性的。用不同的接口来实现；
 * 虚拟代理
 * 动态代理
 *
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/9 10:36
 */
public class TestProxyMain {

    public static void main(String[] args) {
        poxyDrive();
    }

    private static void noPoxyDrive() {
        IDriver driver = new Driver();

        driver.setCarKey();
        driver.startUpCar();
        driver.drivingCar();
        driver.stopCar();
    }

    private static void poxyDrive() {
        IDriver driver = new Driver();

        IDriver proxyDirver = new SubsititudeDriver(driver);
        proxyDirver.setCarKey();
        proxyDirver.startUpCar();
        proxyDirver.drivingCar();
        proxyDirver.stopCar();
    }
}
