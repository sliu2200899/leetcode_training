package com.jetbrains.master;

import java.util.Iterator;

public class StackOfLinked<T> implements Iterable<T> {
    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T i) {
            this.item = i;
            this.next = null;
        }
    }

    private Node<T> first;
    private int N;

    public StackOfLinked() {
    }

    // implement the stack using linked list, it's better to add / remove element from the head of the list
    public void push(T i) {
        Node<T> newNode = new Node<>(i);
        newNode.next = first;

        first = newNode;
        N++;
    }

    public T pop() {
        T i = first.item;
        first = first.next;
        N--;

        return i;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public T peek() {
        return first.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<T>(this);
    }

    class LinkedIterator<T> implements Iterator<T> {
        int i;
        Node<T> t;
        public LinkedIterator(StackOfLinked<T> stackl) {
            this.i = stackl.N;
            this.t = stackl.first;
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            T item = (T) t.item;
            t = t.next;
            i--;
            return item;
        }
    }
}
