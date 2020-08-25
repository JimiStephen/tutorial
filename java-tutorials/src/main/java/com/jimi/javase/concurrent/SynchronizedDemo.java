package com.jimi.javase.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/8/25 9:22
 */
public class SynchronizedDemo {

    private static Object lock = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        int threadCount = 500;
        int corePoolSize = 50;
        int maximumPoolSize = 100;
        long keepAliveTime = 1L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(100);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        for (int i = 0; i < threadCount; i++) {
            threadPoolExecutor.submit(() -> {
                addCount();
            });
        }
        System.out.println(count);

    }

    public static void addCount() {

        synchronized (lock) {
            ++count;
        }
    }
}
