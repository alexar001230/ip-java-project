package com.yunlong.lee.dataStructure.binTree.traversal.lowestAncester;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 235. 二叉树的最近公共祖先
 * @date 31/3/23 10:35 上午
 * @link https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestorOfBinTree {
    TreeNode common = new TreeNode();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        inOrderTraversal(root);
        return getLowestCommon(root, p, q);
    }

    //region 非递归两次遍历
    private void findCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        getNodePathRecursion(root, p, false);
        List<Integer> pathP = new LinkedList<>(xPath);
        xPath.clear();
        getNodePathRecursion(root, q, false);
        List<Integer> pathQ = new LinkedList<>(xPath);
        xPath.clear();
        common = new TreeNode(getLastCommon(pathP, pathQ));
        return;
    }

    private Integer getLastCommon(List<Integer> pathP, List<Integer> pathQ) {
        List<Integer> shortList = pathP.size() > pathQ.size() ? pathQ : pathP;
        List<Integer> longList = pathP.size() > pathQ.size() ? pathP : pathQ;
        for (int i = 0; i < shortList.size(); i++) {
            if (!shortList.get(i).equals(longList.get(i))) {
                return shortList.get(i - 1);
            }
        }
        return shortList.get(shortList.size() - 1);
    }

    //region 找指定值递归做法
    LinkedList<Integer> xPath = new LinkedList<>();

    private void getNodePathRecursion(TreeNode root, TreeNode x,
                                      boolean finded) {
        if (Objects.isNull(root) || finded) {
            return;
        }
        xPath.add(root.val);
        if (root.val == x.val) {
            finded = true;
            return;
        }
        if (Objects.nonNull(root.left)) {
            getNodePathRecursion(root.left, x, finded);
            if (!finded) {
                xPath.removeLast();
            }
        }

        // xPath.removeLast();
        if (Objects.nonNull(root.right)) {
            getNodePathRecursion(root.right, x, finded);
            if (!finded) {
                xPath.removeLast();
            }
        }
        return;
    }
    //endregion


    private List<Integer> myGetNodePath(TreeNode root, TreeNode x) {
        LinkedList<TreeNode> pathNodes = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean curNodeRemoved = false;
        TreeNode curParent = new TreeNode(Integer.MIN_VALUE);
        for (; ; ) {
            // if (!curNodeRemoved) {
            pathNodes.add(root);
            // }

            if (root.val == x.val) {
                break;
            }
            if (Objects.nonNull(root.left)) {
                curParent = root;
                root = root.left;
                continue;
            }
            if (Objects.nonNull(root.right)) {
                curParent = root;
                root = root.right;
            }
            // pathNodes.removeLast();
            if (Objects.nonNull(pathNodes) && pathNodes.size() > 0) {
                TreeNode curNode = pathNodes.getLast();
                if (Objects.nonNull(curParent.left) && curParent.left.val == curNode.val) {
                    root = curParent.right;
                }
                pathNodes.removeLast();
                continue;
            }
        }
        for (TreeNode node : pathNodes) {
            path.add(node.val);
        }
        return path;
    }
    //endregion


    //region 递归做法

    private void findRecursive(TreeNode root, TreeNode p,
                               TreeNode q) {
        TreeNode left = p;
        TreeNode right = q;
        if (p.val > q.val) {
            left = q;
            right = p;
        }
        findCommonNodeRecursive(root, left, right);
    }

    private void findCommonNodeRecursive(TreeNode root, TreeNode left,
                                         TreeNode right) {
        if (Objects.isNull(root)) {
            return;
        }
        if (root.val >= left.val && root.val <= right.val) {
            common = root;
            return;
        } else if (left.val < root.val && right.val < root.val) {
            findCommonNodeRecursive(root.left, left, right);
        } else {
            findCommonNodeRecursive(root.right, left, right);
        }
    }
    //endregion

    public static void main(String[] args) {
        // int[] inOrder = new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9};
        // int[] postOrder = new int[]{0, 3, 5, 4, 2, 7, 9, 8, 6};

        int[] postOrder = new int[]{6, 7, 4, 2, 5, 0, 8, 1, 3};
        int[] inOrder = new int[]{6, 5, 7, 2, 4, 3, 0, 1, 8};
        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrder, postOrder);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);

        // new LowestCommonAncestorOfBinTree().getNodePathRecursion(root, q);

        TreeNode common = new LowestCommonAncestorOfBinTree().lowestCommonAncestor(root, p
                , q);
        System.out.println(common.val);
    }


    private HashMap<Integer, Pair<TreeNode, TreeNode>> childVal2ChildParentNodeMap =
            new HashMap<>();

    public void inOrderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        if (Objects.nonNull(root.left)) {
            if (Objects.isNull(childVal2ChildParentNodeMap.get(root.left.val))) {
                childVal2ChildParentNodeMap.put(root.left.val,
                        new Pair<>(root.left, root));
            }
            inOrderTraversal(root.left);
        }

        if (Objects.nonNull(root.right)) {
            if (Objects.isNull(childVal2ChildParentNodeMap.get(root.right.val))) {
                childVal2ChildParentNodeMap.put(root.right.val,
                        new Pair<>(root.right, root));
            }
            inOrderTraversal(root.right);
        }
        return;
    }

    private TreeNode getLowestCommon(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<Integer, TreeNode> commonAncesterMap = new HashMap<>();
        int pVal = p.val;
        int qVal = q.val;
        int rVal = root.val;
        boolean isPRoot = false;
        boolean isQRoot = false;
        TreeNode pCursor = p;
        TreeNode qCursor = q;
        while (true) {
            if (Objects.nonNull(pCursor) && !isPRoot) {
                if (Objects.nonNull(commonAncesterMap.get(pCursor.val))) {
                    return pCursor;
                }
                commonAncesterMap.put(pCursor.val, pCursor);
                if (pCursor.val == rVal) {
                    isPRoot = true;
                }
                if (!isPRoot) {
                    pCursor = childVal2ChildParentNodeMap.get(pCursor.val).getValue();
                }
            }
            if (Objects.nonNull(qCursor) && !isQRoot) {
                if (Objects.nonNull(commonAncesterMap.get(qCursor.val))) {
                    return qCursor;
                }
                commonAncesterMap.put(qCursor.val, qCursor);
                if (qCursor.val == rVal) {
                    isQRoot = true;
                }
                if (!isQRoot) {
                    qCursor =
                            childVal2ChildParentNodeMap.get(qCursor.val).getValue();
                }
            }
            if (pCursor.val == qCursor.val) {
                return pCursor;
            }
        }
    }
}
