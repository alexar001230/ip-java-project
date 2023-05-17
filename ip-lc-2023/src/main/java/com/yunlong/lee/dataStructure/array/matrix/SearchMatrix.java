package com.yunlong.lee.dataStructure.array.matrix;

import com.yunlong.lee.utils.arr.ArrUtils;

/**
 * @author lijie
 * @version 1.0
 * @description 74. 搜索二维矩阵
 * @date 13/5/23 7:45 下午
 * @link https://leetcode.cn/problems/search-a-2d-matrix/
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        return doSearchMatrix(matrix, target);
    }


    //region 先按照第一列找到可能存在的行，然后在对应的行找目标值
    private boolean doSearchMatrix(int[][] matrix, int target) {
        int targetRowIdx = binSearchTargetRowIdx(matrix, target);
        if (targetRowIdx >= 0) {
            return binSearch(matrix[targetRowIdx], 0,
                    matrix[targetRowIdx].length - 1, target) >= 0;
        } else {
            return false;
        }
    }

    private int binSearchTargetRowIdx(int[][] matrix, int target) {
        int rStartIdx = 0;
        int rEndIdx = matrix.length - 1;
        while (rStartIdx <= rEndIdx) {
            int rMid = (rStartIdx + rEndIdx) / 2;
            if (rMid < matrix.length - 1) {
                if (matrix[rMid][0] <= target && target < matrix[rMid + 1][0]) {
                    return rMid;
                } else {
                    if (target < matrix[rMid][0]) {
                        rEndIdx = rMid - 1;
                    } else {
                        rStartIdx = rMid + 1;
                    }
                }
            } else {
                return matrix.length - 1;
            }
        }
        return -1;
    }
    //endregion


    //region fuck边界，题解错误
    private boolean matrixSearch(int[][] matrix, int rFromIdx, int rEndIdx,
                                 int cFromIdx, int cEndIdx,
                                 int target) {
        int cLen = matrix[0].length;
        int rLen = matrix.length;
        while (rFromIdx <= rEndIdx && cFromIdx <= cEndIdx) {
            int rMid = (rFromIdx + rEndIdx) / 2;
            int cMid = (cFromIdx + cEndIdx) / 2;
            int curNo = matrix[rMid][cMid];
            if (curNo == target) {
                return true;
            } else {
                if (target < curNo) {
                    //往左上看
                    if (rMid - 1 >= 0 && cMid - 1 >= 0) {
                        int preNo = matrix[rMid - 1][cMid - 1];
                        if (target > preNo) {
                            // return binSearch(matrix[rMid - 1], rMid, cLen - 1, target)
                            //         || binSearch(matrix[rMid + 1], 0, rMid, target);
                        } else {
                            rEndIdx = rMid - 1;
                            cEndIdx = cMid - 1;
                        }
                    } else {
                        // return binSearch(matrix[rMid], 0, rMid - 1, target);
                    }
                } else {
                    //往右下看
                    if (rMid + 1 <= rEndIdx && cMid + 1 <= cEndIdx) {
                        int nextNo = matrix[rMid + 1][cMid + 1];
                        if (target < nextNo) {
                            // return binSearch(matrix[rMid], rMid + 1, cLen - 1, target)
                            // || binSearch(matrix[rMid + 1], 0, rMid, target);
                        } else {
                            rFromIdx = rMid + 1;
                            cFromIdx = cMid + 1;
                        }
                    } else {
                        // return binSearch(matrix[rMid], rMid, cLen - 1, target);
                    }
                }
            }
        }
        return false;
    }
    //endregion

    private int binSearch(int[] arr, int fromIdx, int endIdx, int target) {
        while (fromIdx <= endIdx) {
            int mid = (fromIdx + endIdx) / 2;
            if (arr[mid] == target) {
                return mid;
            } else {
                if (arr[mid] > target) {
                    endIdx = mid - 1;
                } else {
                    fromIdx = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // ArrUtils.str2Arr2Print("[[1,3,5,7],[10,11,16,20],[23,30,34,60]]");
        // int[][] arr = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int[][] arr = new int[][]{{1}, {3}};
        int target = 3;
        System.out.println(new SearchMatrix().searchMatrix(arr, target));
    }

}
