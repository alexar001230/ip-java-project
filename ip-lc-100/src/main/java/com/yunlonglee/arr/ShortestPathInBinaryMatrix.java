package com.yunlonglee.arr;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lijie
 * @version 1.0
 * @description 没有a掉
 * @date 27/1/22 1:39 上午
 */
public class ShortestPathInBinaryMatrix {
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        int m = grid.length;
        int n = m;
        boolean[][] vis = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int dis = 1;
        if (grid[0][0] != 0) {
            return -1;
        }
        if (m == 1) {
            return 1;
        }
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int c = 0; c < size; ++c) {
                int[] poll = queue.poll();
                int i = poll[0];
                int j = poll[1];
                for (int k = 0; k < 8; ++k) {
                    int ni = i + dir[k][0];
                    int nj = j + dir[k][1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == 0 && !vis[ni][nj]) {
                        if (ni == m - 1 && nj == n - 1) {
                            return dis + 1;
                        }
                        queue.offer(new int[]{ni, nj});
                        vis[ni][nj] = true;
                    }
                }
            }
            //这一圈都加1
            dis += 1;
        }
        return -1;
    }


    //广度遍历，到右下角则返回，循环完到不了则返回-1，grid[i][j]记录步数
    public static int shortestPathBinaryMatrix1(int[][] grid) {
        //动态规划5步曲
        //1.确定dp[i][j]含义及下标含义  矩阵(i,j)点通路的最小步数
        int columnSize = grid[0].length;//二维数组某一行的长度为列的长度
        int rowSize = grid.length;//二维数组的长度为行的长度
        int[][] dpArr = new int[grid.length][grid[0].length];
        //2.确定递推转化公式
        //3.初始化
        //4.遍历顺序 遍历grid
        //5.举例推倒计算过程

        if (grid[0][0] != 1) {
            dpArr[0][0] = 1;
        } else {
            dpArr[0][0] = Integer.MAX_VALUE - 1;
        }
        if (grid[0][1] != 1) {
            dpArr[0][1] = dpArr[0][0] + 1;
        } else {
            dpArr[0][1] = Integer.MAX_VALUE - 1;
        }

        if (grid[1][0] != 1) {
            dpArr[1][0] = dpArr[0][0] + 1;
        } else {
            dpArr[1][0] = Integer.MAX_VALUE - 1;
        }

        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < columnSize; j++) {


                int leftUp = Integer.MAX_VALUE - 1;
                if (i >= 1 && j >= 1 && grid[i - 1][j - 1] != 1) {
                    leftUp = dpArr[i - 1][j - 1];
                }
                int up = Integer.MAX_VALUE - 1;
                if (i >= 1 && grid[i - 1][j] != 1) {
                    up = dpArr[i - 1][j];
                }

                int rightUp = Integer.MAX_VALUE - 1;
                if (i >= 1 && j < columnSize - 2 && grid[i - 1][j + 1] != 1) {
                    rightUp = dpArr[i - 1][j + 1];
                }

                int right = Integer.MAX_VALUE - 1;
                if (i > 1 && j < columnSize - 2 && grid[i - 1][j + 1] != 1) {
                    right = dpArr[i - 1][j + 1];
                }


                int rightDown = Integer.MAX_VALUE - 1;
                if (i < rowSize - 2 && j < columnSize - 2 && grid[i + 1][j + 1] != 1) {
                    rightDown = dpArr[i - 1][j - 1];
                }
                int down = Integer.MAX_VALUE - 1;
                if (i < rowSize - 2 && j < columnSize - 1 && grid[i + 1][j] != 1) {
                    down = dpArr[i + 1][j];
                }

                int leftDown = Integer.MAX_VALUE - 1;
                if (i < rowSize - 2 && j >= 1 && grid[i + 1][j - 1] != 1) {
                    leftDown = dpArr[i + 1][j - 1];
                }

                int left = Integer.MAX_VALUE - 1;
                if (i < rowSize && j >= 1 && grid[i][j - 1] != 1) {
                    left = dpArr[i][j - 1];
                }
                dpArr[i][j] =
                        Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(leftUp, up),
                                rightUp),
                                right)
                                , rightDown)
                                , down),
                                leftDown), left) + 1;
                if (i == rowSize - 1 && j == columnSize - 1) {
                    return dpArr[i][j];
                }
            }
        }


        return dpArr[rowSize - 1][columnSize - 1] != Integer.MAX_VALUE ?
                dpArr[rowSize - 1][columnSize - 1] : -1;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{0, 1}, {1, 0}};
        shortestPathBinaryMatrix(arr);

        System.out.println(arr[0].length);
        System.out.println(arr.length);
    }
}
