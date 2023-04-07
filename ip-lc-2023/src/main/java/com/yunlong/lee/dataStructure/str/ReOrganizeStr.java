package com.yunlong.lee.dataStructure.str;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author lijie
 * @version 1.0
 * @description 重构字符串
 * @date 6/4/23 2:21 下午
 * @link https://leetcode.cn/problems/reorganize-string/
 */
public class ReOrganizeStr {
    public String reorganizeString(String s) {
        return doReOrganizeStringByBigHeap(s);
    }

    //region 借助数组和大根堆，数组中存串中每个字符的数量,
    // 大根堆中依次存字符,每次从堆中取两个顺序拼串(贪心间隔开(局部最优达到全局最优))
    private String doReOrganizeStringByBigHeap(String s) {
        if (Objects.isNull(s)) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }

        int[] charCntArr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charCntArr[s.charAt(i) - 'a']++;
        }
        //当串中字符数量超过一半,则无法实现,直接返回空串
        int maxCnt = 0;
        for (Integer cnt : charCntArr) {
            maxCnt = Math.max(maxCnt, cnt);
        }
        if (maxCnt > (s.length() + 1) / 2) {
            return "";
        }


        //大根堆
        PriorityQueue<Character> maxRootHeap = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character char1, Character char2) {
                return charCntArr[char2 - 'a'] - charCntArr[char1 - 'a'];
            }
        });
        for (char aChar = 'a'; aChar <= 'z'; aChar++) {
            if (charCntArr[aChar - 'a'] > 0) {
                maxRootHeap.offer(aChar);
            }
        }
        StringBuilder reOrganizeSb = new StringBuilder();
        while (maxRootHeap.size() >= 2) { //需要两两取出
            char left = maxRootHeap.poll();
            char right = maxRootHeap.poll();
            reOrganizeSb.append(left);
            reOrganizeSb.append(right);
            charCntArr[left - 'a']--;
            charCntArr[right - 'a']--;
            if (charCntArr[left - 'a'] > 0) {
                maxRootHeap.offer(left);
            }
            if (charCntArr[right - 'a'] > 0) {
                maxRootHeap.offer(right);
            }
        }
        if (maxRootHeap.size() >= 1) {
            reOrganizeSb.append(maxRootHeap.poll());
        }
        return reOrganizeSb.toString();
    }
    //endregion


    //region 双指针遍历数组左右部分,处理不来
    private String myDoReOrganizeString(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }

        char[] sCharArr = s.toCharArray();
        Arrays.sort(sCharArr);
        int left = 0;
        int postLeft = left + 1;

        int right = s.toCharArray().length - 1;
        int preRight = s.toCharArray().length - 2;
        while (right >= left) {
            //1.左左相同,1.1 右右相同且不同于左,交换左右，左+1，右-1 1.2 右右相同且同于左,右右-1 2.1
            // 右右不同 都不同左，任意交换一个，存在一个相同的，右右-1
            if (sCharArr[left] == sCharArr[postLeft]) {
                if (sCharArr[right] == sCharArr[preRight]) {
                    if (sCharArr[left] != sCharArr[right]) {
                        swap(sCharArr, postLeft, right);
                        left++;
                        postLeft++;
                        right--;
                        preRight--;
                    } else {
                        return "";
                    }
                } else {
                    if (sCharArr[left] != sCharArr[right] && sCharArr[left] != sCharArr[preRight]) {
                        swap(sCharArr, left, preRight);
                        left++;
                        postLeft++;
                        right--;
                        preRight--;
                    } else {
                        swap(sCharArr, right, preRight);
                        if (!hasContinuousRepeat(sCharArr)) {

                        }
                        right--;
                        preRight--;
                    }
                }
            }


            if (sCharArr[right] == sCharArr[preRight]) {
                if (sCharArr[left] == sCharArr[postLeft]) {
                    if (sCharArr[left] != sCharArr[right]) {
                        swap(sCharArr, left, preRight);
                        left++;
                        postLeft++;
                        right--;
                        preRight--;
                    } else {
                        return "";
                    }
                } else {
                    if (sCharArr[right] != sCharArr[left] && sCharArr[right] != sCharArr[postLeft]) {
                        swap(sCharArr, left, preRight);
                        left++;
                        postLeft++;
                        right--;
                        preRight--;
                    } else {
                        right--;
                        preRight--;
                    }
                }
            }

        }
        if (!hasContinuousRepeat(sCharArr)) {
            StringBuilder sb = new StringBuilder();
            for (char aChar : sCharArr) {
                sb.append(aChar);
            }
            return sb.toString();
        } else {
            return "";
        }
    }


    private boolean hasContinuousRepeat(char[] charsArr) {
        int pre = 0;
        int post = pre + 1;
        while (post <= charsArr.length - 1) {
            if (charsArr[pre] == charsArr[post]) {
                return true;
            }
            pre++;
            post++;
        }
        return false;
    }

    private void swap(char[] arr, int idx1, int idx2) {
        char tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
    //endregion

    public static void main(String[] args) {
        String s = "aab";
        //String s = "aaab";
        // String s = "zhmyo";

        System.out.println(new ReOrganizeStr().reorganizeString(s));

        int[] arr = new int[]{1, 7, 3, 4, 5};
        PriorityQueue<Integer> maxRootHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (Integer num : arr) {
            maxRootHeap.offer(num);
        }
        for (; maxRootHeap.size() > 0; ) {
            System.out.println(maxRootHeap.poll());
        }
        // System.out.println(JSON.toJSONString(arr));
    }

}
