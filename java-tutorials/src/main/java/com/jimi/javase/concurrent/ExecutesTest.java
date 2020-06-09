package com.jimi.javase.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/6/9 9:57
 */
public class ExecutesTest {

    private static int count;

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
//        synCount();
        synCountWithCondition();
    }

    private static void synCount() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int threadCount = 50;

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String hashCode = String.valueOf(this.hashCode());
                    String s = runCount(hashCode);
                    System.out.println(s);
                }
            });
        }
        executorService.shutdown();
    }

    private static void synCountWithCondition() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int threadCount = 50;

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String hashCode = String.valueOf(this.hashCode());
                    String s = runCountWithCondition(hashCode);
                    System.out.println(s);
                }
            });
        }
        executorService.shutdown();
    }

    private static String runCount(String name) {

        String s = null;
        try {
            lock.lock();
            condition.await();
            condition.signal();
            s = "";
            ++count;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s = s + name + ":" + count;
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }

        return s;
    }

    private static String runCountWithCondition(String name) {
        lock.lock();
        String s;
        try {
            s = "";
            ++count;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s = s + name + ":" + count;
        } finally {
            lock.unlock();
        }

        return s;
    }
}
