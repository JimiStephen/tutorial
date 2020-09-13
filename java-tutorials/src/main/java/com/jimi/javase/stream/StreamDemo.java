package com.jimi.javase.stream;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StreamDemo<E> {

    public void streamCollector(List<E> list){

        Supplier<Object> supplier = new Supplier<Object>() {
            @Override
            public Object get() {
                return new Object();
            }
        };
        BiConsumer<Object, E> accumulator =new BiConsumer<Object, E>() {
            @Override
            public void accept(Object o, E e) {

            }
        };
        BinaryOperator<Object> combiner = new BinaryOperator<Object>() {
            @Override
            public Object apply(Object o, Object o2) {
                return null;
            }
        };
        Collector.Characteristics finisher = null;
        list.stream().collect(Collector.of(supplier,accumulator,combiner,finisher));
    }
}
