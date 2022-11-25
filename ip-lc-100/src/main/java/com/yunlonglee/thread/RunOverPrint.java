package com.yunlonglee.thread;

import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

/**
 * @author lijie
 * @version 1.0
 * @description 5个线程都跑完打印日志
 * @date 23/2/22 12:57 上午
 */
public class RunOverPrint {
    private static CountDownLatch latch = new CountDownLatch(5);
    public static void runThread1() {
        for (int i = 0; i < 5; i++) {
            final int m = i;
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread " + m + " is done");
                latch.countDown();
            }
            ).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        runThread1();
        System.out.println("5 thread run over,log is printed");

    }


    public static void runThread() {
        Thread[] t = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int m = i;
            t[i] = new Thread(() -> System.out.println("thread " + m + " is done"));
            t[i].start();
        }
        for (int i = 0; i < 5; i++) {
            try {
                //每个子线程阻塞主线程
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


