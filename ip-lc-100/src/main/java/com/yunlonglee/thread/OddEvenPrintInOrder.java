package com.yunlonglee.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijie
 * @version 1.0
 * @description 两个线程，一个打印奇数，一个打印偶数，最后按序打印
 * @date 23/2/22 1:50 上午
 */
public class OddEvenPrintInOrder {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //这里一定要是volatile
    private volatile int i = 1;

    private void oddPrint() {
        while (i < 100) {
            lock.lock();
            try {
                if (i % 2 == 0) {
                    //condition.await/signal方法一定在lock/unlock之间，
                    //因为await一个会释放锁，signal一个会检查是否持有锁
                    condition.await();
                }
                if(i<=100){
                    System.out.println(i);
                }
                i++;
                condition.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }

    }

    private void evenPrint() {
        while (i < 100) {
            lock.lock();
            try {
                if (i % 2 != 0) {
                    condition.await();
                }
                if(i<=100){
                    System.out.println(i);
                }

                i++;
                condition.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }

    }

    private void doPrint() {
        Thread tOdd = new Thread(() -> oddPrint());
        Thread tEven = new Thread(() -> evenPrint());

        tEven.start();
        tOdd.start();


    }

    public static void main(String[] args) {
        OddEvenPrintInOrder printInOrder = new OddEvenPrintInOrder();
        printInOrder.doPrint();
    }
}
