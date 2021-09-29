package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisitCount {
    /*

        For an address like a.b.c, we will count a.b.c, b.c, and c. For an address like x.y, we will count x.y and y.

        To count these strings, we will use a hash map. To split the strings into the required pieces, we will use library split functions.

        time:  O(N)  where N is the length of cpdomains
        space: O(N)  the space used in our count.

     */
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        if (cpdomains == null || cpdomains.length == 0) {
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        for (String domain : cpdomains) {
            String[] parts = domain.split("\\s+");

            int num = Integer.parseInt(parts[0]);

            String[] ds = parts[1].split("\\.");

            StringBuilder sb = new StringBuilder();
            for (int i = ds.length - 1; i >= 0; --i) {
                sb.insert(0, ds[i]);

                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + num);

                sb.insert(0, ".");
            }
        }

        for (String key : map.keySet()) {
            res.add("" + map.get(key) + " " + key);
        }

        return res;
    }
}
