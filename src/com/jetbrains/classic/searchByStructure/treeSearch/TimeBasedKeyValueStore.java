package com.jetbrains.classic.searchByStructure.treeSearch;

import javafx.util.Pair;

import java.util.*;

public class TimeBasedKeyValueStore {

    /*
        treemap<String, TreeMap<Integer, String>> map
        In Java, we can use TreeMap.floorKey(timestamp) to find the largest timestamp smaller than the given timestamp.

        time: O(1) for set operation and O(logN) for get operation
        space: O(n) N is the number of the entries in the timemap
    */
    Map<String, TreeMap<Integer, String>> map;
    /** Initialize your data structure here. */
    public void TimeMap() {
        map = new TreeMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";

        TreeMap<Integer, String> tree = map.get(key);
        Integer t = tree.floorKey(timestamp);
        return t != null ? tree.get(t) : "";
    }


    /*
        or we can use binary search to find the proper timestamp
     */

    /*
        treemap<String, TreeMap<Integer, String>> map
        In Java, we can use TreeMap.floorKey(timestamp) to find the largest timestamp smaller than the given timestamp.

    */
    Map<String, List<Pair<Integer, String>>> map2;
    /** Initialize your data structure here. */
    public void TimeMap2() {
        map2 = new HashMap<>();
    }

    public void set2(String key, String value, int timestamp) {
        if (!map2.containsKey(key)) {
            map2.put(key, new ArrayList<>());
        }
        map2.get(key).add(new Pair(timestamp, value));
    }

    public String get2(String key, int timestamp) {
        if (!map2.containsKey(key)) return "";

        List<Pair<Integer, String>> list = map2.get(key);
        int i = Collections.binarySearch(list, new Pair<Integer, String>(timestamp, "{"),
                (a, b) -> Integer.compare(a.getKey(), b.getKey()));

        if (i >= 0) {
            return list.get(i).getValue();
        } else if (i == -1) {
            return "";
        } else {
            return list.get(-i-2).getValue();
        }
    }
}
