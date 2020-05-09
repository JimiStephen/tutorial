package com.jimi.pattern.proxy;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/5/9 10:27
 */
public class SubsititudeDriver implements IDriver {

    private IDriver driver = null;

    public SubsititudeDriver(IDriver driver){
        this.driver = driver;
    }

    @Override
    public void setCarKey() {

        this.driver.setCarKey();
    }

    @Override
    public void startUpCar() {
        this.driver.startUpCar();
    }

    @Override
    public void drivingCar() {
        this.driver.drivingCar();
    }

    @Override
    public void stopCar() {
        this.driver.stopCar();
    }
}
