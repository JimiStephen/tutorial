package com.jimi.pattern.builder;

import java.util.List;

public class BMWBuilder extends CarBuilder {
    private BMWModel bmwModel = new BMWModel();

    @Override
    public void setRunSeqs(List<String> runSeqs) {
        this.bmwModel.setRunSequences(runSeqs);
    }

    @Override
    public CarModel getCarModel() {
        return this.bmwModel;
    }
}
