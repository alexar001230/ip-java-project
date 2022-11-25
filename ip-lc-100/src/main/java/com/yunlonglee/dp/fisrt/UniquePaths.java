package com.yunlonglee.dp.fisrt;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 24/1/22 11:39 下午
 */
public class UniquePaths {
    public static void main(String[] args) {
        int m=3;
        int n = 7;
        System.out.println(uniquePaths(m,n));
    }


    public static int uniquePaths(int m, int n) {
        //1.确定dpArr[i][j]含义,到达Mij路径数目,i表示行数，j表示列数
        //2.确定递推公式 dpArr[i][j] = dpArr[i][j-1] + dpArr[i-1][j]
        //3.初始化
        //4.遍历顺序
        //5.举例推倒
        int[][] dpArr =new int[m][n];
        for(int i=0;i<m;i++){
            if(i==0){
                dpArr[i][0] = 0;
            }else{
                dpArr[i][0] = 1;
            }

        }

        for(int j=0;j<n;j++){
            if(j==0){
                dpArr[0][j] = 0;
            }else{
                dpArr[0][j] = 1;
            }

        }
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                dpArr[i][j] = dpArr[i][j-1]+dpArr[i-1][j];
                System.out.println(dpArr[i][j]);
            }
        }
        return dpArr[m-1][n-1];
    }
}
