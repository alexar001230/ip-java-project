package com.yunlong.lee.dataStructure.binTree.treeOp;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 剑指 Offer 27. 二叉树的镜像
 * @description 226. 翻转二叉树
 * @date 27/4/23 3:07 下午
 * @link https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
 * @link https://leetcode.cn/problems/invert-binary-tree/
 */
public class MirrorBinTree {
    public TreeNode mirrorTree(TreeNode root) {
        return doMirrorTree(root);
    }

    private TreeNode doMirrorTree(TreeNode root) {
        if(Objects.isNull(root)){
            return null;
        }
        doMirrorTreeRecursion(root);
        return root;
    }

    private void doMirrorTreeRecursion(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        if (Objects.nonNull(root.left)) {
            doMirrorTreeRecursion(root.left);
        }

        if (Objects.nonNull(root.right)) {
            doMirrorTreeRecursion(root.right);
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    private boolean isLeaf(TreeNode aNode) {
        return Objects.nonNull(aNode)
                && Objects.isNull(aNode.left)
                && Objects.isNull(aNode.right);
    }

    public static void main(String[] args) {
        int[] inOrders = new int[]{1, 2, 3, 4, 6, 7, 9};
        int[] postOrders = new int[]{1, 3, 2, 6, 9, 7, 4};

        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrders,
                postOrders);
        TreeNodeUtils.levelOrderTraversalPrint(root);
        new MirrorBinTree().mirrorTree(root);
        TreeNodeUtils.levelOrderTraversalPrint(root);
    }
}
