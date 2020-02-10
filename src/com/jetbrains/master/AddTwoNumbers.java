package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        Two things to make the code simple:
            Whenever one of the two ListNode is null, replace it with 0.
            Keep the while loop going when at least one of the three conditions is met.
        */
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

    public static ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2) {
        return helper(l1, l2, 0);
    }

    private static ListNode helper(ListNode l1, ListNode l2, int carry) {
        // precondition which break the recursion
        if (l1 == null && l2 == null) {
            if (carry == 1) return new ListNode(1);
            else return null;
        }

        // recursive method call
        int sum = carry;
        sum += (l1 == null ? 0 : l1.val);
        sum += (l2 == null ? 0 : l2.val);

        carry = sum / 10;
        sum = sum % 10;

        ListNode newNode = new ListNode(sum);
        if (l1 == null) {
            newNode.next = helper(l1, l2.next, carry);
        } else if (l2 == null) {
            newNode.next = helper(l1.next, l2, carry);
        } else {
            newNode.next = helper(l1.next, l2.next, carry);
        }

        return newNode;
    }
}
