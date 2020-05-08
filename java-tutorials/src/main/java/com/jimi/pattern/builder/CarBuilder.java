package com.jimi.pattern.builder;

import java.util.List;

public abstract class CarBuilder {

    public abstract void setRunSeqs(List<String> runSeqs);

    public abstract CarModel getCarModel();
}
