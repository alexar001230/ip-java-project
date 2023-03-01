package com.yunlong.lee.thread;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 1/3/23 1:34 下午
 * @link https://leetcode.cn/problems/building-h2o/
 */
public class BuildingH2O {

    private static Semaphore hSemaphore = new Semaphore(2);
    private static Semaphore oSemaphore = new Semaphore(1);
    private static CyclicBarrier h2oBarrier = new CyclicBarrier(3);

    public BuildingH2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hSemaphore.acquire();
        try {
            h2oBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        hSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSemaphore.acquire();
        try {
            h2oBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        oSemaphore.release();
    }


    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            10, 100, 1000,
            TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100),
            Executors.defaultThreadFactory());

    public static void main(String[] args) {
        String water = "OOHHHH";


        Runnable releaseOxygen = new Runnable() {
            @Override
            public void run() {
                System.out.println("O");
            }
        };

        Runnable releaseHydrogen = new Runnable() {
            @Override
            public void run() {
                System.out.println("H");
            }
        };

        // Runnable hRunner = new Runnable() {
        //     @Override
        //     public void run() {
        //         try {
        //             hydrogen(releaseHydrogen);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // };
        //
        // Runnable oRunner = new Runnable() {
        //     @Override
        //     public void run() {
        //         try {
        //             oxygen(releaseOxygen);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // };

        char[] charArr = water.toCharArray();
        LinkedList<Character> charactersQ = new LinkedList<>();
        for (int i = 0; i < charArr.length; i++) {
            charactersQ.add(charArr[i]);
        }

        int hCnt = 0;
        int oCnt = 1;

        BuildingH2O buildingH2O = new BuildingH2O();

        for (; !charactersQ.isEmpty(); ) {
            char charOfQ = charactersQ.poll();
            if (charOfQ == 'H') {
                pool.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            buildingH2O.hydrogen(releaseHydrogen);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                // hCnt++;
                // if (hCnt == 2) {
                //     pool.execute(new Runnable() {
                //         @Override
                //         public void run() {
                //             try {
                //                 hydrogen(releaseHydrogen);
                //             } catch (InterruptedException e) {
                //                 e.printStackTrace();
                //             }
                //         }
                //     });
                //     hCnt = 0;
                // }
            } else {

                pool.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            buildingH2O.oxygen(releaseOxygen);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                // pool.execute(new Runnable() {
                //     @Override
                //     public void run() {
                //         try {
                //             oxygen(releaseOxygen);
                //         } catch (InterruptedException e) {
                //             e.printStackTrace();
                //         }
                //     }
                // });
            }
            // System.out.println("ing");
        }

        pool.shutdown();

    }
}
