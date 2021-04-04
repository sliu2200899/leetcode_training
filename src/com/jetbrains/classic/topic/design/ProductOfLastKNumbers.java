package com.jetbrains.classic.topic.design;

import java.util.ArrayList;
import java.util.List;

public class ProductOfLastKNumbers {

    List<Integer> list;
    public void ProductOfNumbers() {
        list = new ArrayList<>();
        list.add(1);
    }

    public void add(int num) {
        if (num > 0) {
            list.add(list.get(list.size() - 1) * num);
        } else {
            list = new ArrayList<>();
            list.add(1);
        }
    }

    public int getProduct(int k) {
        int n = list.size();
        if (k >= n) {
            return 0;
        }
        return list.get(n - 1) / list.get(n - k - 1);
    }
}
