package com.jetbrains.hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class MaxSlidingWindow {
    // just use priorityqueue to store the elements in the windows
    // time compleity is O(nlogn)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int len = nums.length;
        int[] res = new int[len - k + 1];

        PriorityQueue<Integer> pqmax = new PriorityQueue<>((a, b) -> {
            return Integer.compare(b, a);
        });

        for (int i = 0; i < k; ++i) {
            pqmax.offer(nums[i]);
        }

        res[0] = pqmax.peek();

        for (int i = k; i < len; ++i) {
            pqmax.offer(nums[i]);
            pqmax.remove(nums[i - k]);
            res[i - (k - 1)] = pqmax.peek();
        }

        return res;
    }


    // time complexity is O(n)
    // space is O(n)
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();

        int[] rst = new int[n - k + 1];
        for (int i = 0; i < k - 1; ++i) {
            enqueue(deque, nums[i]);
        }

        for (int i = k - 1; i < n; ++i) {
            enqueue(deque, nums[i]);
            rst[i - (k - 1)] = deque.peekFirst();
            dequeue(deque, nums[i - k + 1]);
        }

        return rst;
    }

    private void enqueue(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) {
            deque.pollLast();
        }
        deque.offerLast(num);
    }

    private void dequeue(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }
}
