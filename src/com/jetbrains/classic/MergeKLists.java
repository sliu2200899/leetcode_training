package com.jetbrains.classic;

import com.jetbrains.innerStructure.ListNode;

import java.util.PriorityQueue;

public class MergeKLists {
    /*
        pair up k lists and merge each pair
        after the first pairing, k lists are merged into k/2 lists with average 2N/k length, then, k/4, k/8 and so on.
        repeat this procedure until we get the final sorted linked list

        The complexity analysis here is a bit confusing, because N is the total number of nodes. If we express the complexity in the terms of average size of each list "n", it would be better to catch it (at least for me).
        E.g. the Approach 4: Merge lists one by one. Say, for the sake of simplicity, all lists have the same size "n".
        At the first step of the algorithm we merge 2 lists with O(n) and get a list with size "2n".
        At the second step we merge a "2n" list with a "n" list, in the worse case we have to visit "2n" nodes
        and get a list with size "3n". And so on. At the end we have n + 2n + ... + kn = n(1+2 + .. + k) = n * k * (k + 1) / 2.
        So in average we have here O(nk^2).
        
        In the Approach 5 we have then O(nk*log(k)).
        Don't confuse N and n on the real interview :)

        time: O(nklogk)    where n is teh average size of nodes in a linked list, k is the number of the input linked lists
        space:  O(1)  we can merge the linked list in constant space
     */
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
