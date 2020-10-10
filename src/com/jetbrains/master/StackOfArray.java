package com.jetbrains.master;

import java.util.Iterator;

public class StackOfArray<T> implements Iterable<T> {
    private T[] items;  // array
    private int count;   // number of elements in the array

    public StackOfArray() {
        this.items = (T[])new Object[1];
        this.count = 0;
    }

    public void push(T item) {
        if (count == items.length) resize(items.length * 2);
        items[count++] = item;
    }

    public T pop() {
        if (count == 0) return null;
        T item = items[--count];
        items[count] = null;
        if (count > 0 && count == items.length / 4) resize(items.length / 2);
        return item;
    }

    public int size() {
        return this.count;
    }

    private void resize(int length) {
        T[] res = (T[])new Object[length];
        System.arraycopy(this.items, 0, res, 0, items.length);

        this.items = res;
    }

    public T peek() {
        return items[count - 1];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(this);
    }

    class ArrayIterator<T> implements Iterator<T> {
        //
        int i;
        T[] nodes;

        public ArrayIterator(StackOfArray<T> stack) {
            this.i = stack.count;
            this.nodes = stack.items;
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return nodes[--i];
        }
    }
}
