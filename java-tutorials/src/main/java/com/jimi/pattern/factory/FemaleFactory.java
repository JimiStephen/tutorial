package com.jimi.pattern.factory;

public class FemaleFactory implements IHumanFactory {
    @Override
    public IHuman createYellowMan() {
        return new FemaleYellowMan();
    }

    @Override
    public IHuman createWhiteMan() {
        return new FemaleWiteMan();
    }
}
