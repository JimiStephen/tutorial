package com.jimi.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *  VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @program: tutorial
 * @description:
 * @author: jimi
 * @create: 2023-04-03 22:37
 **/
public class HeapOOM {

    static class OOMObject{

    }

    public static void main(String[] args) {

        List<OOMObject> lists = new ArrayList<OOMObject>();
        while (true){

            lists.add(new OOMObject());
        }
    }
}
