package com.jetbrains.classic.topic.linkedlist;

import com.jetbrains.innerStructure.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class addTwoNumbers2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode head = new ListNode(-1);
        int carry = 0;
        while (stack1.size() != 0  || stack2.size() != 0 || carry != 0) {
            int val1 = stack1.size() == 0 ? 0 : stack1.pop();
            int val2 = stack2.size() == 0 ? 0 : stack2.pop();

            ListNode newNode = new ListNode((val1 + val2 + carry) % 10);
            newNode.next = head.next;
            head.next = newNode;

            carry = (val1 + val2 + carry) /10;
        }

        return head.next;
    }
}
