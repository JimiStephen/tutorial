package com.jimi.pattern.proxy;

/**
 * 强代理的实现，在被代理人，可以关联指定的代理人才可以进行操作；否则不能进行操作；
 * 代理也可以是有一自己的特性的。用不同的接口来实现；
 * 虚拟代理
 * 动态代理
 *
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/9 10:36
 */
public class TestForceProxyMain {

    public static void main(String[] args) {
        poxyDrive();

        poxyAppointDrive();
    }


    private static void poxyDrive() {
        IForceDriver driver = new ForceDriver("jimi");

        IForceDriver proxyDirver = new SubsititudeForceDriver(driver);
        proxyDirver.setCarKey();
        proxyDirver.startUpCar();
        proxyDirver.drivingCar();
        proxyDirver.stopCar();
    }
    private static void poxyAppointDrive() {
        IForceDriver driver = new ForceDriver("jimi");

        IForceDriver proxyDirver = driver.getProxy();
        proxyDirver.setCarKey();
        proxyDirver.startUpCar();
        proxyDirver.drivingCar();
        proxyDirver.stopCar();
    }
}
