package com.jetbrains.hard;

import java.util.LinkedList;

public class MinStack {
    LinkedList<int[]> stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        int[] arr = new int[]{x, x};
        if (!stack.isEmpty()) {
            arr[1] = Math.min(x, stack.peek()[1]);
        }
        stack.push(arr);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}
