package com.yunlong.lee.utils.tree;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
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
        //          3
        //       9      20
        //            15   7
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postOrder = new int[]{9, 15, 7, 20, 3};
        return buildTreeByInAndPostOrder(inOrder, postOrder);
    }

    //region buildTreeByLevelOrder todo 有bug
    public static TreeNode buildTreeByLevelOrder(Integer[] levelOrder) {
        return levelOrderArr2BinTree(levelOrder);
    }
    //endregion

    //region 中序后序生成二叉树(当树节点存在值相同的情况,可能得二叉树结果不唯一)
    public static TreeNode buildTreeByInAndPostOrder(int[] inorder, int[] postorder) {
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
    //endregion


    //region 层序打印
    public static void levelOrderTraversalPrint(TreeNode root) {
        List<List<Integer>> levelNos = levelOrderTraversal(root);
        System.out.println(JSON.toJSONString(levelNos));
    }
    //endregion

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
                if (Objects.isNull(curNode)) {
                    aLevelNums.add(null);
                } else {
                    if (Objects.nonNull(curNode.left)) {
                        levelNodeQ.add(curNode.left);
                    } else {
                        levelNodeQ.add(null);
                    }
                    if (Objects.nonNull(curNode.right)) {
                        levelNodeQ.add(curNode.right);
                    } else {
                        levelNodeQ.add(null);
                    }
                    aLevelNums.add(curNode.val);
                }
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

    //region inOrder中序遍历

    public static List<Integer> inOrder(TreeNode root) {
        inOrderTraversal(root);
        return InOrderTraversals;
    }

    private static List<Integer> InOrderTraversals = new LinkedList<>();

    private static void inOrderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        if (Objects.nonNull(root.left)) {
            inOrderTraversal(root.left);
        }
        InOrderTraversals.add(root.val);
        if (Objects.nonNull(root.right)) {
            inOrderTraversal(root.right);
        }
    }
    //endregion
    //region postOrder后序遍历

    public static List<Integer> postOrder(TreeNode root) {
        postOrderTraversal(root);
        return PostOrderTraversals;
    }

    private static List<Integer> PostOrderTraversals = new LinkedList<>();

    private static void postOrderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        if (Objects.nonNull(root.left)) {
            postOrderTraversal(root.left);
        }
        if (Objects.nonNull(root.right)) {
            postOrderTraversal(root.right);
        }
        PostOrderTraversals.add(root.val);
    }
    //endregion

    //region 层序数组生成二叉树
    public static TreeNode levelOrderArr2BinTree(Integer[] levelOrderArr) {
        return doLevelOrderArr2BinTree(levelOrderArr);
    }

    private static TreeNode doLevelOrderArr2BinTree(Integer[] levelOrderArr) {
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
    //endregion

    //region 层序构建n叉树 todo 没写完
    public static Node buildNNodeTreeByLevelOrders(Integer[] nNodeTreeLevelOrders) {
        Node root = new Node(nNodeTreeLevelOrders[0]);
        LinkedList<LinkedList<Node>> parentQ = new LinkedList<>();
        parentQ.offer(new LinkedList<>(Arrays.asList(root)));
        LinkedList<LinkedList<Node>> childQ = new LinkedList<>();
        int qLeftIdx = 1;
        int qRightIdx = qLeftIdx + 1;
        while (qRightIdx < nNodeTreeLevelOrders.length) {
            LinkedList<Node> parents = parentQ.peek();
            while (nNodeTreeLevelOrders[qRightIdx] != null) {
                qRightIdx++;
            }
        }
        return null;
    }
    //endregion
}
