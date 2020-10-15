package com.jetbrains.classic;

import java.util.Arrays;

public class ArrayQueue {
    private String[] items;
    private int head = 0;
    private int tail = 0;
    private int n = 0;

    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }
    // amotised O(1),
    // normal case is O(1), when tail == n, the time complexity will becomes O(n)
    public boolean enqueue(String item) {
        // queue is full
        if (head == 0 && tail == n) {
            return false;
        }
        else if (head != 0 && tail == n) {
            // need data left shift
            for (int i = head; i < tail; ++i) {
                items[i - head] = items[i];
            }
            head = 0;
            tail = tail - head;
        }
        items[tail++] = item;
        return true;
    }
    // time complexity is O(1)
    public String dequeue() {
        // if head == tail, queue is empty
        if (head == tail) return null;
        return items[head++];
    }

    public void printAllData() {
        System.out.println(Arrays.asList(items));
    }
}
