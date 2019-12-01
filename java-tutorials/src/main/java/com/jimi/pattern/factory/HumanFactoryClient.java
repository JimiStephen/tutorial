package com.jimi.pattern.factory;

public class HumanFactoryClient {
    public static void main(String[] args) {

        MaleFactory maleFactory = new MaleFactory();
        FemaleFactory femaleFactory = new FemaleFactory();

        IHuman maleYellowMan = maleFactory.createYellowMan();

        IHuman femaleYellowMan = femaleFactory.createYellowMan();


        maleYellowMan.getColor();
        maleYellowMan.getLanguage();
        maleYellowMan.getSex();


        femaleYellowMan.getColor();
        femaleYellowMan.getLanguage();
        femaleYellowMan.getSex();

    }
}
