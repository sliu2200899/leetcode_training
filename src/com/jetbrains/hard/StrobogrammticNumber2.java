package com.jetbrains.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrobogrammticNumber2 {

    /*

    It's given that the length of the number is n,
    if n = 1,  0   1   8
    if n = 2,              11    88     69     96
    if n = 3, 101 111 181
              808 818 888
              609 619 689
              906 916 986
    if n = 4,             1111  1881   1691   1961   1001
                          8118  8888   8698   8968   8008
                          6119  6889   6699   6969   6009
                          9116  9886   9696   9966   9006

    if n = ..., ...
    After careful observation, it's inductive that
    if s is strobogrammatic
      1s1,
      8s8,
      6s9,
      9s6,
    ..0s0
          are also strobogrammatic.
    So we start from l = 0, r = n - 1,
    enumerate all possible values that ensure [l,r] to be strobogrammatic if [l + 1, r - 1] are strobogrammatic,
    and we move forward to the next step,
    we end when l >= r.

     */
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    private List<String> helper(int n, int m) {
        // base case
        if (n == 0) return new ArrayList<String>(Arrays.asList(""));
        if (n == 1) return new ArrayList<String>(Arrays.asList("1", "0", "8"));

        // recursive case
        List<String> list = helper(n - 2, m);

        List<String> res = new ArrayList<>();

        for (int i = 0; i < list.size(); ++i) {
            String s = list.get(i);

            if (n != m) res.add("0" + s + "0");

            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }

        return res;
    }
}
