package com.yunlonglee.binTree;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 19/1/22 2:06 上午
 */
public class BuildTreeFromInAndPost {

    public static void main(String[] args) {
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postOrder = new int[]{9, 15, 7, 20, 3};
        TreeNode root = buildTree(inOrder, postOrder);
    }

    public static TreeNode genBinTree(){
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postOrder = new int[]{9, 15, 7, 20, 3};
        return buildTree(inOrder,postOrder);
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
        int rootVal = postOrder[postRight-1];
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
                postOrder, postLeft + leftNodesCnt, postRight-1);
        return curRoot;
    }
}
