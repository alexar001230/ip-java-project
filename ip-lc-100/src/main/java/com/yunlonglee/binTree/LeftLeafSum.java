package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.List;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.genBinTree;

/**
 * @author lijie
 * @version 1.0
 * @description 左叶子之和
 * @date 21/1/22 7:12 上午
 */
public class LeftLeafSum {
    /**
     * 左叶子=left&&left.child==null && right.child == null
     *
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        List<Integer> inOrderTravelNodes = new ArrayList<>();
        List<Integer> leftLeafNodeValues = new ArrayList<>();
        recursiveTravel(root,inOrderTravelNodes,leftLeafNodeValues);
        int sumLeft = 0;
        for (int i = 0; i < leftLeafNodeValues.size(); i++) {
            sumLeft += leftLeafNodeValues.get(i);
        }
        return sumLeft;
    }

    private static void recursiveTravel(TreeNode root, List<Integer> result,
                                 List<Integer> leftNodeValues) {
        if (null == root) {
            return;
        }
        if (null != root.left) {
            if(null == root.left.left && null == root.left.right){
                leftNodeValues.add(root.left.val);
            }
            recursiveTravel(root.left, result,leftNodeValues);
        }
        result.add(root.val);
        if (null != root.right) {
            recursiveTravel(root.right, result,leftNodeValues);
        }
    }

    private static void recursiveGetAllPath(TreeNode root, List<Integer> single,
                                            List<List<Integer>> allPath) {
        single.add(root.val);
        if (null == root.left && null == root.right) {
            List<Integer> copy = new ArrayList<>();
            for (int i = 0; i < single.size(); i++) {
                copy.add(single.get(i));
            }
            allPath.add(copy);
            return;
        }
        if (null != root.left) {
            recursiveGetAllPath(root.left, single, allPath);
            single.remove(single.size() - 1);
        }
        if (null != root.right) {
            recursiveGetAllPath(root.right, single, allPath);
            single.remove(single.size() - 1);
        }
    }


    public static void main(String[] args) {
        TreeNode root = genBinTree();
        System.out.println(sumOfLeftLeaves(root));
    }
}
