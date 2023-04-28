package com.yunlong.lee.dataStructure.linkedList.op;

import com.yunlong.lee.utils.list.ListNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 237. 删除链表中的节点(不给头节点,原地删除)
 * @date 27/4/23 5:44 下午
 * @link https://leetcode.cn/problems/delete-node-in-a-linked-list/
 */
public class DelNodeInLinkedList {
    public void deleteNode(ListNode node) {
        doDeleteNode(node);
    }

    private void doDeleteNode(ListNode node) {
        int tmp = node.val;
        node.val = node.next.val;
        node.next.val = tmp;
        node.next = node.next.next;
    }


}
