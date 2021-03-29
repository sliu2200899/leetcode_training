package com.jetbrains.classic.topic.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KthFreqElementsStream {
    /*
    Given an array of n numbers. Your task is to read numbers from the array and keep at-most K numbers at the top (According to their
    decreasing frequency) every time a new number is read. We basically need to print top k numbers sorted by frequency when input stream
    has included k distinct elements, else need to print all distinct elements sorted by frequency.

    Input : arr[] = {5, 2, 1, 3, 2}
    k = 4
    Output : 5 2 5 1 2 5 1 2 3 5 2 1 3 5
    Explanation:

    After reading 5, there is only one element 5 whose frequency is max till now.
    so print 5.
    After reading 2, we will have two elements 2 and 5 with the same frequency.
    As 2, is smaller than 5 but their frequency is the same so we will print 2 5.
    After reading 1, we will have 3 elements 1, 2 and 5 with the same frequency,
    so print 1 2 5.
    Similarly after reading 3, print 1 2 3 5
    After reading last element 2 since 2 has already occurred so we have now a
    frequency of 2 as 2. So we keep 2 at the top and then rest of the element
    with the same frequency in sorted order. So print, 2 1 3 5.


    algo:
        Approach: The idea is to store the top k elements with maximum frequency. To store them a vector or an array can be used. To keep the track of
        frequencies of elements create a HashMap to store element-frequency pair. Given a stream of numbers, when a new element appears in the stream
        update the frequency of that element in HashMap and put that element at the end of the list of K numbers (total k+1 elements) now compare adjacent
        elements of the list and swap if higher frequency element is stored next to it.

    time:
        O(n)
    space:
        O(n)

     */

    Map<Integer, Integer> map;   // map to store the element-frequency pair.
    List<Integer> list;   // store teh element and sort them based on the freq
    public KthFreqElementsStream() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    // Function to print top k numbers
    public int[] read(int a, int k) {
        if (!map.containsKey(a)) {
            map.put(a, 1);
            list.add(a);
        } else {
            // update the elements in the map
            map.put(a, map.get(a) + 1);
        }
//        for (int num : map.keySet()) {
//            System.out.println(num + ": " + map.get(num));
//        }
//        System.out.println(list.toString());
        sortBySwap(a);

        int[] res = new int[list.size() > k ? k : list.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = list.get(i);
        }

        return res;
    }

    private void sortBySwap(int a) {
        int i = list.size() - 1;

        while (i >= 0 && list.get(i) != a) {
            --i;
        }

        --i;

        while (i >= 0 &&
                ((map.get(list.get(i)) < map.get(list.get(i+1))) ||
                (map.get(list.get(i)) == map.get(list.get(i+1)) && list.get(i) > list.get(i+1))) ) {
            int temp = list.get(i + 1);
            list.set(i + 1, list.get(i));
            list.set(i, temp);
            --i;
        }
    }
}
