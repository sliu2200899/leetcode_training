package com.jetbrains.classic.searchByAlgorithm.bfs;

import java.util.*;

public class WordLadder2 {
    /*
    analyze:
        use bfs to obtains the shortest path from source word to target word
        use dfs to get the all the paths

*/
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<>();

        // nodeNeighbors and distance would be used in dfs
        Map<String, ArrayList<String>> nodeNeighbors = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();   // also act as visited set

        dict.add(start);
        bfs(start, end, dict, nodeNeighbors, distance);
//        Iterator it = nodeNeighbors.entrySet().iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
        List<String> list = new ArrayList<>();
        list.add(start);
        dfs(start, end, dict, nodeNeighbors, distance, list, res);

        return res;
    }

    private void bfs(String start, String end, Set<String> dict, Map<String, ArrayList<String>> nodeNeighbors, Map<String, Integer> distance) {
        for (String str : dict) {
            nodeNeighbors.put(str, new ArrayList<String>());
        }

        Deque<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean found = false;
            for (int i = 0; i < count; ++i) {

                String cur = queue.poll();
                int curDistance = distance.get(cur);

                for (String neighbor : getNeighbors(cur, dict)) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor)) {
                            found = true;
                        } else {
                            queue.offer(neighbor);
                        }
                    }
                }

                if (found) {
                    break;
                }
            }
        }
    }

    private List<String> getNeighbors(String cur, Set<String> dict) {

        List<String> res = new ArrayList<>();
        for (int i = 0; i < cur.length(); ++i) {
            char[] arr = cur.toCharArray();
            for (char c = 'a'; c <= 'z'; ++c) {
                arr[i] = c;
                String newStr = String.valueOf(arr);
                if (newStr.equals(cur)) continue;

                if (dict.contains(newStr)) {
                    res.add(newStr);
                }
            }
        }
        return res;
    }

    private void dfs(String cur, String end, Set<String> dict, Map<String, ArrayList<String>> nodeNeighbors, Map<String, Integer> distance, List<String> solution, List<List<String>> res) {

        if (end.equals(cur)) {
            res.add(new ArrayList<String>(solution));
            return;
        }

        for (String next : nodeNeighbors.get(cur)) {
            if (distance.get(next) == distance.get(cur) + 1) {
                solution.add(next);
                dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                solution.remove(solution.size() - 1);
            }
        }
    }
}
