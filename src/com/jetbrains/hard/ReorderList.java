package com.jetbrains.hard;

import com.jetbrains.innerStructure.ListNode;

public class ReorderList {
    public static void reorderList(ListNode head) {
        if (head == null) return;

        // get the length of the list
        int i = 1;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            i++;
        }

        int mid = (i % 2 == 0) ? i / 2 : (i + 1) / 2;

        // get the middle node in the list
        cur = head;
        ListNode prev = null;
        for (int j = 0; j < mid; ++j) {
            prev = cur;
            cur = cur.next;
        }

        prev.next = null;
        // reverse the latter part of the list
        ListNode newAfterHead = reverseList(cur);

        // merge two list alternatively
        ListNode before = head, after = newAfterHead;
        cur = head;
        boolean odd = true;
        while (before != null && after != null) {
            if (odd) {
                cur.next = after;
                after = after.next;
            } else {
                cur.next = before;
                before = before.next;
            }
            odd = !odd;
            cur = cur.next;
        }

        return;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;

            pre = head;
            head = next;
        }
        return pre;
    }
}
