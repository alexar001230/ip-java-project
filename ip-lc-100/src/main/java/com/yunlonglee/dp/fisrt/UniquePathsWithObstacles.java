package com.yunlonglee.dp.fisrt;

/**
 * @author lijie
 * @version 1.0
 * @description 63. 不同路径 II
 * @date 17/2/22 8:29 上午
 */
public class UniquePathsWithObstacles {

    private static final int OBSTACLE = 1;
    private static final int NO_OBSTACLE = 0;


    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //动态规划5步走
        //1.确定dp数组含义。dp[i][j]，到数组(i,j)位置的不同路径数，arr[i][j]==0
        //2.确定转移方程。 if(arr[i-1][j]==0 && arr[i][j-1]==0)
        // dp[i][j]=dp[i-1][j]+dp[i][j-1] ，有一个为0，dp[i][j] 为另一个方向的路径数
        //3.确定遍历顺序  行遍历
        //4.初始化 dp[0][j]=1/0,dp[i][0]=1/0
        //5.举例推导

        int result = 0;
        int columns = obstacleGrid[0].length;
        int rows = obstacleGrid.length;//todo 二维数组的行数为二维数组的长度(可看作存储了行数个数组)
        int[][] dp = new int[rows][columns];
        int j = 0;
        boolean preHasNoObStacle = true;
        while (j < columns) {
            if (obstacleGrid[0][j] != OBSTACLE && preHasNoObStacle) {
                dp[0][j] = 1;
            } else {
                preHasNoObStacle = false;
            }
            if (!preHasNoObStacle) {
                dp[0][j] = 0;
            }
            j++;
        }
        preHasNoObStacle = true;
        int i = 0;
        while (i < rows) {
            if (obstacleGrid[i][0] != OBSTACLE && preHasNoObStacle) {
                dp[i][0] = 1;
            } else {
                preHasNoObStacle = false;
            }
            if (!preHasNoObStacle) {
                dp[i][0] = 0;
            }
            i++;
        }

        for (int m = 1; m < rows; m++) {
            for (int n = 1; n < columns; n++) {
                if(obstacleGrid[m][n] == OBSTACLE){
                    dp[m][n] = 0;
                }else{
                    if (obstacleGrid[m - 1][n] != OBSTACLE && obstacleGrid[m][n - 1] != OBSTACLE) {
                        dp[m][n] = dp[m - 1][n] + dp[m][n - 1];
                    } else if (obstacleGrid[m - 1][n] == OBSTACLE) {
                        dp[m][n] = dp[m][n - 1];
                    } else if (obstacleGrid[m][n - 1] == OBSTACLE) {
                        dp[m][n] = dp[m - 1][n];
                    }else {
                        dp[m][n] = 0;
                    }
                }

            }
        }
        return dp[rows - 1][columns - 1];

    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles(grid));
    }
}
