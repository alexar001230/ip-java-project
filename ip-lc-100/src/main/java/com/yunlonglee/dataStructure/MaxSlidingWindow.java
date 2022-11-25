package com.yunlonglee.dataStructure;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 239. 滑动窗口最大值
 * @date 9/3/22 2:17 上午
 */
public class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        int arrLength = nums.length - 1 - k + 1 + 1;
        int[] arr = new int[arrLength];
        int num = 0;
        MyQueue myQueue = new MyQueue();
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        arr[num++] = myQueue.peek();
        for(int i=k;i<nums.length;i++){
            myQueue.poll(nums[i-k]);
            myQueue.add(nums[i]);
            arr[num++] = myQueue.peek();
        }
        return arr;
    }


    class MyQueue {
        private Deque<Integer> deque = new LinkedList<>();

        public void poll(int val) {
            if (!deque.isEmpty() && val == deque.peek()) {
                deque.poll();
            }
        }

        public void add(int val) {
            while (!deque.isEmpty() && val > deque.getLast()) {
                deque.removeLast();
            }
            deque.add(val);
        }

        public int peek() {
            return deque.peek();
        }
    }


    public int[] maxSlidingWindow1(int[] nums, int k) {
        int left = 0;
        int right = k - 1;
        List<Integer> maxes = new ArrayList<>();
        while (right < nums.length) {
            Integer aMax = getMax(nums, left, right);
            maxes.add(aMax);
            left++;
            right++;
        }
        int[] arr = new int[maxes.size()];
        for (int i = 0; i < maxes.size(); i++) {
            arr[i] = maxes.get(i);
        }
        return arr;
    }

    private int getMax(int[] arr, int left, int right) {
        Stack<Integer> maxNumStack = new Stack<>();
        for (int i = left; i <= right; i++) {
            if (maxNumStack.isEmpty()) {
                maxNumStack.push(arr[i]);
            } else {
                int top = maxNumStack.peek();
                if (top < arr[i]) {
                    maxNumStack.push(arr[i]);
                }
            }
        }
        int aMax = maxNumStack.pop();
        return aMax;
    }
}
