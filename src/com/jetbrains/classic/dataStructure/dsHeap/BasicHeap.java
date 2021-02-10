package com.jetbrains.classic.dataStructure.dsHeap;

public class BasicHeap {

    /*
        heap is a special tree. it need to satisfy the following conditions:
        1. heap is a complete binary tree
        2. for each node, its value should be larger than any node in left or right subtree

        time complexity for insert and remvoe are both O(nlogn)
    */
    private int[] a; //  starting from index 1
    private int n;
    private int count;

    public BasicHeap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        // time complexity: O(logn)
        if (count >= n) return;
        ++count;
        a[count] = data;

        int i = count;
        while (i/2 > 0 && a[i] > a[i/2]) {  // heapify from bottom to top
            swap(a, i, i/2);
            i = i/2;
        }
    }

    public void removeMax() {
        // time complexity: O(logn)
        if (count == 0) return;
        a[1] = a[count];
        --count;

        heapify(a, count, 1);// heapify from top to bottom
    }

    public void buildHeap(int[] a, int n) {
        // time complexity: O(nlogn)
        for (int i = n/2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    public void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }


    private void heapify(int[] a, int n, int i) {   // from top to bottom
        while (true) {
            int maxPos = i;
            if (i*2 <= n && a[i] < a[i*2]) maxPos = i*2;
            if (i*2 + 1 <= n && a[maxPos] < a[i*2 + 1]) maxPos = i*2 + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
