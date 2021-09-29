package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.*;

public class MaxStack {
    /*
        two stack approach

        time: O(N)
        space: O(N)


        Deque<Integer> stack;
        Deque<Integer> maxStack;

        public MaxStack() {
            stack = new ArrayDeque<>();
            maxStack = new ArrayDeque<>();
        }

        public void push(int x) {

            if (stack.isEmpty()) {
                stack.push(x);
                maxStack.push(x);
            } else {
                int top = maxStack.peek();
                maxStack.push(Math.max(top, x));
                stack.push(x);
            }
        }

        public int pop() {
            maxStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            int max = maxStack.peek();
            Deque<Integer> tmp = new ArrayDeque<>();
            while (max != stack.peek()) {
                tmp.push(pop());
            }
            pop();
            while (!tmp.isEmpty()) {
                push(tmp.pop());
            }
            return max;
        }
     */


    /*
        Double linked list  + treeMap
     */
    TreeMap<Integer, List<Node>> map;
    DLL dll;

    /** initialize your data structure here. */
    public MaxStack() {
        map = new TreeMap<>();
        dll = new DLL();
    }

    public void push(int x) {
        Node node = dll.add(x);
        map.putIfAbsent(x, new ArrayList<>());
        map.get(x).add(node);
    }

    public int pop() {
        int val = dll.pop();
        List<Node> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) map.remove(val);
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<Node> L = map.get(max);
        Node node = L.remove(L.size() - 1);
        dll.unlink(node);
        if (L.isEmpty()) map.remove(max);
        return max;
    }

    class DLL {
        Node head, tail;
        public DLL() {
            head = new Node(0);
            head.prev = null;
            tail = new Node(0);
            tail.next = null;

            head.next = tail;
            tail.prev = head;
        }

        public Node add(int val) {
            Node x = new Node(val);
            x.next = tail;
            x.prev = tail.prev;
            tail.prev = tail.prev.next = x;
            return x;
        }

        public int pop() {
            return unlink(tail.prev).val;
        }

        public int peek() {
            return tail.prev.val;
        }

        public Node unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
    }

    class Node {
        int val;
        Node prev, next;
        public Node(int v) {
            this.val = v;
        }
    }

}
