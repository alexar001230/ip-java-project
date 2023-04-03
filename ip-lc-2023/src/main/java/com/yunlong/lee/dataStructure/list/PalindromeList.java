package com.yunlong.lee.dataStructure.list;

import com.yunlong.lee.utils.list.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 17/3/23 11:08 上午
 * @link
 */
public class PalindromeList {
    public boolean isPalindrome(ListNode head) {
        if (null == head || head.next == null) {
            return true;
        }

        List<ListNode> nodes = new ArrayList<>();
        ListNode curNode = head;
        while (null != curNode) {
            nodes.add(curNode);
            curNode = curNode.next;
        }


        for (int i = 0, j = nodes.size() - 1; i <= j; i++, j--) {
            if (nodes.get(i).val != nodes.get(j).val) {
                return false;
            }
        }
        return true;
    }
}
