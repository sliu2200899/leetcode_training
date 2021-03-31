package com.jetbrains.classic.searchByStructure.hashingSearch;

import java.util.*;

public class MostVisitedPattern {
    private class Pair{
        int time;
        String web;
        public Pair(int time, String web) {
            this.time = time;
            this.web = web;
        }
    }
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> map = new HashMap<>();   // key: username. value: Pair<time, web>
        int n = username.length;

        // collect the website infor for each user
        for (int i = 0; i < n; ++i) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Pair(timestamp[i], website[i]));
        }
        // count map to record every 3 combination occuring time for the different user.
        Map<String, Integer> count = new HashMap<>();    // key: web pattern string, value: frequency
        String res = "";
        for (String key : map.keySet()) {
            Set<String> set = new HashSet<>();
            // this set is to avoid visit the same 3-seq in one user
            List<Pair> list = map.get(key);
            Collections.sort(list, (a, b) -> (a.time - b.time)); // sort by time
            // brute force
            for (int i = 0; i < list.size(); ++i) {
                for (int j = i + 1; j < list.size(); ++j) {
                    for (int k = j + 1; k < list.size(); ++k) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(list.get(i).web).append(" ")
                                .append(list.get(j).web).append(" ")
                                .append(list.get(k).web);

                        String str = sb.toString();
                        if (!set.contains(str)) {
                            count.put(str, count.getOrDefault(str, 0) + 1);
                            set.add(str);
                        }

                        if (res.equals("") || count.get(res) < count.get(str) || (count.get(res) == count.get(str) && res.compareTo(str) > 0)) {
                            // make sure the right lexi order, we only need to return one answer
                            res = str;
                        }
                    }

                }
            }
        }

        // grab the right answer
        String[] r = res.split(" ");
        List<String> result = new ArrayList<>();
        for (String str : r) {
            result.add(str);
        }

        return result;
    }
}
