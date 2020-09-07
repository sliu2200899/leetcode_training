package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class RemoveNthFromEnd {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // time: O(n)
        // space: O(1)

        if (head == null || n < 0) return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;

        while (n-- > 0) {
            cur = cur.next;
        }

        ListNode p1 = cur;
        cur = dummy;
        while (p1.next != null) {
            p1 = p1.next;
            cur = cur.next;
        }

        cur.next = cur.next.next;

        return dummy.next;
    }
}
