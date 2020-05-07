package com.jimi.pattern.builder;

import java.util.ArrayList;
import java.util.List;


/**
 * 汽车模型的定义
 */
public abstract class CarModel {

    private List<String> runSequences = new ArrayList<>();

    public abstract void start();

    public abstract void stop();

    public abstract void alarm();

    public abstract void engineBoom();

    public void run() {

        for (int i = 0; i < runSequences.size(); i++) {
            String action = runSequences.get(i);
            if ("start".equals(action)) {
                this.start();
            } else if ("stop".equals(action)) {
                this.stop();
            } else if ("alarm".equals(action)) {
                this.alarm();
            } else if ("engineBoom".equals(action)) {
                this.engineBoom();
            }
        }


    }

    public final void setRunSequences(List<String> runSequences) {
        this.runSequences = runSequences;
    }
}
