package com.jimi.pattern.builder;

import java.util.Arrays;
import java.util.List;

public class TestCarModel {

    public static void main(String[] args) {
        CarModel benzModel = new BenzModel();

        List<String> runSeqs = Arrays.asList("alarm","run","engineBoom","stop");

        benzModel.setRunSequences(runSeqs);

        benzModel.run();
    }
}
