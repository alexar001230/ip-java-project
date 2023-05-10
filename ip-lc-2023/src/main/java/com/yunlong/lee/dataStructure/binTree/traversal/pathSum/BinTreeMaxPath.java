package com.yunlong.lee.dataStructure.binTree.traversal.pathSum;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 124. 二叉树中的最大路径和
 * @date 19/4/23 2:46 下午
 * @link https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 */
public class BinTreeMaxPath {
    public int maxPathSum(TreeNode root) {
        return doMaxPathSum(root);
    }

    //region 节点最大贡献值  思路，递归遍历过程逐步将最大贡献值的根节点保留下来并返回
    private int maxGain = Integer.MIN_VALUE;

    private int doMaxPathSum(TreeNode root) {
        // int maxGain = Integer.MIN_VALUE;
        int rootMaxGain = getBinTreeNodeMaxGainRecursion(root);
        int finalMax = Math.max(rootMaxGain, maxGain);
        return finalMax;
    }

    private int getBinTreeNodeMaxGainRecursion(TreeNode root) {
        //1.确定出口，分类，对叶子/空节点，递归出口分析计算
        if (Objects.isNull(root)) {
            return 0;
        }
        //2.确定递归迭代逻辑
        int curNodeGainL = Math.max(getBinTreeNodeMaxGainRecursion(root.left), 0);
        int curNodeGainR = Math.max(getBinTreeNodeMaxGainRecursion(root.right), 0);
        int curNodeMaxGain = root.val + curNodeGainL + curNodeGainR;
        //3.业务处理，这里取max(递归迭代过程中记录max值)
        maxGain = Math.max(curNodeMaxGain, maxGain);
        //对于含有返回值的迭代模型,返回值属性的递归迭代节点属性一致
        return root.val + Math.max(curNodeGainL, curNodeGainR);
    }

    public static void main(String[] args) {
        // int[] postOrders = new int[]{9, 15, 7, 20, -10};
        // int[] inOrders = new int[]{9, -10, 15, 20, 7};
        //
        // TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrders,
        //         postOrders);
        Integer[] levelOrder = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null,
                null, null, 1};
        TreeNode root = TreeNodeUtils.buildTreeByLevelOrder(levelOrder);
        TreeNodeUtils.levelOrderTraversalPrint(root);
        System.out.println(new BinTreeMaxPath().maxPathSum(root));

//         5
//     4         8
//  11   nil  13      4
// 7  2    nil nil nil 1
    }
}
