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
    private class Tuple{
        ListNode head;
        ListNode tail;
        public Tuple (ListNode h, ListNode t) {
            this.head = h;
            this.tail = t;
        }
    }
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;

        for (int i = 0; i < m - 1; ++i) {
            cur = cur.next;
        }

        ListNode reversePreHead = cur;
        ListNode reverseHead = cur.next;

        cur = cur.next;

        for (int i = 0; i < n - m + 1; ++i) {
            cur = cur.next;
        }

        ListNode reverseTail = cur;

        Tuple t = reverseLinkedList(reverseHead, reverseTail);
        reversePreHead.next = t.head;

        t.tail.next = reverseTail;

        return dummy.next;

    }

    private Tuple reverseLinkedList(ListNode head, ListNode tail) {
        // return the head of the new list
        ListNode pre = null, newTail = head;
        while (head != tail) {
            ListNode next = head.next;
            head.next = pre;

            pre = head;
            head = next;
        }

        return new Tuple(pre, newTail);
    }
}
