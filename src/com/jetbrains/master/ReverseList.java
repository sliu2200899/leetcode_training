package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class ReverseList {
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }

        return pre;
    }
}
