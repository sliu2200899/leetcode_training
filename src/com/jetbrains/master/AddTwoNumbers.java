package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyNode = new ListNode(-1), n = dummyNode;
        int carry  = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int value1 = l1 != null ? l1.val : 0;
            int value2 = l2 != null ? l2.val : 0;

            ListNode newNode = new ListNode((value1 + value2 + carry) % 10);
            carry = (value1 + value2 + carry) / 10;

            n.next = newNode;
            n = n.next;

            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }

        return dummyNode.next;
    }
}
