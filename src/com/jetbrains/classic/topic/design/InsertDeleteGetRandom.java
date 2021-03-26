package com.jetbrains.classic.topic.design;

import java.util.*;

public class InsertDeleteGetRandom {
    /*
        analyze:
            implement the structure which provides the following operations in average O(1) time:
                insert
                delete
                getRandom

            actually, we two good candidates with O(1) average insert time:  hashmap and arraylist

                hashmap provides insert and delete in average constant time, although has problems with GetRandom
                The idea of GetRandom is to choose a random index and then to retrieve an element with that index.
                There is no indexes in hashmap, and hence to get true random value, one has first to convert hashmap keys in a list, that would take linear time.

                The solution here is to build a list of keys aside and to use this list to compute GetRandom in constant time.

                arraylist has indexes and could provide Insert and GetRandom in average constant time, though has problems with Delete.

                To delete a value at arbitrary index takes linear time. The solution here is to always delete the last value:
                    Swap the element to delete with the last one.
                    Pop the last element out.


            Time complexity. GetRandom is always O(1). Insert and Delete both have O(1) average time complexity, and O(N) in the worst-case scenario
                            when the operation exceeds the capacity of currently allocated array/hashmap and invokes space reallocation.

            Space complexity: O(N), to store N elements.
    */
    Map<Integer, Integer> map;
    List<Integer> list;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public void RandomizedSet() {
        map = new HashMap();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;

        map.put(val, list.size());
        list.add(val);

        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        // move the last element to the place idx of the element to delete
        int lastElement = list.get(list.size() - 1);
        int idx = map.get(val);
        list.set(idx, lastElement);  // swap in list
        map.put(lastElement, idx);   // update in map

        // delete teh last element
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

    /*
        follow up: how to design the data structure allowing duplicates?
     */

}
