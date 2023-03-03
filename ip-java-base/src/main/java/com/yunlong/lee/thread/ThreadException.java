package com.yunlong.lee.thread;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 3/3/23 4:23 下午
 * @link
 */
public class ThreadException {

    static class NumPrinter implements Runnable {
        @Override
        public void run() {
            int i = Integer.parseInt("uncaughtException");
            System.out.println(i);
        }
    }

    public static void main(String[] args) {

        // Thread thread  = new Thread(new NumPrinter());
        // thread.setUncaughtExceptionHandler(new ThreadException());
        // thread.start();

        ThreadUtils.execute(new NumPrinter());
    }
}
