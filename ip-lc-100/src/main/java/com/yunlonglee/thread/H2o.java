package com.yunlonglee.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 25/10/21 6:20 下午
 */
public class H2o {
    /**
     * h重入竞争锁
     */
    private ReentrantLock numberLock = new ReentrantLock();

    /**
     * 重入锁释放条件
     */
    private Condition condition = numberLock.newCondition();
    /**
     * h数量(临界资源)
     */
    private static Integer hCnt = 1;


    public static void main(String[] args) {
//        String originStr = "HOH";
//        List<String> origins = Splitter.on("").splitToList(originStr);

    }


    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        numberLock.lock();
        try {
            while (hCnt == 0) {
                condition.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            if (hCnt == 1) {
                hCnt++;
            } else if (hCnt == 2) {
                hCnt = 0;
            }
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            numberLock.unlock();
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        numberLock.lock();
        try {
            if (hCnt == 2 || hCnt == 1) {
                condition.await();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hCnt = 1;
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            numberLock.unlock();
        }


    }
}
