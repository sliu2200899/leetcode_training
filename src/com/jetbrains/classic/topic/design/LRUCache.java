package com.jetbrains.classic.topic.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node{
        Node prev;
        Node next;
        int key;
        int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public Node() {

        }
    }

    int cap;

    Node head, tail;
    Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        head = new Node();
        head.prev = null;


        tail = new Node();
        tail.next = null;

        head.next = tail;
        tail.prev = head;

        this.cap = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }

        remove(node);
        insert(node);

        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {

            Node newNode = new Node(key, value);
            insert(newNode);
            map.put(key, newNode);

            if (map.size() > cap) {
                Node tailNode = tail.prev;
                remove(tailNode);
                map.remove(tailNode.key);
            }
        } else {
            node.val = value;
            remove(node);
            insert(node);
        }
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void insert(Node node) {
        Node next = head.next;
        head.next = node;
        node.next = next;

        node.prev = head;
        next.prev = node;
    }

    public static void main(String[] args) {
        LRUCache solution = new LRUCache(2);
        solution.put(1, 1);
        solution.put(2, 2);
        System.out.println(solution.get(1));
    }
}
