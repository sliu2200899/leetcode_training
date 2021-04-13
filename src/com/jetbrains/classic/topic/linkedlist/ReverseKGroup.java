package com.jetbrains.classic.topic.linkedlist;

import com.jetbrains.innerStructure.ListNode;

public class ReverseKGroup {
    /*
    clarify:
        1. input, output, example
        2. space: O(1)
        3. other constraints .... cannot alter the values in the list's nodes

    algo:

        dummyNode cur
        iterate over the list
            reverse(list, start, end)

        reverser(list, start, end)

        use a couple additional variables to maintain the proper connections along the way.

        key is that when doing reverse(), you need to bring the next node in the function

    time: O(N)
    space: O(1)

*/
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode cur = dummyNode;
        while (cur != null) {
            ListNode prev = cur, tail = prev.next;
            int num = 1;
            cur = tail;
            while(cur != null && num < k) {
                cur = cur.next;
                num++;
            }

            if (cur != null && num == k) {
                ListNode next = cur.next;
                prev.next = reverseGroup(tail, next);
                tail.next = next;
                cur = tail;
            }
        }

        return dummyNode.next;
    }

    private ListNode reverseGroup(ListNode tail, ListNode next) {
        ListNode pre = null;
        while (tail != next) {
            ListNode temp = tail.next;
            tail.next = pre;

            pre = tail;
            tail = temp;
        }

        return pre;
    }
}
