package com.jetbrains.zCompanyInterviewPrep.linkedIn;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxStack {
    Deque<int[]> stack;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new int[]{x, x});
            return;
        }

        int max = Math.max(x, stack.peek()[1]);
        stack.push(new int[]{x, max});
    }

    public int pop() {
        int[] topArr = stack.pop();
        return topArr[0];
    }

    public int top() {
        return stack.peek()[0];
    }

    public int peekMax() {
        return stack.peek()[1];
    }

    public int popMax() {
        int max = peekMax();
        Deque<Integer> temp = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            int[] item = stack.pop();
            if (item[0] == max) {
                break;
            }
            temp.push(item[0]);
        }

        while (!temp.isEmpty()) {
            push(temp.pop());
        }

        return max;
    }
}
