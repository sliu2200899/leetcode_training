package com.jetbrains.OOD.polymorphism;

public class DynamicArray {
    private static final int DEFAULT_CAPACITY = 10;
    protected int size = 0;
    protected int capacity = DEFAULT_CAPACITY;
    protected Integer[] elements = new Integer[DEFAULT_CAPACITY];

    public int size() { return this.size; }
    public Integer get(int index) { return elements[index]; }
    public void add(Integer e) {
        ensureCapacity();
        elements[size++] = e;
    }

    protected void ensureCapacity() {
        if (size == capacity) {
            capacity = size * 2 + 1;
            Integer[] tmp = new Integer[capacity];
            System.arraycopy(elements, 0, tmp, 0, size);
        }
    }
}
