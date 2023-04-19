package com.yunlong.lee.dataStructure.binTree.convert;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.list.ListNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 有序链表转换为二叉排序树
 * @date 31/3/23 11:46 上午
 * @link https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/
 */
public class SortedList2Bst {

    //region 迭代方式
    TreeNode root = new TreeNode(-1);

    public TreeNode sortedListToBST(ListNode head) {
        if(Objects.isNull(head)){
            return null;
        }
        //1.list转数组
        List<Integer> arrList = sortedList2Arr(head);
        //2.数组借助队列转二叉排序树
        //2.1 声明队列并初始化
        Queue<TreeNode> nodeQ = new LinkedList<>();
        nodeQ.offer(root);
        Queue<Integer> leftIdxQ = new LinkedList<>();
        leftIdxQ.offer(0);

        Queue<Integer> rightIdxQ = new LinkedList<>();
        rightIdxQ.offer(arrList.size() - 1);

        while (!nodeQ.isEmpty()) {
            TreeNode curNode = nodeQ.poll();
            int leftIdx = leftIdxQ.poll();
            int rightIdx = rightIdxQ.poll();
            int midIdx = (leftIdx + rightIdx) / 2;
            curNode.val = arrList.get(midIdx);

            if (leftIdx <= midIdx - 1) {
                curNode.left = new TreeNode(-1);
                nodeQ.offer(curNode.left);
                leftIdxQ.offer(leftIdx);
                rightIdxQ.offer(midIdx - 1);
            }

            if (rightIdx >= midIdx + 1) {
                curNode.right = new TreeNode(-1);
                nodeQ.offer(curNode.right);
                leftIdxQ.offer(midIdx + 1);
                rightIdxQ.offer(rightIdx);
            }
        }
        return root;
    }
    //endregion

    public TreeNode mySortedListToBST(ListNode head) {
        // 从有序链表找中序/后序排列(中点的地方)
        List sortedList = sortedList2Arr(head);
        sortedList2InOrderRecursive(sortedList, 0, sortedList.size() - 1);
        sortedList2PostOrderRecursive(sortedList, 0, sortedList.size() - 1);
        // 从中序后序构建二叉树
        int[] inOrderArr = inOrder.stream().mapToInt(Integer::valueOf).toArray();
        int[] postOrderArr = postOrder.stream().mapToInt(Integer::valueOf).toArray();
        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrderArr, postOrderArr);
        return root;
    }


    private List<Integer> inOrder = new LinkedList<>();
    private List<Integer> postOrder = new LinkedList<>();

    private void sortedList2InOrderRecursive(List<Integer> sortedList,
                                             int low, int high) {
        if (low > high) {
            return;
        }
        int mid = (low + high) / 2;
        sortedList2InOrderRecursive(sortedList, low, mid - 1);
        inOrder.add(sortedList.get(mid));
        sortedList2InOrderRecursive(sortedList, mid + 1, high);
    }

    private void sortedList2PostOrderRecursive(List<Integer> sortedList,
                                               int low, int high) {
        if (low > high) {
            return;
        }
        int mid = (low + high) / 2;
        sortedList2PostOrderRecursive(sortedList, low, mid - 1);
        sortedList2PostOrderRecursive(sortedList, mid + 1, high);
        postOrder.add(sortedList.get(mid));
    }


    private List<Integer> sortedList2Arr(ListNode head) {
        List sortedList = new LinkedList();
        while (Objects.nonNull(head)) {
            sortedList.add(head.val);
            head = head.next;
        }
        return sortedList;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-10, -3, 0, 5, 9};
        ListNode head = ListNode.genListNode(arr);
        TreeNode root = new SortedList2Bst().sortedListToBST(head);
        System.out.println(TreeNodeUtils.levelOrderTraversal(root));
    }

}
