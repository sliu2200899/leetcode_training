package com.jetbrains.hard;

import com.jetbrains.innerStructure.ListNode;

public class ReorderList {
    public static void reorderList(ListNode head) {
        if (head == null) return;

        // get the middle node in the list
        // get the middle node, just fast and slow pointer enough,
        // but if you want to merge two pointers, you need pre pointer.
        ListNode fast = head, slow = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (pre == null) return;
        pre.next = null;

        // reverse the latter part of the list
        ListNode newAfterHead = reverseList(slow);

        // merge two list alternatively
        mergeTwoListAlternative(head, newAfterHead);
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

    private static void mergeTwoListAlternative(ListNode l1, ListNode l2) {
        ListNode before = l1, after = l2;
        ListNode dummy = new ListNode(-1);
        dummy.next = l1;
        ListNode cur = dummy;
        boolean odd = false;
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

        if (before != null) {
            cur.next = before;
        }
        if (after != null) {
            cur.next = after;
        }
    }
}
