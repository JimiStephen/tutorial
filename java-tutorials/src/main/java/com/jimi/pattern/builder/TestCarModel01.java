package com.jimi.pattern.builder;

import java.util.Arrays;
import java.util.List;

public class TestCarModel01 {

    public static void main(String[] args) {

        List<String> runSeqs = Arrays.asList("alarm","run","engineBoom","stop");

        BenzBuilder benzBuilder = new BenzBuilder();
        benzBuilder.setRunSeqs(runSeqs);
        BenzModel benzModel = (BenzModel) benzBuilder.getCarModel();
        benzModel.run();


        BMWBuilder bmwBuilder = new BMWBuilder();
        bmwBuilder.setRunSeqs(runSeqs);
        BMWModel bmwModel = (BMWModel) bmwBuilder.getCarModel();
        bmwModel.run();
    }
}
