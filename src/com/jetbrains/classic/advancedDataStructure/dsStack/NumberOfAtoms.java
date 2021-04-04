package com.jetbrains.classic.advancedDataStructure.dsStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

public class NumberOfAtoms {
    /*
        recursion:

        We will put i in global state: our parse function increments i throughout any future calls to parse.

        When we see a '(', we will parse whatever is inside the brackets (up to the closing ending bracket) and add it to our count.

        Otherwise, we should see an uppercase character: we will parse the rest of the letters to get the name, and add that (plus the multiplicity if there is one.)

        At the end, if there is a final multiplicity (representing the multiplicity of a bracketed sequence), we'll multiply our answer by this.

        time:  O(n^2) where N is the length of the formula. It is O(N) to parse through the formula, but each of O(N) multiplicities after a bracket may increment the count of each name in the formula
        space: O(n)
     */
    private int i;
    public String countOfAtoms(String formula) {
        StringBuilder ans = new StringBuilder();
        i = 0;
        Map<String, Integer> map = countOfAtoms(formula.toCharArray());
        for (String key : map.keySet()) {
            ans.append(key);
            int count = map.get(key);
            if (count > 1) ans.append(""+count);
        }
        return ans.toString();
    }

    private Map<String, Integer> countOfAtoms(char[] arr) {
        Map<String, Integer> ans = new TreeMap<>();
        while (i < arr.length) {
            if (arr[i] == '(') {
                i++;
                Map<String, Integer> tmp = countOfAtoms(arr);
                int count = getCount(arr);
                for (Map.Entry<String, Integer> entry : tmp.entrySet()) {
                    ans.put(entry.getKey(),
                            ans.getOrDefault(entry.getKey(), 0)
                                    + entry.getValue() * count);
                }
            } else if (arr[i] == ')') {
                i++;
                return ans;
            } else {
                String name = getName(arr);
                ans.put(name, ans.getOrDefault(name, 0) + getCount(arr));
            }
        }

        return ans;
    }

    private String getName(char[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[i++]);

        while (i < arr.length && Character.isLowerCase(arr[i])) {
            sb.append(arr[i++]);
        }

        return sb.toString();
    }

    private int getCount(char[] arr) {
        int num = 0;
        while (i < arr.length && Character.isDigit(arr[i])) {
            num = num * 10 + arr[i] - '0';
            ++i;
        }
        return num == 0 ? 1 : num;
    }

    /*
        stack, treeMap

     */
    public String countOfAtoms2(String formula) {
        Deque<Map<String, Integer>> stack = new ArrayDeque<>();
        Map<String, Integer> map = new TreeMap<>();

        int i = 0, n = formula.length();
        while (i < n) {
            char c = formula.charAt(i);
            i++;
            if (c == '(') {
                stack.push(map);
                map = new TreeMap<>();
            } else if (c == ')') {
                //
                int val = 0;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    val = val * 10 + formula.charAt(i++) - '0';
                }
                int count = val == 0 ? 1 : val;

                if (!stack.isEmpty()) {
                    Map<String, Integer> tmp = map;
                    map = stack.pop();
                    for (Map.Entry<String, Integer> entry : tmp.entrySet()) {
                        String key = entry.getKey();
                        map.put(key, map.getOrDefault(key, 0) + tmp.get(key) * count);
                    }
                }

            } else {
                int start = i - 1;
                while (i < n && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(start, i);

                int val = 0;
                while (i < n && Character.isDigit(formula.charAt(i))) val = val * 10 + formula.charAt(i++) - '0';
                int count = val == 0 ? 1 : val;

                map.put(name, map.getOrDefault(name, 0) + count);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(key);
            int count = map.get(key);
            if (count > 1) sb.append(count);
        }

        return sb.toString();
    }
}
