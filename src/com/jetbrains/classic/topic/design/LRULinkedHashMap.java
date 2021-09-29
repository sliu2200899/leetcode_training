package com.jetbrains.classic.topic.design;

import java.util.LinkedHashMap;

public class LRULinkedHashMap {
    LinkedHashMap<Integer, Integer> map;
    int cap;
    public LRULinkedHashMap(int capacity) {
        this.map = new LinkedHashMap<>();
        this.cap = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        int res = map.get(key);
        map.remove(res);
        map.put(key, res);

        return res;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
        } else {
            map.put(key, value);

            if (map.size() > cap) {
                Integer last = map.keySet().iterator().next();
                map.remove(last);
            }
        }
    }
}
