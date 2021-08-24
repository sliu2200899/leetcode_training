package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.*;

public class TwoSumUniquePairs {
    /*
        write a function that takes a list of numbers and a target number, and then
        returns the number of unique pairs that add up to the target number.
        [x,y] and [y,x] are teh same pair.

        example
            input: [1,1,2,45,46,46] target 47
            output:  2

     */

    public int findUniquePair(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);

        dfs(res, arr, 0, target, new ArrayList<>(), new boolean[arr.length]);

        return res.size();
    }

    public void dfs(List<List<Integer>> res, int[] arr, int start, int target, ArrayList<Integer> list,
                    boolean[] visited) {
        if (list.size() == 2 && target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        if (list.size() > 2 || target < 0) {
            return;
        }

        for (int i = start; i < arr.length; ++i) {
            if (i != 0 && arr[i] == arr[i-1] && !visited[i-1]) {
                continue;
            }

            if (!visited[i]) {
                visited[i] = true;
                list.add(arr[i]);
                dfs(res, arr, i + 1, target - arr[i], list, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    public int findUniquePairs2(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        int ans = 0;
        for (int i : arr) {
            if (map.containsKey(i)) {
                int key = map.get(i) * 10 + i;
                if (!seen.contains(key)) {
                    ans++;
                    seen.add(key);
                }
            } else {
                map.put(i, target - i);
            }
        }

        return ans;
    }
}
