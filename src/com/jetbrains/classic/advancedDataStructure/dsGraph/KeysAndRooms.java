package com.jetbrains.classic.advancedDataStructure.dsGraph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Deque<Integer> stack = new ArrayDeque<>();
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        stack.add(0);

        while (!stack.isEmpty()) {
            int i = stack.pop();
            for (int key : rooms.get(i)) {
                if (!visited.contains(key)) {
                    visited.add(key);
                    stack.offer(key);

                    if (rooms.size() == visited.size()) return true;
                }
            }
        }

        return rooms.size() == visited.size();
    }
}
