package com.yunlong.lee.dataStructure.binTree.traversal.pathSum;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 11/4/23 6:47 下午
 * @link
 */
public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        return doPathSum(root, targetSum);
    }


    LinkedList<LinkedList<Integer>> allPaths = new LinkedList<>();

    private List<List<Integer>> doPathSum(TreeNode root,
                                          int targetSum) {
        List<List<Integer>> result = new LinkedList<>();
        getAllPathsRecursion(root, new LinkedList<>());
        for (LinkedList<Integer> aPath : allPaths) {
            int aSum = targetSum;
            for (Integer aVal : aPath) {
                aSum = aSum - aVal;
            }
            if (aSum == 0) {
                result.add(aPath);
            }
        }
        return result;
    }

    private void getAllPathsRecursion(TreeNode root,
                                      LinkedList<Integer> aPath) {
        //1.确定出口
        if (Objects.isNull(root)) {
            return;
        }
        //2.处理节点数据
        aPath.add(root.val); //2.1 节点数据入路径
        if (Objects.isNull(root.right) && Objects.isNull(root.left)) {
            //孩子节点时，入全局路径,同时路径最后一个节点删除
            allPaths.add(new LinkedList<>(aPath));
            aPath.removeLast();
            return;
        }
        //3.递归迭代
        if (Objects.nonNull(root.left)) {
            getAllPathsRecursion(root.left, aPath);
        }
        if (Objects.nonNull(root.right)) {
            getAllPathsRecursion(root.right, aPath);
        }
        aPath.removeLast();
        return;
    }

    public static void main(String[] args) {
        int[] inOrderArr = new int[]{7, 11, 2, 4, 5, 13, 8, 5, 4, 1};
        int[] postOrderArr = new int[]{7, 2, 11, 4, 13, 5, 1, 4, 8, 5};
        // TreeNode root = TreeNodeUtils.genBinTree();
        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrderArr, postOrderArr);
        System.out.println(new PathSum2().pathSum(root, 22));
    }
}
