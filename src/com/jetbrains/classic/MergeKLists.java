package com.jetbrains.classic;

import com.jetbrains.innerStructure.ListNode;

import java.util.PriorityQueue;

public class MergeKLists {
    // time: O(nlogK)    where n is the number of nodes in the final linked list, k is the number of the input linked lists
    // space:  O(k)
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (pq.size() != 0) {
            ListNode node = pq.poll();
            cur.next = node;

            if (node.next != null) {
                pq.offer(node.next);
            }
            cur = cur.next;
        }

        return dummy.next;
    }


    // time: O(nlogK)
    // space:  O(1)
    public static ListNode mergeKList2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        /*
            1. Pair up k lists and merge each pair.
            2. After the first pairing, k lists are merged into k/2 lists with average 2N/k length,
                then k/4, k/8 and so on.
            3. Repeat this procedure until we get the final sorted linked list.
         */
        int interval = 1;
        while (interval < lists.length) {

            for (int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = mergeTwoList(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }

        return lists[0];
    }

    private static ListNode mergeTwoList(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (a != null && b != null) {
            if (a.val > b.val) {
                cur.next = b;
                b = b.next;
            } else {
                cur.next = a;
                a = a.next;
            }
            cur = cur.next;
        }

        if (a != null) {
            cur.next = a;
        }
        if (b != null) {
            cur.next = b;
        }

        return dummy.next;
    }
}
