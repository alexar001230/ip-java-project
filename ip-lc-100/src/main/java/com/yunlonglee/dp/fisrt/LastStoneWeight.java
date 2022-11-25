package com.yunlonglee.dp.fisrt;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 1046. 最后一块石头的重量
 * @date 18/2/22 10:37 下午
 */
public class LastStoneWeight {
    //private static int[] resArr = null;

    public static int lastStoneWeight(int[] stones) {
        int[] resArr = new int[stones.length];
        processArr(stones,resArr);
        return stones[stones.length-1];
    }

    private static void processArr(int[] stones,int[] resArr){
        process(stones,resArr);
    }


    private static void process(int[] stones,int[] resArr){
        Arrays.sort(stones);
        int max1 = stones[stones.length-1];
        int max2 = stones[stones.length-2];
        if(max1 == 0 || max2 == 0){
            return;
        }
        stones[stones.length-1] = 0;
        stones[stones.length-2] = Math.abs(max2-max1);
        deepCopy(stones,resArr);
        process(stones,resArr);
    }

    private static int[] deepCopy(int[] arr,int[] res){
        for(int i=0;i<arr.length;i++){
            res[i] = arr[i];
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2,7,4,1,8,1};
        System.out.println(lastStoneWeight(arr));
    }
}
