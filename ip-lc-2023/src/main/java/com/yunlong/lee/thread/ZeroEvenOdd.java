package com.yunlong.lee.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.IntConsumer;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 28/2/23 6:44 下午
 * @link https://leetcode.cn/problems/print-zero-even-odd/
 */
public class ZeroEvenOdd {
    private static ReentrantLock LOCK = new ReentrantLock();

    private static Condition ZERO_COND = LOCK.newCondition();
    private static Condition ODD_EVEN_COND = LOCK.newCondition();
    private static Condition EVEN_COND = LOCK.newCondition();
    private static Condition ODD_COND = LOCK.newCondition();

    private static String ZERO_PRINT = "ZERO_PRINT";
    private static String ODD_EVEN_PRINT = "ODD_EVEN_PRINT";
    private static String NUM_PRINT = "NUM_PRINT";


    private static String EVEN_PRINT = "EVEN_PRINT";
    private static String ODD_PRINT = "ODD_PRINT";

    private volatile String PRINT_FLAG = "ZERO_PRINT";
    private volatile String NUM_FLAG = "ODD_PRINT";

    private int curNum = 0;


    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        LOCK.lock();
        try {
            while (PRINT_FLAG != ZERO_PRINT) {
                ZERO_COND.await();
            }
            printNumber.accept(curNum);
            PRINT_FLAG = ODD_EVEN_PRINT;
            ODD_EVEN_COND.signal();
        } finally {
            LOCK.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        LOCK.lock();
        try {
            while (PRINT_FLAG == ODD_EVEN_PRINT) {
                if (NUM_FLAG == ODD_PRINT) {
                    break;
                }
                while (NUM_FLAG != ODD_PRINT) {
                    ODD_COND.await();
                }
            }
            printNumber.accept(curNum);
            PRINT_FLAG = ZERO_PRINT;
            NUM_FLAG = EVEN_PRINT;
            ZERO_COND.signal();
            EVEN_COND.signal();
        } finally {
            LOCK.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        LOCK.lock();
        try {
            while (PRINT_FLAG == ODD_EVEN_PRINT) {
                if (NUM_FLAG == EVEN_PRINT) {
                    break;
                }
                while (NUM_FLAG != EVEN_PRINT) {
                    EVEN_COND.await();
                }
            }
            printNumber.accept(curNum);
            PRINT_FLAG = ZERO_PRINT;
            NUM_FLAG = ODD_PRINT;
            ZERO_COND.signal();
            ODD_COND.signal();
        } finally {
            LOCK.unlock();
        }
    }





    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        for (int i = 1; i <= zeroEvenOdd.n; i++) {
            try {
                zeroEvenOdd.curNum = 0;
                zeroEvenOdd.zero(printFunc);
                zeroEvenOdd.curNum = i;
                if (i % 2 == 0) {
                    zeroEvenOdd.even(printFunc);
                } else {
                    zeroEvenOdd.odd(printFunc);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static IntConsumer printFunc = System.out::print;


}
