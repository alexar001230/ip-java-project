package com.yunlong.lee.utils.tree;

import com.yunlong.lee.utils.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 17/3/23 11:27 上午
 * @link
 */
public class TreeNodeUtils {
    public static TreeNode genBinTree() {
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postOrder = new int[]{9, 15, 7, 20, 3};
        return buildTree(inOrder, postOrder);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        //1.数组长度为0返回空
        //2.找到后序数组中的最后一个元素作为切割点
        //3.找到后序数组中最后一个节点在中序中的位置,作为切割点
        //4.切割中序数组，切成中序左数组,中序右数组
        //5.切割后续数组，切成后续左数组,后续右数组
        //6.递归处理左区间右区间
        return buildTreeRecursive(inorder, 0, inorder.length
                , postorder, 0, postorder.length);
    }

    private static TreeNode buildTreeRecursive(int[] inOrder, int inLeft, int inRight,
                                               int[] postOrder, int postLeft, int postRight) {
        //没有元素了
        if (inRight - inLeft < 1) {
            return null;
        }
        //只有一个
        if (inRight - inLeft == 1) {
            return new TreeNode(inOrder[inLeft]);
        }
        //后序中寻找根节点
        int rootVal = postOrder[postRight - 1];
        TreeNode curRoot = new TreeNode(rootVal);
        //中序中寻找切割点
        int rootIndex = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        //根据中序切割点递归构建当前根节点的左右子树
        int leftNodesCnt = rootIndex - inLeft;
        curRoot.left = buildTreeRecursive(inOrder, inLeft, rootIndex,
                postOrder, postLeft, postLeft + leftNodesCnt);
        curRoot.right = buildTreeRecursive(inOrder, rootIndex + 1, inRight,
                postOrder, postLeft + leftNodesCnt, postRight - 1);
        return curRoot;
    }


    //region 层序遍历
    private static LinkedList<TreeNode> levelNodeQ = new LinkedList<>();

    public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> levelNums = new LinkedList<>();
        if (Objects.isNull(root)) {
            return levelNums;
        }
        levelNodeQ.add(root);
        while (!levelNodeQ.isEmpty()) {
            //记录每层的数据
            List<Integer> aLevelNums = new LinkedList<>();
            //层间标识隔开
            //levelNodeQ.add(null);
            int curLevelSize = levelNodeQ.size();
            while (curLevelSize > 0) {
                TreeNode curNode = levelNodeQ.poll();
                if (Objects.nonNull(curNode.left)) {
                    levelNodeQ.add(curNode.left);
                }
                if (Objects.nonNull(curNode.right)) {
                    levelNodeQ.add(curNode.right);
                }
                aLevelNums.add(curNode.val);
                curLevelSize--;
            }
            //层数据保存
            levelNums.add(aLevelNums);
            //间隔标识出队
            // levelNodeQ.poll();
        }
        return levelNums;
    }
    //endregion
}
