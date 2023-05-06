package com.yunlong.lee.dataStructure.linkedList.op.reverse;

import com.yunlong.lee.utils.list.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description k个一组翻转链表
 * @date 4/4/23 2:31 下午
 * @link https://leetcode.cn/problems/reverse-nodes-in-k-group/
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        return reverseKGroupByArr(head, k);
    }

    private ListNode reverseKGroupByArr(ListNode head, int k) {
        int len = nodesLength(head);
        List<ListNode> toMerge = new ArrayList<>();
        List<ListNode> kGroups = split2KGroup(head, k);
        boolean lastNeedReverse = len % k == 0;
        for (int i = 0; i < kGroups.size(); i++) {
            ListNode aReversed = null;
            if (i < kGroups.size() - 1) {
                aReversed = reverse(kGroups.get(i));
                toMerge.add(aReversed);
            } else {
                if (i == kGroups.size() - 1 && lastNeedReverse) {
                    aReversed = reverse(kGroups.get(i));
                    toMerge.add(aReversed);
                } else {
                    toMerge.add(kGroups.get(kGroups.size() - 1));
                }
            }
        }
        return mergeReversedNodes(toMerge);
    }


    private int nodesLength(ListNode head) {
        int len = 0;
        ListNode cursor = head;
        while (Objects.nonNull(cursor)) {
            cursor = cursor.next;
            len++;
        }
        return len;
    }

    private List<ListNode> split2KGroup(ListNode head, int k) {
        int len = nodesLength(head);
        int actualKGroups = len / k;
        int lastGroupQuantity = len % k;

        ListNode cursor = head;

        List<ListNode> nodeHeads = new ArrayList<>();

        for (int j = 0; j < actualKGroups; j++) {
            int i = 0;
            ListNode aDummyHead = new ListNode(-1);
            ListNode aCursor = aDummyHead;
            for (; Objects.nonNull(cursor); ) {
                if (i < k) {
                    ListNode newNode = new ListNode(cursor.val);
                    cursor = cursor.next;
                    aCursor.next = newNode;
                    aCursor = aCursor.next;
                    i++;
                    if (i == k) {
                        nodeHeads.add(aDummyHead);
                        aCursor.next = null;
                        break;
                    }
                }
            }
        }
        if (lastGroupQuantity != 0) {
            ListNode notReverse = cursor;
            ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
            dummyNode.next = notReverse;
            nodeHeads.add(dummyNode);
        }
        return nodeHeads;
    }

    private ListNode reverse(ListNode toReverseDummyHead) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode originCursor = toReverseDummyHead.next;
        ListNode newCursor = dummyHead.next;
        while (Objects.nonNull(originCursor)) {
            //处理原链数据及游标
            ListNode newProcessNode = new ListNode(originCursor.val);
            originCursor = originCursor.next;

            //新节点入新链
            newProcessNode.next = newCursor;
            dummyHead.next = newProcessNode;
            //新链表游标指向刚处理的节点
            newCursor = newProcessNode;
        }
        return dummyHead;
    }


    private ListNode mergeReversedNodes(List<ListNode> nodeDummyHeads) {
        List<ListNode> tails = new ArrayList<>();
        for (int j = 0; j < nodeDummyHeads.size(); j++) {
            ListNode cursor = nodeDummyHeads.get(j).next;
            for (; Objects.nonNull(cursor.next); ) {
                cursor = cursor.next;
            }
            tails.add(cursor);
        }
        for (int i = 0; i < nodeDummyHeads.size() - 1; i++) {
            tails.get(i).next = nodeDummyHeads.get(i + 1).next;
        }
        return nodeDummyHeads.get(0).next;
    }


    public static void main(String[] args) {
        ListNode head = ListNode.genListNode(1, 2, 3, 4, 5);
        int k = 3;
        ListNode.print(new ReverseNodesInKGroup().reverseKGroup(head, k));
    }
}
