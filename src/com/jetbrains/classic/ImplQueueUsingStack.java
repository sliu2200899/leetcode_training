package com.jetbrains.classic;

import java.util.ArrayDeque;
import java.util.Deque;

public class ImplQueueUsingStack {

    // first method each push operation, I will keep the s1 stack ordered by using two stacks
    private Deque<Integer> s1 = new ArrayDeque<>();
    private Deque<Integer> s2 = new ArrayDeque<>();

    /** Initialize your data structure here. */
    public ImplQueueUsingStack() {
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s2.push(x);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return s1.pop();
    }

    /** Get the front element. */
    public int peek() {
        return s1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty();
    }









    // second method each pop operation, I will keep partial order for the s2.
//    private Deque<Integer> s1 = new ArrayDeque<>();
//    private Deque<Integer> s2 = new ArrayDeque<>();
    private int front;

    /** Initialize your data structure here. */
//    public ImplQueueUsingStack() {
//
//    }

    /** Push element x to the back of queue. */
    public void push2(int x) {
        if (s1.isEmpty()) {
            front = x;
        }
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop2() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    /** Get the front element. */
    public int peek2() {
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        return front;
    }

    /** Returns whether the queue is empty. */
    public boolean empty2() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
