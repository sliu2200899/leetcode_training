package com.jetbrains.zCompanyInterviewPrep.amazon.vo;

import java.util.Iterator;

public class MergeTwoSortedStream {
    class MergedIterator<T extends Comparable> implements Iterator<T> {
        private Iterator<T> s1;
        private Iterator<T> s2;
        private T next1;
        private T next2;

        public MergedIterator(Iterator<T> it1, Iterator<T> it2) {
            this.s1 = it1;
            this.s2 = it2;

            next1 = (it1.hasNext() ? it1.next() : null);
            next2 = (it2.hasNext() ? it2.next() : null);
        }

        @Override
        public boolean hasNext() {
            return next1 != null || next2 != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }

            if ((next1 == null && next2 != null) ||
                    (next1 != null && next2 != null && next2.compareTo(next1) <= 0)){
                T returnObject = next2;
                next2 = (s2.hasNext() ? s2.next() : null);
                return returnObject;
            }
            else {
                T returnObject = next1;
                next1 = (s1.hasNext() ? s1.next() : null);
                return returnObject;
            }
        }
    }
}
