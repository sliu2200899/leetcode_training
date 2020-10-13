package com.jetbrains.classic;

import java.util.ArrayDeque;
import java.util.Deque;

public class ImplStackUsingQueue {
    private Deque<Integer> q1 = new ArrayDeque<>();
    private Deque<Integer> q2 = new ArrayDeque<>();

    /** Initialize your data structure here. */
    public ImplStackUsingQueue() {
    }

    /** Push element x onto stack. */
    public void push(int x) {

        // time complexity: O(n)
        // space complexity: O(1)
        q1.add(x);
        int sz = q1.size();
        while (sz > 1) {
            q1.offer(q1.poll());
            sz--;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q1.poll();
    }

    /** Get the top element. */
    public int top() {
        return q1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}
