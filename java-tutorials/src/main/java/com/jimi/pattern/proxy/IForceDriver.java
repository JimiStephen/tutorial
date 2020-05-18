package com.jimi.pattern.proxy;

/**
 *
 * 司机接口
 * @author jimi
 * @version 1.0
 * @date 2020/5/9 10:23
 */
public interface IForceDriver {

    public void setCarKey();

    public void startUpCar();

    public void drivingCar();

    public void stopCar();

    //force proxy
    public IForceDriver getProxy();
}
