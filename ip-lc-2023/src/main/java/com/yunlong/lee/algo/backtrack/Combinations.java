package com.yunlong.lee.algo.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 14/2/23 4:00 下午
 * @link https://leetcode.cn/problems/combinations/
 * 基本思想：凡是可以用回溯的问题都可以转换成一颗n叉树的问题
 */
public class Combinations {
    static List<List<Integer>> res = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        System.out.println(JSON.toJSONString(combine(n,k)));
    }

    public static List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return res;
    }

    public static void combineHelper(int n, int k, int startIdx) {
        //构造终止条件，每个分支纵向遍历的长度即为组合的数目上限k
        if (path.size() == k) {
            //达到上限k,保存值
            res.add(new ArrayList<>(path));
            return;
        }
        //回溯算法单层搜索遍历
        for (int i = startIdx; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            combineHelper(n, k, i + 1);
            path.removeLast();
        }
    }
}
