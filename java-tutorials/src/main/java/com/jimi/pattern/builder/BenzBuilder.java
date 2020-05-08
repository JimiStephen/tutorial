package com.jimi.pattern.builder;

import java.util.List;

public class BenzBuilder extends CarBuilder {

    private BenzModel benzModel = new BenzModel();

    @Override
    public void setRunSeqs(List<String> runSeqs) {
        this.benzModel.setRunSequences(runSeqs);
    }

    @Override
    public CarModel getCarModel() {
        return this.benzModel;
    }
}
