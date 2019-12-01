package com.jimi.pattern.factory;

public class MaleFactory implements IHumanFactory {
    @Override
    public IHuman createYellowMan() {
        return new MaleYellowMan();
    }

    @Override
    public IHuman createWhiteMan() {
        return new MaleWiteMan();
    }
}
