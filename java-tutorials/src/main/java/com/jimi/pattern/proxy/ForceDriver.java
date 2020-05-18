package com.jimi.pattern.proxy;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/5/9 10:27
 */
public class ForceDriver implements IForceDriver {


    private String name;

    private IForceDriver proxy;

    public ForceDriver(String name) {
        this.name = name;
    }

    @Override
    public void setCarKey() {
        if (isProxy()) {
            System.out.println("拿到车钥匙");
        } else {
            System.out.println("请叫你的专职司机！");
        }
    }

    @Override
    public void startUpCar() {
        if (isProxy()) {
            System.out.println("启动汽车");
        } else {
            System.out.println("请叫你的专职司机！");
        }
    }

    @Override
    public void drivingCar() {
        if (isProxy()) {
            System.out.println("开着汽车回家");
        } else {
            System.out.println("请叫你的专职司机！");
        }
    }

    @Override
    public void stopCar() {
        if (isProxy()) {
            System.out.println("停车到家");

        } else {
            System.out.println("请叫你的专职司机！");
        }
    }

    @Override
    public IForceDriver getProxy() {
        this.proxy  = new SubsititudeForceDriver(this);
        return this.proxy;
    }

    private boolean isProxy() {

        if (this.proxy == null) {
            return false;
        } else {
            return true;
        }

    }
}
