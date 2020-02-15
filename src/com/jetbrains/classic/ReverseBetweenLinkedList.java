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
}
