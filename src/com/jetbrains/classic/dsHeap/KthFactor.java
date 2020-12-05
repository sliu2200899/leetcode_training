package com.jetbrains.classic.dsHeap;

import java.util.PriorityQueue;

public class KthFactor {


    /*
        method 1: brute force
        just iterate from 1 to n / 2 + 1, will check each number on hte fly...
     */
    public int kthFactor(int n, int k) {
        for (int x = 1; x < n / 2 + 1; ++x) {
            if (n % x == 0) {
                --k;
                if (k == 0) {
                    return x;
                }
            }
        }

        return k == 1 ? n : -1;
    }


    /*
        method 2: Heap
        algo:  iterate from 1 to sqrt(N), and push each divisor x and its pair N/x into max heap of size k

        time: O(N ^ (1/2) * log k)
        space: O(min(k, N ^ (1/2))

     */

    // max heap -> to keep max element always on top
    PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public void heappushK(int x, int k) {
        heap.add(x);
        if (heap.size() > k) {
            heap.remove();
        }
    }

    public int kthFactor2(int n, int k) {
        int sqrtN = (int) Math.sqrt(n);
        for (int x = 1; x < sqrtN + 1; ++x) {
            if (n % x == 0) {
                heappushK(x, k);
                if (x != n / x) {
                    heappushK(n / x, k);
                }
            }
        }

        return k == heap.size() ? heap.poll() : -1;
    }
}
