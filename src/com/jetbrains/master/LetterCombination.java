package com.jetbrains.master;

import java.util.*;

public class LetterCombination {
    /*
        backtracking
        Backtracking is an algorithm for finding all solutions by exploring all potential candidates.
        If the solution candidate turns to be not a solution (or at least not the last one), backtracking algorithm discards it
        by making some changes on the previous step, i.e. backtracks and then try again.

        So many people here do not have a general grasp on DSA and only knows how to grind questions.
        Backtracking is basically any DFS implementation that is used on anything other than a graph.
        Recursion is an implementation and backtracking/DFS is an algo. Just because it doesn't follow the push/pop backtracking
        template used in combination/permutation questions doesn't mean it's not backtracking. Any time whenever you have several
        branches for each recursion level and you need to explore all of these branches one by one via exhaustive enumeration it's called backtracking.
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) return list;

        map = new HashMap<>();
        fillMap(map);

        dfs(digits, 0, "", list);

        return list;
    }

    private void dfs(String digits, int index, String s, List<String> list) {
        if (index == digits.length()) {
            list.add(s);
            return;
        }

        for (Character c : map.get(digits.charAt(index))) {
            dfs(digits, index+1, s + c, list);
        }
    }

    private void fillMap(Map<Character, ArrayList<Character>> map) {
        map.put('2', new ArrayList<>(Arrays.asList('a', 'b', 'c')));
        map.put('3', new ArrayList<>(Arrays.asList('d', 'e', 'f')));
        map.put('4', new ArrayList<>(Arrays.asList('g', 'h', 'i')));
        map.put('5', new ArrayList<>(Arrays.asList('j', 'k', 'l')));
        map.put('6', new ArrayList<>(Arrays.asList('m', 'n', 'o')));
        map.put('7', new ArrayList<>(Arrays.asList('p', 'q', 'r', 's')));
        map.put('8', new ArrayList<>(Arrays.asList('t', 'u', 'v')));
        map.put('9', new ArrayList<>(Arrays.asList('w', 'x', 'y', 'z')));
    }

    private Map<Character, ArrayList<Character>> map;
}
