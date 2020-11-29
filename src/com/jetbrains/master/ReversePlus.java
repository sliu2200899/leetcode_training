package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class ReversePlus {
    public ListNode plus (ListNode head) {
        if (head == null) return new ListNode(1);

        ListNode tmp = reverse(head);

        addPlus(tmp);

        return reverse(tmp);
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = prev;

            prev = head;
            head = tmp;
        }
        return prev;
    }

    private void addPlus(ListNode head) {
        int carry = 1;
        ListNode cur = head, prev = null;
        while (cur != null) {
            int val = cur.val;

            cur.val = (val + carry) % 10;
            carry = (val + carry) / 10;

            prev = cur;
            cur = cur.next;
        }

        if (carry == 1) {
            ListNode node = new ListNode(1);
            prev.next = node;
        }
    }
}
