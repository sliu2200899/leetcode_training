package com.jetbrains.classic.stringMatch;

public class WildcardMatching {
    private boolean matched = false;
    private char[] pattern;
    private int plen;

    public WildcardMatching(char[] pattern, int plen) {   // pattern and len
        this.pattern = pattern;
        this.plen = plen;
    }

    public boolean match(char[] text, int tlen) {   // text and len
        matched = false;
        helper(0, 0, text, tlen);
        return matched;
    }

    private void helper(int ti, int pj, char[] text, int tlen) {
        // base case
        if (matched) return; // 如果已经匹配了，就不要继续递归了
        if (pj == plen) {
            if (ti == tlen) matched = true;
            return;
        }

        // recursive case
        if (pattern[pj] == '*') {
            for (int k = 0; k <= tlen - ti; ++k) {
                helper(ti + k, pj + 1, text, tlen);
            }
        } else if (pattern[pj] == '?') {
            helper(ti, pj + 1, text, tlen);
            helper(ti + 1, pj + 1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) {
            helper(ti + 1, pj + 1, text, tlen);
        }
    }

    // recursion with Memoization


    // DP
}
