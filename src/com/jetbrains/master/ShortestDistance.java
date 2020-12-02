package com.jetbrains.master;

public class ShortestDistance {
    /*
        solution 1: brute force
     */

    /*
        solution 2: One pass
        We can greatly improve on the brute-force approach by keeping two indices i1 and i2 where we store the most recent locations
        of word1 and word2. Each time we find a new occurrence of one of the words, we do not need to search the entire array for the other word,
        since we already have the index of its most recent occurrence.
     */

    public int shortestDistance(String[] words, String word1, String word2) {
        // hashMap

        // word1, ..., word2, .., word2,., word1, ..., word2
        // scan the array, and update the index for word1 and word2 when we meet them along the way.
        // and get the minimum distance between word1 and word2 at the smae time.
        int min = words.length - 1, index1 = -1, index2 = -1;
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1)) {
                index1 = i;
            } else if (words[i].equals(word2)) {
                index2 = i;
            }

            if (index1 != -1 && index2 != -1) {
                min = Math.min(min, index2 > index1 ? index2 - index1 : index1 - index2);
            }
        }

        return min;
    }
}
