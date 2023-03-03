package com.yunlong.lee.thread;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 3/3/23 4:24 下午
 * @link
 */
public class ThreadUtils {
    private static ThreadPoolExecutor pool =
            new ThreadPoolExecutor(
                    Runtime.getRuntime().availableProcessors() * 2 + 1,
                    20, 1000,
                    TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());


    public static void execute(Runnable... tasks) {
        for (Runnable task : tasks) {
            pool.submit(task);
        }
        pool.shutdown();
    }

}
