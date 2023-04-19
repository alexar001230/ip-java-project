package com.yunlong.lee.dataStructure.binTree.convert;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 层序数组转换成bintree
 * @date 19/4/23 3:45 下午
 * @link 常见二叉树给的是一个层序数组, 需要将其转成bintree
 */
public class LevelOrderArr2BinTree {
    public TreeNode levelOrderArr2BinTree(Integer[] levelOrderArr) {
        return doLevelOrderArr2BinTree(levelOrderArr);
    }

    private TreeNode doLevelOrderArr2BinTree(Integer[] levelOrderArr) {
        LinkedList<TreeNode> binTreeNodesQ = new LinkedList<>();
        int i = 0;
        while (i < levelOrderArr.length) {
            if (Objects.nonNull(levelOrderArr[i])) {
                binTreeNodesQ.offer(new TreeNode(levelOrderArr[i]));
            } else {
                binTreeNodesQ.offer(null);
            }
            i++;
        }
        int curLevelSize = 1;
        TreeNode root = binTreeNodesQ.peek();
        LinkedList<TreeNode> parentQ = new LinkedList<>();
        parentQ.push(binTreeNodesQ.poll());
        LinkedList<TreeNode> childQ = new LinkedList<>();
        while (!binTreeNodesQ.isEmpty()) {
            // int curParentSize = curLevelSize;
            // while (curLevelSize > 0) {
            //     parentQ.push(binTreeNodesQ.poll());
            //     curParentSize--;
            // }
            curLevelSize = curLevelSize * 2;
            int curChildSize = curLevelSize;
            while (curChildSize > 0 && !binTreeNodesQ.isEmpty()) {
                childQ.offer(binTreeNodesQ.poll());
                curChildSize--;
            }
            int j = 0;
            while (!parentQ.isEmpty()) {
                TreeNode curParent = parentQ.poll();
                for (; j < childQ.size(); ) {
                    curParent.left = childQ.get(j);
                    curParent.right = childQ.get(j + 1);
                    j = j + 2;
                    break;
                }
            }
            while (!childQ.isEmpty() && !binTreeNodesQ.isEmpty()) {
                TreeNode toBeParentNode = childQ.poll();
                if (Objects.nonNull(toBeParentNode)) {
                    parentQ.offer(toBeParentNode);
                }
            }

        }
        return root;
    }

    public static void main(String[] args) {
        // Integer[] levelOrders = new Integer[]{-10, 9, 20, null, null, 15, 7};
        Integer[] levelOrders = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};

        TreeNode root =
                new LevelOrderArr2BinTree().levelOrderArr2BinTree(levelOrders);
        TreeNodeUtils.levelOrderTraversalPrint(root);
    }
}
