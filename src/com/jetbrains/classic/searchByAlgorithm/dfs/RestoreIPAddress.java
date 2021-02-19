package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, res, 0, "");

        return res;
    }

    // String s is shrinked along the way
    // String path used to keep track of potential result
    private void dfs(String s, List<String> result, int k, String path) {

        if (s.length() == 0 && k == 4) {
            result.add(path.substring(1));   // skip the first character '.'
            return;
        }

        if (s.length() == 0 || k == 4) {
            return;
        }

        // key point is we just only traverse i from 1 to 3 because each part of ip address is in the range from 0 to 255
        for (int i = 1; i <= (s.charAt(0) == '0' ? 1 : 3) && i <= s.length(); ++i) {
            String part = s.substring(0, i);
            if (Integer.valueOf(part) <= 255) {
                dfs(s.substring(i), result,  k + 1, path + "." + part);
            }
        }
    }
}
