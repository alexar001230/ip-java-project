package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.List;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.buildTree;

/**
 * @author lijie
 * @version 1.0
 * @description 98. 验证二叉搜索树
 * @date 13/3/22 9:53 下午
 */
public class IsValidBST {
    private static List<Integer> vals = new ArrayList<>();

    public static boolean isValidBST(TreeNode root) {
        inOrderTravel(root);
        for (int i = 0; i < vals.size() - 1; i++) {
            for (int j = i + 1; j < vals.size(); j++) {
                if (vals.get(j) < vals.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void inOrderTravel(TreeNode root) {
        if (null == root) {
            return;
        }
        inOrderTravel(root.left);
        if (null != root) {
            vals.add(root.val);
        }
        inOrderTravel(root.right);
    }

    public static void main(String[] args) {
        int[] inOrder = {4,5,3,6,7};
        int[] postOrder = {4,3,7,6,5};
        TreeNode root = buildTree(inOrder, postOrder);
        System.out.println(isValidBST(root));
    }
}
