package com.jetbrains.master;

import com.jetbrains.innerStructure.ListNode;

public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // find the middle node of the list
        ListNode mid = findMiddle(head);

        // reverse the second sublist
        ListNode headSecondList = reverseList(mid);

        // merge two single linked list
        mergeTwoList(head, headSecondList);
    }

    private void mergeTwoList(ListNode head, ListNode newHead) {
        int num = 1;
        ListNode dummy = new ListNode(1);
        ListNode cur = dummy;
        while (head != null && newHead != null) {
            if (num % 2 == 1) {
                cur.next = head;
                head = head.next;
            } else {
                cur.next = newHead;
                newHead = newHead.next;
            }

            cur = cur.next;
            num++;
        }

        if (head != null) cur.next = head;
        if (newHead != null) cur.next = newHead;

        head = dummy.next;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head, slow = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = null;
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode temp = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = temp;

            temp = head;
            head = next;
        }
        return temp;
    }
}
