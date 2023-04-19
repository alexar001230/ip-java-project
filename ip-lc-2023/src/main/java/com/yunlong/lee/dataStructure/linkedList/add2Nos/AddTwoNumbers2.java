package com.yunlong.lee.dataStructure.linkedList.add2Nos;

import com.yunlong.lee.utils.list.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description 445. 两数相加 II(+ 不翻转链表,怎么解？)
 * @date 19/4/23 1:20 下午
 * @link https://leetcode.cn/problems/add-two-numbers-ii/
 */
public class AddTwoNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return doAdd2NumbersByStack(l1, l2);
    }

    //region 从高位到低位顺序相加借助栈来做,同理从低位到高位相加可借助队列来做，逻辑清晰很多
    private ListNode doAdd2NumbersByStack(ListNode l1, ListNode l2) {
        Deque<Integer> l1NumStack = numVals2Stack(l1);
        Deque<Integer> l2NumStack = numVals2Stack(l2);

        int carry = 0;
        Deque<Integer> sumStack = new ArrayDeque<>();
        while (!l1NumStack.isEmpty() && !l2NumStack.isEmpty()) {
            int aSum = l1NumStack.pop() + l2NumStack.pop() + carry;
            carry = aSum / 10;
            int curVal = aSum % 10;
            sumStack.push(curVal);
        }

        while (!l1NumStack.isEmpty()) {
            int aSum = l1NumStack.pop() + carry;
            carry = aSum / 10;
            int curVal = aSum % 10;
            sumStack.push(curVal);
        }

        while (!l2NumStack.isEmpty()) {
            int aSum = l2NumStack.pop() + carry;
            carry = aSum / 10;
            int curVal = aSum % 10;
            sumStack.push(curVal);
        }

        if (carry > 0) {
            sumStack.push(carry);
        }
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode cursor = dummyHead;
        while (!sumStack.isEmpty()) {
            ListNode aNew = new ListNode(sumStack.pop());
            cursor.next = aNew;
            cursor = cursor.next;
        }
        return dummyHead.next;
    }

    private Deque<Integer> numVals2Stack(ListNode l1) {
        if (Objects.isNull(l1)) {
            return new ArrayDeque();
        }
        ListNode cursor = l1;
        Deque<Integer> stack = new ArrayDeque<>();
        while (Objects.nonNull(cursor)) {
            stack.push(cursor.val);
            cursor = cursor.next;
        }
        return stack;
    }
    //endregion

    //region 链表翻转方式做相加
    private ListNode doAddTwoNumbersByReverseList(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) {
            return l2;
        }
        if (Objects.isNull(l2)) {
            return l1;
        }
        ListNode l1Reverse = reverse(l1);
        ListNode l2Reverse = reverse(l2);
        ListNode sumLow2High = addTwoNosFromLow2High(l1Reverse, l2Reverse);
        ListNode sumRes = reverse(sumLow2High);
        return sumRes;
    }

    private ListNode reverse(ListNode l1) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = l1;
        ListNode originCursor = l1.next;
        ListNode newCursor = l1;
        ListNode newTail = l1;
        while (Objects.nonNull(originCursor)) {
            //1.保存原指针指向,方便循环迭代后移
            ListNode tmp = originCursor.next;
            //2.业务处理  2.1 老节点(要处理的节点指向新链表游标节点)
            originCursor.next = newCursor;
            // 2.2 新游标往前移动
            newCursor = originCursor;
            // 2.3 哑头节点重新指向新游标节点
            dummyHead.next = newCursor;
            // 3.原指针游标后移
            originCursor = tmp;
        }
        newTail.next = null;
        return dummyHead.next;
    }

    private ListNode addTwoNosFromLow2High(ListNode l1, ListNode l2) {
        ListNode l1Cursor = l1;
        ListNode l2Cursor = l2;
        int carry = 0;
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode finalCursor = dummyHead;
        while (Objects.nonNull(l1Cursor) && Objects.nonNull(l2Cursor)) {
            //1.通过carry保留上次累加的结果
            int aSum = l1Cursor.val + l2Cursor.val + carry;
            ListNode aNew = new ListNode(aSum % 10);
            carry = aSum / 10;
            //2.业务处理,链入新链表
            finalCursor.next = aNew;
            finalCursor = finalCursor.next;
            //3.指针后移
            l1Cursor = l1Cursor.next;
            l2Cursor = l2Cursor.next;
        }
        while (Objects.nonNull(l1Cursor)) {
            int aSum = l1Cursor.val + carry;
            ListNode aNew = new ListNode(aSum % 10);
            carry = aSum / 10;
            finalCursor.next = aNew;

            l1Cursor = l1Cursor.next;
            finalCursor = finalCursor.next;
        }
        while (Objects.nonNull(l2Cursor)) {
            int aSum = l2Cursor.val + carry;
            ListNode aNew = new ListNode(aSum % 10);
            carry = aSum / 10;

            finalCursor.next = aNew;

            l2Cursor = l2Cursor.next;
            finalCursor = finalCursor.next;
        }

        if (carry > 0) {
            ListNode aNew = new ListNode(carry);
            finalCursor.next = aNew;
        }

        return dummyHead.next;
    }
    //endregion


    public static void main(String[] args) {
        ListNode l1 = ListNode.genListNode(7, 2, 4, 3);
        ListNode l2 = ListNode.genListNode(5, 6, 4);

        ListNode.print(new AddTwoNumbers2().addTwoNumbers(l1, l2));
    }
}
