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
       clarify:
           input, output, example
               assume that left and right will not exceed the length of the list   ok

           corner cases


       algo:
           [1,    2,    3,    4,    5]
                  |

           step1: find some necessary nodes
           step2: reverse the sub list
           step3: reshuffle

   */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        // find the
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head, prev = dummy;
        int i = 1;
        while (i < left) {
            prev = cur;
            cur = cur.next;
            i++;
        }

        ListNode before = prev;
        ListNode subListHead = cur;

        while (i < right) {
            cur = cur.next;
            i++;
        }

        ListNode subListTail = cur;
        ListNode after = cur.next;

        before.next = reverseSubList(subListHead, subListTail.next);
        subListHead.next = after;

        return dummy.next;
    }

    private ListNode reverseSubList(ListNode head, ListNode tail) {
        ListNode prev = null;
        while (head != tail) {
            ListNode next = head.next;
            head.next = prev;

            prev = head;
            head = next;
        }

        return prev;
    }
}
