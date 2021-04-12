package com.jetbrains.classic.topic.stringMatch.conversion;

public class LCP {
    /*
        approach: find hte smallest string and try to find hte LCP
     */
        /*
        clarify:
            1. input, output, example
            2.
        algo:
            strs = ["flower","flow","flight"]

            flower
            flow
            flight

            return "fl"

    */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int minLen = Integer.MAX_VALUE;
        String s = null;
        for (int i = 0; i < strs.length; ++i) {
            if (strs[i].length() == 0) {
                return "";
            }
            if (minLen > strs[i].length()) {
                s = strs[i];
                minLen = s.length();
            }
        }

        int index = 0;
        while (index < minLen) {
            char ch = s.charAt(index);
            for (int i = 0; i < strs.length; ++i) {
                if (strs[i].charAt(index) != ch) {
                    return s.substring(0, index);
                }
            }
            index++;
        }
        return s.substring(0, index);
    }

    /*
        D/C
        time: O(S) where S is the nunmber of all characters in the array, S = m * n
                T(n) = 2 * T(n/2) + O(m)
                     = (2 ^ x - 1) * O(m)
                     = m * n   (since 2 ^ x == n)
        space: O(m*logn)
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        }

        int mid = l + (r - l) / 2;
        String lcpLeft = longestCommonPrefix(strs, l, mid);
        String lcpRight = longestCommonPrefix(strs, mid + 1, r);

        return commonPrefix(lcpLeft, lcpRight);
    }

    private String commonPrefix(String l, String r) {
        int minLen = l.length() > r.length() ? r.length() : l.length();

        int index = 0;
        while (index < minLen) {
            if (l.charAt(index) != r.charAt(index)) {
                return l.substring(0, index);
            }
            index++;
        }
        return l.substring(0, index);
    }
}
