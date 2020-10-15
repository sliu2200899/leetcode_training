package com.jetbrains.classic;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

public class LoopArrayQueue {
    private String[] items;
    private int head = 0;
    private int tail = 0;
    private int n = 0;

    public LoopArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }


    public boolean enqueue(String item) {
        // if (tail + 1) % n == head, queue is full
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;   // for the loop array
        return true;
    }


    public String dequeue() {
        // if head == tail, queue is empty
        if (head == tail) return null;
        String res = items[head];
        head = (head + 1) % n;   // for the loop array
        return res;
    }

    public void printAllData() {
        for (int i = head; i != (tail % n); i = (i + 1) % n) {
            System.out.println(items[i]);
        }
    }
}
