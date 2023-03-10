package com.yunlong.lee.dataStructure.list;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 10/3/23 2:26 下午
 * @link https://leetcode.cn/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        while (Objects.nonNull(cur)) {
            //新建节点，保存节点连接关系
            ListNode next = cur.next;
            //遍历游标指向前驱节点
            cur.next = prev;
            //前驱节点后移，方便反向指向
            prev = cur;
            //当前处理节点后移
            cur = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.genListNode(1, 2, 3);
        ListNode.print(head);
        ListNode.print(new ReverseLinkedList().reverseList(head));
    }
}
