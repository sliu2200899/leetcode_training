package com.jetbrains.classic.topic.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LFU {
    /*
        implement it using hashmap + treeset

        time: O(nlogn)
        space: O(c)   c represents teh capacity
     */
    Map<Integer, Cache> caches;
    int cap, time;
    TreeSet<Cache> set;    // track the fre and time....

    public void LFUCache(int capacity) {
        cap = capacity;
        time = 0;
        caches = new HashMap<>();
        set = new TreeSet<>();
    }

    public int get(int key) {
        if(!caches.containsKey(key)) return -1;
        time++;
        update(key, caches.get(key).value);
        return caches.get(key).value;
    }

    public void put(int key, int value) {
        if(cap <= 0) return;

        time++;
        if(caches.containsKey(key)) {
            update(key, value);
            return;
        }
        if(caches.size() == cap) {
            Cache first = set.pollFirst();
            caches.remove(first.key);
        }
        Cache curr = new Cache(key, value, 1, time);
        caches.put(key, curr);
        set.add(curr);
    }

    private void update(int key, int value) {
        Cache old = caches.get(key);
        Cache curr = new Cache(key, value, old.freq + 1, time);
        set.remove(old); // remove() uses 'equals()' to judge the equality
        set.add(curr);
        caches.put(key, curr);
    }

    class Cache implements Comparable<Cache> {
        int key;
        int value;
        int freq;
        int time;

        public Cache(int k, int v, int f, int t) {
            key = k;
            value = v;
            freq = f;
            time = t;
        }

        public boolean equals(Object o) {
            if(!(o instanceof Cache)) return false;
            return key == ((Cache) o).key;
        }

        public int hashCode() {
            return key;
        }

        public int compareTo(Cache c) {
            return freq == c.freq? time - c.time : freq - c.freq;
        }
    }


    /*
        time: O(1) for get() and put() operation
        space: O(c)  c : capacity
     */
    HashMap<Integer, DLL> freqMap = new HashMap<>(); // HashMap<freq, ddl>
    HashMap<Integer, Node> map = new HashMap<>(); // HashMap<Key, Node>
    int minFreq;
    int count, capacity;
    public void LFUCache1(int capacity) {
        this.capacity = capacity;
        count = 0;
        minFreq = 1;
        freqMap.put(1, new DLL());
    }

    public int get1(int key) {
        if(!map.containsKey(key))
            return -1;
        //1. Get the node
        Node node = map.get(key);
        //2. remove from original frequency
        DLL curList = freqMap.get(node.freq);
        curList.removeNode(node);
        //3. increase frequency + add to new frequency
        node.freq ++;
        if(!freqMap.containsKey(node.freq))
            freqMap.put(node.freq, new DLL());
        DLL newList = freqMap.get(node.freq);
        newList.addToHead(node);

        if(freqMap.get(minFreq).isEmpty())             // Update minFreq !!!
            minFreq ++;

        return node.val;
    }

    public void put1(int key, int value) {
        if(capacity == 0) return;
        if(map.containsKey(key)) {
            //1. Get the node
            Node node = map.get(key);
            //2. Change its value
            node.val = value;
            //3. remove from original frequency
            DLL curList = freqMap.get(node.freq);
            curList.removeNode(node);
            //4. increase frequency + add to new frequency
            node.freq ++;
            if(!freqMap.containsKey(node.freq))
                freqMap.put(node.freq, new DLL());
            DLL newList = freqMap.get(node.freq);
            newList.addToHead(node);

            if(freqMap.get(minFreq).isEmpty())        // Update minFreq !!!
                minFreq ++;

            return;
        }


        //1. new node with frequency 1
        Node node = new Node(key, value, 1);
        map.put(key, node);
        //2. if count > capacity, evict the last --> update the last
        count ++;
        if(count > capacity) {
            DLL remove = freqMap.get(minFreq);   // Remove minFreq !!!
            Node last = remove.popTail();
            map.remove(last.key);
            count --;
        }
        //3. add the node to the list
        if(!freqMap.containsKey(node.freq))
            freqMap.put(node.freq, new DLL());
        DLL newList = freqMap.get(node.freq);
        newList.addToHead(node);

        minFreq = 1;                                 // Update minFreq !!!
    }

    class Node {
        int key;
        int val;
        int freq;
        Node prev;
        Node next;
        public Node(int k, int v, int f) {
            key = k;
            val = v;
            freq = f;
        }
    }
    class DLL {
        Node head, tail;
        public DLL() {
            head = new Node(-1,-1,-1);
            tail = new Node(-1,-1,-1);
            head.next = tail;
            tail.prev = head;
        }
        public void addToHead(Node node) {
            Node headNext = head.next;
            head.next = node;
            node.prev = head;
            node.next = headNext;
            headNext.prev = node;
        }
        public void removeNode(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        public Node popTail() {
            Node node = tail.prev;
            if(node == head) return null;
            Node prevNode = node.prev;
            prevNode.next = tail;
            tail.prev = prevNode;
            node.next = null;
            node.prev = null;
            return node;
        }
        public boolean isEmpty() {
            return head.next == tail;
        }
    }
}
