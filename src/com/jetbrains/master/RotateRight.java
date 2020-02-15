package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class RotateRight {
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;

        ListNode cur = head;
        int i = 0;
        while (cur != null) {
            cur = cur.next;
            i++;
        }

        int offset = k % i;
        if (offset == 0) {
            return head;
        }

        cur = head;
        int j = 0;
        while (j < (i - offset - 1)) {
            cur = cur.next;
            j++;
        }
        ListNode newTail = cur;
        ListNode newHead = cur.next;
        ListNode tail = newHead;
        while(tail.next != null) {
            tail = tail.next;
        }

        tail.next = head;
        newTail.next = null;

        return newHead;
    }
}
