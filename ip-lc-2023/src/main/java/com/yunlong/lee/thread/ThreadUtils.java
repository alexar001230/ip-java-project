package com.yunlong.lee.thread;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 28/2/23 6:05 下午
 * @link
 */
public class ThreadUtils {
    public static void print(String msg) {
        System.out.print(msg);
    }

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            10, 100, 1000,
            TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100),
            Executors.defaultThreadFactory());

    public static void executeTasks(List<Runnable> tasks) {
        for (Runnable task : tasks) {
            pool.submit(task);
        }
        pool.shutdown();
    }


    public static void executeTask(Runnable task) {
        pool.submit(task);
        pool.shutdown();
    }


}
