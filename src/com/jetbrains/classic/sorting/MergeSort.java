package com.jetbrains.classic.sorting;

import com.jetbrains.innerStructure.ListNode;

public class MergeSort {

    public static void sort(int[] arr) {
        sortHelper(arr, 0, arr.length - 1);
    }

    private static void sortHelper(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            sortHelper(arr, start, mid);
            sortHelper(arr, mid + 1, end);

            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        // find sizes of the subarray to be merged
        int n1 = mid - start + 1;
        int n2 = end - mid;

        // create temp arrays
        int[] startArr = new int[n1];
        int[] endArr = new int[n2];

        // copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            startArr[i] = arr[i + start];
        }

        for (int i = 0; i < n2; ++i) {
            endArr[i] = arr[i + mid + 1];
        }

        // merge two sorted temp array
        int i = 0, j = 0;
        int base = start;
        while (i < n1 && j < n2) {
            if (startArr[i] <= endArr[j]) {
                arr[base++] = startArr[i++];
            } else {
                arr[base++] = endArr[j++];
            }
        }

        while (i < n1) {
            arr[base++] = startArr[i++];
        }

        while (i < n2) {
            arr[base++] = endArr[j++];
        }
    }



    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode fast = head, slow = head, pre = null;

        // step 1. cut the list to two halves
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = null;

        ListNode mid = slow;

        // sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(mid);

        // merge l1 and l2
        return mergeList(l1, l2);
    }

    private ListNode mergeList(ListNode t1, ListNode t2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (t1 != null && t2 != null) {
            if (t1.val < t2.val) {
                cur.next = t1;
                t1 = t1.next;
            } else {
                cur.next = t2;
                t2 = t2.next;
            }

            cur = cur.next;
        }

        if (t1 == null) {
            cur.next = t2;
        }

        if (t2 == null) {
            cur.next = t1;
        }

        return dummy.next;
    }
}
