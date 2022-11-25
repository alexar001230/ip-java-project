package com.yunlonglee.binTree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.genBinTree;

/**
 * @author lijie
 * @version 1.0
 * @description 路径总和2:找出二叉树中所有根节点到叶子节点和为targetSum的路径
 * @notice 不能用迭代，迭代很复杂
 * @date 6/2/22 6:19 下午
 */
public class PathSum2 {
    //递归三步曲

    /**
     * 递归三步曲
     * @param root
     * @param targetSum
     * @return
     * 1.确定递归函数参数和返回类型
     * 2。确定终止条件
     * 3。确定单层递归逻辑
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        //标记单个路径
        List<Integer> aResult = new ArrayList<>();
        //中序遍历找出所有路径
        preOrderTravel(root, targetSum, result, aResult);
        return result;
    }

    private static void preOrderTravel(TreeNode root, int targetSum,
                                List<List<Integer>> result,
                                List<Integer> aResult) {
        aResult.add(root.val);
        if (null == root.left && null == root.right) {
            if (targetSum - root.val == 0) {
                //单个路径拷贝并存入结果集
                result.add(new ArrayList<>(aResult));
            }
            return;
        }
        if (null != root.left) {
            preOrderTravel(root.left, targetSum - root.val, result, aResult);
            //单个路径回溯
            aResult.remove(aResult.size() - 1);
        }
        if (null != root.right) {
            preOrderTravel(root.right, targetSum - root.val, result, aResult);
            //单个路径回溯
            aResult.remove(aResult.size() - 1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = genBinTree();
        System.out.println(JSON.toJSONString(pathSum(root,30)));
    }
}
