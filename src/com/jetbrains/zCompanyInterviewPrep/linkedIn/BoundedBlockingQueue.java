package com.jetbrains.zCompanyInterviewPrep.linkedIn;

import java.util.Deque;
import java.util.LinkedList;

public class BoundedBlockingQueue {

    Deque<Integer> q = new LinkedList<>();
    int capacity;

    // Synchronization object
    Object lock = new Object();

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized(lock) {
            while (q.size() >= capacity) {
                lock.wait();
            }

            q.offer(element);
            // wake up the threads
            lock.notifyAll();
        }
    }

    public int dequeue() throws InterruptedException {
        int val = 0;
        synchronized(lock) {
            // again it is possible some other thread has taken the last element.
            while (q.size() == 0) {
                lock.wait();
            }
            val = q.poll();
            lock.notifyAll();
        }
        return val;
    }

    public int size() {
        return q.size();
    }
}