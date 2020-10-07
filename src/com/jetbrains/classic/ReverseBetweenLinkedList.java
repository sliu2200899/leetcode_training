package com.jetbrains.classic;

import com.jetbrains.innerStructure.ListNode;

public class ReverseBetweenLinkedList {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return head;

        int i = 1;
        ListNode prev = null, cur = head;
        while (i < m) {
            prev = cur;
            cur = cur.next;
            i++;
        }

        ListNode before = prev, tail = cur; //
        for (; i <= n; ++i) {
            ListNode next = cur.next;
            cur.next = prev;

            prev = cur;
            cur = next;
        }
        if (before == null) {
            head = prev;
        } else {
            before.next = prev;
        }
        tail.next = cur;

        return head;
    }

    // more like raw methods

    /*
        this method is much more easier to understand and implement..
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        // sanity check
        if (head == null || m > n) return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = dummy, pre = null;
        int i = 0;
        while (i < m) {
            pre = cur;
            cur = cur.next;
            i++;
        }

        ListNode revBefore = pre;
        ListNode sublistNewTail = cur;

        while (i <= n) {
            pre = cur;
            cur = cur.next;
            i++;
        }

        ListNode sublistNewHead = pre;
        ListNode revAfter = cur;

        reverseList(sublistNewTail, sublistNewHead);

        revBefore.next = sublistNewHead;
        sublistNewTail.next = revAfter;

        return dummy.next;
    }

    private void reverseList(ListNode head, ListNode tail) {
        ListNode cur = head, temp = null;
        while (cur != tail) {
            ListNode next = cur.next;
            cur.next = temp;

            temp = cur;
            cur = next;
        }
        tail.next = temp;
    }
}
