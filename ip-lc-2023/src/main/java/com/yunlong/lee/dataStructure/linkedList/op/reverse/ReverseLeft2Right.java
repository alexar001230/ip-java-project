package com.yunlong.lee.dataStructure.linkedList.op.reverse;

import com.yunlong.lee.utils.list.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 翻转确定位置的链表(一般来说不允许我们修改节点的值或者现成的api或集合类)
 * @date 4/4/23 11:28 上午
 * @link https://leetcode.cn/problems/reverse-linked-list-ii/
 */
public class ReverseLeft2Right {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        return reverseBetweenByArr(head, left, right);
    }


    //region  简单做法,放到数组,从数组固定地方获取,重新生成链表(边界处理失败)
    private ListNode reverseBetweenByArr(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }
        //1.存入列表中
        ListNode cursor = head;
        List<ListNode> nodes = new ArrayList<>();
        while (Objects.nonNull(cursor)) {
            nodes.add(cursor);
            cursor = cursor.next;
        }
        int dummyLeft = left - 1;
        int dummyRight = right - 1;
        //2.从列表中取出来
        //分三部分left，reverse,right
        ListNode leftDummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode leftCursor = leftDummyHead;
        for (int i = 0; i < dummyLeft; i++) {
            ListNode newNode = nodes.get(i);
            leftCursor.next = newNode;
            leftCursor = leftCursor.next;
        }

        ListNode midDummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode midCursor = midDummyHead;
        for (int i = dummyRight; i >= dummyLeft; i--) {
            ListNode newNode = nodes.get(i);
            midCursor.next = newNode;
            midCursor = midCursor.next;
        }

        ListNode rightDummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode rightCursor = rightDummyHead;
        for (int i = dummyRight + 1; i < nodes.size(); i++) {
            ListNode newNode = nodes.get(i);
            rightCursor.next = newNode;
            rightCursor = rightCursor.next;
        }

        //3.整合三个链表
        leftCursor.next = midDummyHead.next;
        midCursor.next = rightDummyHead.next;
        // rightCursor.next = null;

        return leftDummyHead.next;
    }
    //endregion

    public static void main(String[] args) {
        ListNode head = ListNode.genListNode(3, 5);
        // ListNode head = ListNode.genListNode(1, 2, 3, 4, 5);
        ListNode.print(new ReverseLeft2Right().reverseBetween(head, 1, 2));
    }
}
