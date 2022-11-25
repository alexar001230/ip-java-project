package com.yunlong.lee.blockingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 24/2/22 6:32 下午
 */
public class MyLinkedBlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private AtomicInteger curQueueSize;
    private AtomicInteger maxCapacity;
    private ReentrantLock putLock = new ReentrantLock();
    private Condition notFull = putLock.newCondition();
    private ReentrantLock takeLock = new ReentrantLock();
    private Condition notEmpty = takeLock.newCondition();

    public MyLinkedBlockingQueue(int curQueueSize, int maxCapacity) {
        this.curQueueSize.set(0);
        this.maxCapacity.set(maxCapacity);
    }

    public void put(T element) throws InterruptedException {
        int count = -1;
        putLock.lock();
        try {
            while (curQueueSize.get() >= maxCapacity.get()) {
                notFull.await();
            }
            queue.add(element);
            count = curQueueSize.incrementAndGet();
            if (count + 1 < maxCapacity.get()) {
                notFull.signal();
            }
        } catch (InterruptedException e) {
            throw e;
        } finally {
            putLock.unlock();
        }
        if (count == 1) {
            takeLock.lock();
            try {
                notEmpty.signal();
            } finally {
                takeLock.unlock();
            }
        }
    }

    public T take() throws InterruptedException {
        int count = 0;
        T element;
        takeLock.lock();
        try {
            while (curQueueSize.get() == 0) {
                notEmpty.await();
            }
            element = queue.poll();
            count = curQueueSize.decrementAndGet();
            if (count > 0) {
                notEmpty.signal();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            takeLock.unlock();
        }

        if (count == maxCapacity.get()) {
            putLock.lock();
            try {
                notFull.signal();
            } finally {
                putLock.unlock();
            }
        }
        return element;
    }
}
