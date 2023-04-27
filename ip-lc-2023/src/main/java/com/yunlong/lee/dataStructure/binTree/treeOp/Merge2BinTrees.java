package com.yunlong.lee.dataStructure.binTree.treeOp;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 617. 合并二叉树
 * @date 26/4/23 6:49 下午
 * @link https://leetcode.cn/problems/merge-two-binary-trees/
 */
public class Merge2BinTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return doMergeTrees(root1, root2);
    }


    //region 官解
    private TreeNode doMergeTreesOfficial(TreeNode root1, TreeNode root2) {
        if (Objects.isNull(root1)) {
            return root2;
        }
        if (Objects.isNull(root2)) {
            return root1;
        }

        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = doMergeTreesOfficial(root1.left, root2.left);
        merged.right = doMergeTreesOfficial(root1.right, root2.right);
        return merged;
    }
    //endregion


    //region 以其中一个为基准合并,做的有些复杂了，官解产生一个新的root,然后递归合并两个左右子树
    private TreeNode finalRoot;

    private TreeNode doMergeTrees(TreeNode root1, TreeNode root2) {
        finalRoot = Objects.nonNull(root1) ? root1 : root2;
        if (Objects.equals(finalRoot, root1)) {
            mergeTreesRecursion(root1, root2, null, true);
        } else {
            mergeTreesRecursion(root2, root1, null, true);
        }

        return finalRoot;
    }

    private void mergeTreesRecursion(TreeNode root1, TreeNode root2,
                                     TreeNode curParent, boolean is2AppendLeft) {
        if (Objects.nonNull(root1) && Objects.nonNull(root2)) {
            root1.val = root1.val + root2.val;
            curParent = root1;
        } else if (Objects.isNull(root1) && Objects.nonNull(root2)) {
            if (is2AppendLeft) {
                curParent.left = root2;
            } else {
                curParent.right = root2;
            }
            return;
        } else if (Objects.nonNull(root1) && Objects.isNull(root2)) {
            return;
        } else {
            return;
        }
        mergeTreesRecursion(root1.left, root2.left, curParent, true);
        mergeTreesRecursion(root1.right, root2.right, curParent, false);
    }
    //endregion

    public static void main(String[] args) {
        int[] inOrder1 = new int[]{5, 3, 1, 2};
        int[] postOrder1 = new int[]{5, 3, 2, 1};
        TreeNode root1 = TreeNodeUtils.buildTreeByInAndPostOrder(inOrder1,
                postOrder1);
        TreeNodeUtils.levelOrderTraversalPrint(root1);

        int[] inOrder2 = new int[]{1, 4, 2, 3, 7};
        int[] postOrder2 = new int[]{4, 1, 7, 3, 2};
        TreeNode root2 = TreeNodeUtils.buildTreeByInAndPostOrder(inOrder2,
                postOrder2);
        TreeNodeUtils.levelOrderTraversalPrint(root2);

        TreeNode merged = new Merge2BinTrees().mergeTrees(root1, root2);
        TreeNodeUtils.levelOrderTraversalPrint(merged);
    }
}
