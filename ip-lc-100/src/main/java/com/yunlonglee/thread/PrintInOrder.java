package com.yunlonglee.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijie
 * @version 1.0
 * @description 1114. 按序打印
 * @date 16/2/22 3:07 上午
 */
public class PrintInOrder {

    private int threadFlag = 1;

    ReentrantLock lock = new ReentrantLock();
    Condition firstRunCond = lock.newCondition();
    Condition secondRunCond = lock.newCondition();
    Condition thirdRunCond = lock.newCondition();


    public void first(Runnable printFirst) throws InterruptedException {
        try {
            lock.lock();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            threadFlag = 2;
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        try {
            lock.lock();
            if (threadFlag != 2) {
                secondRunCond.await();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            Thread.sleep(1000);
            printSecond.run();
            threadFlag = 3;
            thirdRunCond.signal();
        } finally {
            lock.unlock();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {

        try {
            lock.lock();
            if (threadFlag != 3) {
                thirdRunCond.await();
            }
            printThird.run();
        } finally {
            lock.unlock();
        }




    }

    public static void main(String[] args) {
        Runnable printFirst = () -> System.out.println("first");
        Runnable print2nd = () -> System.out.println("second");
        Runnable print3rd = () -> System.out.println("third");

        PrintInOrder printInOrder = new PrintInOrder();

        new Thread(() -> {
            try {
                printInOrder.first(printFirst);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                printInOrder.second(print2nd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                printInOrder.third(print3rd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
