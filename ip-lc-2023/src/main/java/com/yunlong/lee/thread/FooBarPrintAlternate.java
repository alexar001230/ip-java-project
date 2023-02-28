package com.yunlong.lee.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 28/2/23 5:55 下午
 * @link https://leetcode.cn/problems/print-foobar-alternately/
 */
public class FooBarPrintAlternate {
    public FooBarPrintAlternate(int n) {
        this.n = n;
    }

    private int n;

    private static ReentrantLock Lock = new ReentrantLock();
    private static Condition FOO_PRINT_COND = Lock.newCondition();
    private static Condition BAR_PRINT_COND = Lock.newCondition();

    private static String FOO_PRINT = "FOO_PRINT";
    private static String BAR_PRINT = "BAR_PRINT";

    private static volatile String PRINT_FLAG = FOO_PRINT;


    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            Lock.lock();
            try {
                while (PRINT_FLAG != FOO_PRINT) {
                    FOO_PRINT_COND.await();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                PRINT_FLAG = BAR_PRINT;
                BAR_PRINT_COND.signal();
            } finally {
                Lock.unlock();
            }
        }
    }

    // public void bar(Runnable printBar) throws InterruptedException {
    //     for (int i = 0; i < n; i++) {
    //         Lock.lock();
    //         try {
    //             while (PRINT_FLAG != BAR_PRINT) {
    //                 BAR_PRINT_COND.await();
    //             }
    //             // printBar.run() outputs "bar". Do not change or remove this line.
    //             printBar.run();
    //             PRINT_FLAG = FOO_PRINT;
    //             FOO_PRINT_COND.signal();
    //         } finally {
    //             Lock.unlock();
    //         }
    //
    //     }
    // }


    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            Lock.lock();
            try {
                while (PRINT_FLAG != BAR_PRINT) {
                    BAR_PRINT_COND.await();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                PRINT_FLAG = FOO_PRINT;
                FOO_PRINT_COND.signal();
            } finally {
                Lock.unlock();
            }

        }
    }

    public static void main(String[] args) {
        FooBarPrintAlternate printer = new FooBarPrintAlternate(5);

        Runnable printFoo = new Runnable() {
            @Override
            public void run() {
                ThreadUtils.print("foo");
            }
        };

        Runnable printBar = new Runnable() {
            @Override
            public void run() {
                ThreadUtils.print("bar");
            }
        };

        Runnable fooRunner = new Runnable() {
            @Override
            public void run() {
                try {
                    printer.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable barRunner = new Runnable() {
            @Override
            public void run() {
                try {
                    printer.bar(printBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        ThreadUtils.executeTasks(Arrays.asList(new Runnable[]{
                fooRunner,
                barRunner}));
    }

    private static void printBar() {
        ThreadUtils.print("bar");
    }

    private static void printFoo() {
        ThreadUtils.print("foo");
    }


}
