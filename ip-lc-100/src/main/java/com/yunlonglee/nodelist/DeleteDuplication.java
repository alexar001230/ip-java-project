package com.yunlonglee.nodelist;

import java.util.HashMap;
import java.util.Map;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNode;

/**
 * @author lijie
 * @version 1.0
 * @description https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&rp=3&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking
 * JZ76 删除链表中重复的结点
 * @date 2/3/22 2:00 上午
 */
public class DeleteDuplication {

    /**
     * 删除所有出现重复val节点
     *
     * @param pHead
     * @return
     */
    public static ListNode deleteDuplication0(ListNode pHead) {
        if (null == pHead) {
            return null;
        }
        Map<Integer, Integer> aMap = new HashMap<>();
        ListNode cursor = pHead;
        while (null != cursor) {
            if (null != aMap.get(cursor.val)) {
                aMap.put(cursor.val, aMap.get(cursor.val) + 1);
            } else {
                aMap.put(cursor.val, 1);
            }
            cursor = cursor.next;
        }
        cursor = pHead;
        ListNode head0 = new ListNode(Integer.MIN_VALUE);
        head0.next = pHead;
        int i = 1;
        while (null != cursor) {
            if (aMap.get(cursor.val) > 1) {

                head0.next = cursor.next;
                cursor = cursor.next;
                if (i == 1) {
                    pHead = head0.next;
                }
            } else {
                head0 = head0.next;
                cursor = cursor.next;
                i++;
            }
        }
        return pHead;
    }

    /**
     * 预期只保留一个
     *
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication1(ListNode pHead) {
        Map<Integer, ListNode> aMap = new HashMap<>();
        ListNode head0 = new ListNode(Integer.MIN_VALUE);
        head0.next = pHead;
        ListNode cursor = pHead;
        while (null != cursor) {
            if (null != aMap.get(cursor.val)) {
                head0.next = cursor.next;
                cursor = cursor.next;
            } else {
                aMap.put(cursor.val, cursor);
                cursor = cursor.next;
                head0 = head0.next;
            }
        }
        return pHead;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,3,4,4,5};
        ListNode listNode = constructListNode(arr);
        deleteDuplication0(listNode);
    }
}
