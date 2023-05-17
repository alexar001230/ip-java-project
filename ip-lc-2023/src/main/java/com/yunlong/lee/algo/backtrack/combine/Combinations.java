package com.yunlong.lee.algo.backtrack.combine;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 77. 组合
 * @date 14/2/23 4:00 下午
 * @link https://leetcode.cn/problems/combinations/
 * 基本思想：凡是可以用回溯的问题都可以转换成一颗n叉树的问题
 */
public class Combinations {
    static List<List<Integer>> res = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        int n = 4;
        int k = 3;
        System.out.println(JSON.toJSONString(combine(n,k)));
    }

    public static List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return res;
    }

    public static void combineHelper(int n, int k, int startIdx) {
        //1.终止条件
        if (path.size() == k) {
            //1.1保存结果
            res.add(new ArrayList<>(path));
            return;
        }
        //2.选择:本层集合中的元素(树中节点孩子的数量就是集合的大小)
        for (int i = startIdx; i <= n - (k - path.size()) + 1; i++) {
            //2.1 处理节点
            path.add(i);
            combineHelper(n, k, i + 1);//(选择列表，路径)
            //2.2 回溯,撤销处理结果
            path.removeLast();
        }
    }
}
