package com.jimi.javase.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author jimi
 * @version 1.0
 * @date 2020/6/9 15:11
 */
public class CountdownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        try {
            new Thread(){public void run() {
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
            };}.start();
            new Thread(){ public void run() {
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
            };}.start();
            System.out.println("等待 2 个子线程执行完毕...");
            latch.await();
            System.out.println("2 个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
