package com.yunlong.lee.dataStructure.str.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 9/5/23 5:34 下午
 * @link
 */
public class OpenLock {
    public int openLock(String[] deadends, String target) {
        return doOpenLock(deadends, target);
    }


    private static String init = "0000";

    //region 广度优先搜索
    private int doOpenLock(String[] deadends, String target) {
        if (init.equals(target)) {
            return 0;
        }
        //1.构造锁集合
        HashSet<String> deadSet = new HashSet<>();
        for (String aDead : deadends) {
            deadSet.add(aDead);
        }
        if (deadSet.contains(init)) {
            return -1;
        }
        //2.广度优先搜索
        //2.1 bfs队列  初始化
        LinkedList<String> queue = new LinkedList<>();
        queue.offer(init);
        int curToVisitSize = queue.size();
        //2.2 搜索过的集合
        HashSet<String> visitedSet = new HashSet<>();
        visitedSet.add(init);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            curToVisitSize = queue.size();
            for (int i = 0; i < curToVisitSize; i++) {
                String curStr = queue.poll();
                LinkedList<String> nextStrs = getNextStr(curStr);
                for (String aNextStr : nextStrs) {
                    if (!visitedSet.contains(aNextStr) && !deadSet.contains(aNextStr)) {
                        if (aNextStr.equals(target)) {
                            return step;
                        }
                        queue.offer(aNextStr);
                        visitedSet.add(aNextStr);
                    }
                }
            }
        }
        return -1;
    }

    private LinkedList<String> getNextStr(String curStr) {
        LinkedList<String> res = new LinkedList<>();
        char[] curStrArr = curStr.toCharArray();
        for (int i = 0; i < curStr.length(); i++) {
            char iChar = curStr.charAt(i);
            char iPrev = numPrev(iChar);
            curStrArr[i] = iPrev;
            res.add(new String(curStrArr));
            char iPost = numPost(iChar);
            curStrArr[i] = iPost;
            res.add(new String(curStrArr));
            //复原
            curStrArr[i] = iChar;
        }
        return res;
    }

    private char numPrev(char numChar) {
        return numChar <= '9' && numChar > '0' ? (char) (numChar - 1) : '9';
    }

    private char numPost(char numChar) {
        return numChar == '9' ? '0' : (char) (numChar + 1);
    }
    //endregion

    public static void main(String[] args) {
        String[] deadEnds = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println(new OpenLock().openLock(deadEnds, target));
    }
}
