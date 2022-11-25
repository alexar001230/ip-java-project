package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 递归中序遍历
 * @date 6/2/22 5:31 下午
 */
public class InOrderTravelRecursive {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        recursiveTravel(root, result);
        return result;
    }

    private void recursiveTravel(TreeNode root, List<Integer> result) {
        if (null == root) {
            return;
        }
        if (null != root.left) {
            recursiveTravel(root.left, result);
        }
        result.add(root.val);
        if (null != root.right) {
            recursiveTravel(root.right, result);
        }
    }
}
