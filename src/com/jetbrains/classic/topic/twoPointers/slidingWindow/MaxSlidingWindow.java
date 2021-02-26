package com.jetbrains.classic.topic.twoPointers.slidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class MaxSlidingWindow {
    // just use priorityqueue to store the elements in the windows   TLE
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
    /*

    Algorithm

        The algorithm is quite straigthforward :

        Process the first k elements separately to initiate the deque.

        Iterate over the array. At each step :
            enque(deque, nums[i])
            res[i - k + 1] = deque.peekFirst()
            deque(deque, nums[i - k + 1])


        enque:
            remove all the elements that is smaller than the current element
            Keep only elements that is larger than the cur in the sliding window
            in this case, first element in the deque is the largest element in the sliding window

        deque
            remove teh elements if deque.peekFirst() == num


        Return the output array.

     */
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

    /*
        use deque to solve the problem
        here deque is a combination of queue and stack
     */
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
