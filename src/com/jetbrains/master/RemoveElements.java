package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class RemoveElements {
    public static ListNode removeElements(ListNode head, int val) {
        // dummy
        // pre, cur
        // cur.val == val pre.next = cur.next

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}
