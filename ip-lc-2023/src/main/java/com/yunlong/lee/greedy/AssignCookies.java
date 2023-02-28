package com.yunlong.lee.greedy;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 9/2/23 12:29 下午
 * @link https://leetcode.cn/problems/assign-cookies/
 */
public class AssignCookies {

    public static void main(String[] args) {
        int[] g = new int[]{10,9,8,7};
        int[] s = new int[]{7,8};
        System.out.println(findContentChildren(g, s));

        Integer[] ss = new Integer[]{7,8};
        copyAndSort2List(ss);
    }


    /**
     * 优先考虑饼干，小饼干先喂饱小胃口
     * 和自己想法区别是：两个集合同时遍历,不满足时增加另一个集合遍历的游标，这种技巧在链表两数相加中也看到过
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int count = 0;
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (s[i] >= g[start]) {
                start++;
                count++;
            }
        }
        return count;
    }





    /**
     * 自己的解题思路
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildrenMy(int[] g, int[] s) {
        Integer[] gInt = Arrays.stream(g).boxed().toArray(Integer[]::new);
        Integer[] sInt = Arrays.stream(s).boxed().toArray(Integer[]::new);
        //1.孩子排序,饼干排序
        //2.遍历孩子集合，在饼干集合中找比孩子胃口大的饼干，有则两个都删除，统计量+1
        //3.有一个为空，则返回统计量
        List<Integer> sSorted = copyAndSort2List(sInt);
        List<Integer> gSorted = copyAndSort2List(gInt);
        int total = 0;
        for (int i = 0; i < gSorted.size(); i++) {
            for (int j = 0; j < sSorted.size(); j++) {
                if (gSorted.get(i) <= sSorted.get(j)) {
                    total++;
                    gSorted.remove(i);
                    sSorted.remove(j);
                }
            }
        }
        return total;
    }

    private static List<Integer> copyAndSort2List(Integer[] arr) {
        List<Integer> resList = new ArrayList<>(arr.length);
        List<Integer> resList2 =
                Arrays.stream(arr).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(resList2));
        Integer[] copy = arr;
        Arrays.sort(copy);
        Collections.addAll(resList, arr);
        System.out.println(JSON.toJSONString(resList));
        return resList;
    }
}
