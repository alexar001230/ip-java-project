package com.yunlonglee.nodelist;

import com.alibaba.fastjson.JSON;

import static com.yunlonglee.nodelist.ConstructListNode.constructListNode;

/**
 * @author lijie
 * @version 1.0
 * @description 合并两个有序链表
 * @date 6/2/22 10:12 下午
 */
public class MergeTwoList {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (null == list1) {
            return list2;
        }
        if (null == list2) {
            return list1;
        }
        ListNode result = new ListNode();
        ListNode cursor1 = list1;
        ListNode cursor2 = list2;
        ListNode resCursor = result;
        while (null != cursor1 || null != cursor2) {
            if (null != cursor1 && null != cursor2) {
                if (cursor1.val <= cursor2.val) {
                    ListNode toMerge = new ListNode(cursor1.val);
                    resCursor.next = toMerge;
                    resCursor = resCursor.next;
                    cursor1 = cursor1.next;
                    continue;
                } else {
                    ListNode toMerge = new ListNode(cursor2.val);
                    resCursor.next = toMerge;
                    resCursor = resCursor.next;
                    cursor2 = cursor2.next;
                    continue;
                }
            }
            if (null == cursor1) {
                resCursor.next = cursor2;
                break;
            }
            if (null == cursor2) {
                resCursor.next = cursor1;
                break;
            }
        }
        result = result.next;
        return result;
    }

    public static void main(String[] args) {
        ListNode listNode1 = constructListNode(new int[]{});
        ListNode listNode2 = constructListNode(new int[]{});
        mergeTwoLists(listNode1, listNode2);
    }
}
