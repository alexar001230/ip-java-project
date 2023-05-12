package com.yunlong.lee.algo.dp.subSeq.editDistance;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 10/3/23 4:09 下午
 * @link https://leetcode.cn/problems/edit-distance/
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        return doMinDistanceBy2DimensionArr(word1, word2);
    }

    //region  ans 二维dp做法

    /**
     * 动态规划5步走 二维
     * 1.确定dp[i][j]数组及下标的含义,word1以下标i-1的子串 转换成 word2以下标j-1的子串的最少操作步数dp[i][j]
     * 2.确定转移方程,
     * 2.1  if  word1[i-1] == word2[j-1],则说明不需要操作，也即，dp[i][j] = dp[i-1][j-1]
     * 2.2   word1[i-1] != word2[j-1],分成三种情况，
     * a.word1[i-1]位置增加一个，dp[i][j] = dp[i][j-1]+1
     * b.word1[i-1]位置删除一个,dp[i][j] = dp[i-1][j]+1
     * c.word1[i-1]位置需要替换一个,dp[i][j] = dp[i-1][j-1]+1
     * 3.初始化 dp[i][0] = range(i),dp[0][j] = range(j)
     * 4.遍历顺序 从左到右，从上到下
     * 5.举例验证
     *
     * @param word1
     * @param word2
     * @return
     */
    private int doMinDistanceBy2DimensionArr(String word1, String word2) {

        int word1Len = word1.length();
        int word2Len = word2.length();

        int[][] dp = new int[word1Len + 1][word2Len + 1];

        // if (word1Len == 0 && word2Len == 1) {
        //     dp[0][1] = 1;
        // }
        //
        // if (word1Len == 1 && word2Len == 0) {
        //     dp[1][0] = 1;
        // }


        for (int i = 1; i <= word1Len; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= word2Len; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1Len; i++) {
            for (int j = 1; j <= word2Len; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(
                            //不相等时,word1[0~i-1]替换一个成word2[0~j-1]
                            dp[i - 1][j - 1],
                            //不相等时,word1[0~i-1]添加一个成word2[0~j-1]
                            dp[i][j - 1]),
                            //不相等时,word1[0~i-1]删除一个成word2[0~j-1]，相当于word2[0~j
                            // -1]添加一个成word1[0~i-1]
                            dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[word1Len][word2Len];
    }
    //endregion

    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
        System.out.println(new EditDistance().minDistance(word1, word2));
    }
}
