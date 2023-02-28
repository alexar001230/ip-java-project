package com.yunlong.lee.thread;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 28/2/23 12:03 下午
 * @link https://leetcode.cn/problems/print-in-order/
 */
public class PrintInOrder {
    public PrintInOrder() {
    }

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition release1stCon = lock.newCondition();
    private static Condition release2ndCon = lock.newCondition();
    private static Condition release3rdCon = lock.newCondition();
    /**
     * 第几个线程结束标识
     */
    private static volatile int NThreadFinishFlag = 0;

    public static void main(String[] args) {

        Runnable print1st = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (NThreadFinishFlag != 0) {
                        try {
                            release1stCon.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("1st run out");
                    NThreadFinishFlag = 1;
                    release2ndCon.signal();
                } finally {
                    lock.unlock();
                }
            }
        };


        Runnable print2nd = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (NThreadFinishFlag != 1) {
                        try {
                            release2ndCon.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("2nd run out");
                    NThreadFinishFlag = 2;
                    release3rdCon.signal();
                } finally {
                    lock.unlock();
                }
            }
        };


        Runnable print3rd = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (NThreadFinishFlag != 2) {
                        try {
                            release3rdCon.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("3rd run out");
                    NThreadFinishFlag = 0;
                    release1stCon.signal();
                } finally {
                    lock.unlock();
                }
            }
        };
        List<Runnable> threads = Arrays.asList(new Runnable[]{print1st, print2nd,
                print3rd});
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(10, 15, 1000,
                TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (Runnable r : threads) {
            executorPool.submit(r);
        }
        executorPool.shutdown();
    }


    public static void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public static void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public static void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
