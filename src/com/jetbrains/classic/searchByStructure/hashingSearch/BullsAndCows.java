package com.jetbrains.classic.searchByStructure.hashingSearch;

import java.util.HashMap;
import java.util.Map;

public class BullsAndCows {
    /*
        two pass solution
     */
    public String getHint(String secret, String guess) {
        Map<Character, Integer> h = new HashMap<>();
        for (char s : secret.toCharArray()) {
            h.put(s, h.getOrDefault(s, 0) + 1);
        }

        int bulls = 0, cows = 0;
        int n = guess.length();
        for (int idx = 0; idx < n; ++idx) {
            char ch = guess.charAt(idx);
            if (h.containsKey(ch)) {
                // corresponding characters match
                if (ch == secret.charAt(idx)) {
                    // update the bulls
                    bulls++;
                    h.put(ch, h.get(ch) - 1);
                    // update the cows
                    // if all ch characters from secret were used up
                    if (h.get(ch) < 0) {
                        cows--;
                    }
                } else {
                    h.put(ch, h.get(ch) - 1);
                    // update the cows
                    if (h.get(ch) >= 0) {
                        cows++;
                    }
                }
            }
        }
        return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
    }

    /*
        one pass
        time: O(n)
        space: O(1)
     */
    public String getHint2(String secret, String guess) {
        int len = secret.length();
        int[] secretarr = new int[10];
        int[] guessarr = new int[10];

        int bull = 0, cow = 0;
        for (int i = 0; i < len; ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bull++;
            }
            else {
                secretarr[secret.charAt(i) - '0']++;
                guessarr[guess.charAt(i) - '0']++;
            }
        }

        for (int i = 0; i < 10; ++i) {
            cow += Math.min(secretarr[i], guessarr[i]);
        }

        return bull + "A" + cow + "B";
    }
}
