package com.jetbrains.classic.topic.design;

import java.util.HashMap;
import java.util.Map;

public class LRU {

    /*
        requirement:
            1. access to a random key in O(1) -> hashtable
            2. remove the last entry in LRU cache in O(1)  -> list, stack
            3. add/move an entry to the front of LRU cache in O(1)  -> list

        The problem can be solved with a hashtable that keeps track of the keys and its values in the double linked list.
        One interesting property about double linked list is that the node can remove itself without other reference.
        In addition, it takes constant time to add and remove nodes from the head or tail.

        One particularity about the double linked list that I implemented is that I create a pseudo head and tail to mark the boundary,
        so that we don't need to check the NULL node during the update. This makes the code more concise and clean, and also it is good for the performance.

        Time complexity : O(1) both for put and get.
        space complexity: O(capacity)
     */
    private class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    private void addNode(DLinkedNode node) {
    /*
        always add the new node right after head
    */
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
    /*
        remove an existing node from the linked list
    */

        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DLinkedNode node) {
    /*
        move certain node in between to the head
    */
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail() {
    /*
        pop the current tail
    */
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    public void LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.prev = null;

        tail = new DLinkedNode();
        tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        // move the accessed node to the head;
        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);

            ++size;

            if (size > capacity) {
                // pop the tail
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            // update the value.
            node.value = value;
            moveToHead(node);
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
