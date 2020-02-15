package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        // dummy
        // pre, cur
        // cur.val == cur.next.val => pre.next = cur.next;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy, cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
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
