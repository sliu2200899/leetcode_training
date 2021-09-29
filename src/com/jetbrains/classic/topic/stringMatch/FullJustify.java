package com.jetbrains.classic.topic.stringMatch;

import java.util.ArrayList;
import java.util.List;

public class FullJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        /*
        General Approach:

        1.Calculate the maximum number of word each line could pack -> count
        2.Calculate the length of the space of each line = maxWidth - count
        3.Distribute the space as evenly as possible, if we could not distribute evenly, we add the reminder space from left to right
            i.At the last line - we should deal as special case
            ii.Not at the last line - be careful of the case when number of the space is not even
       */
        List<String> res = new ArrayList<>();
        if(words == null || words.length == 0) {
            return res;
        }

        int index = 0;

        while (index < words.length) {
            int last = index + 1;
            int count = words[index].length(); // count : the length of all words at this line
            while (last < words.length) {
                if (words[last].length() + 1 + count > maxWidth) break;
                count += words[last].length() + 1;
                last++;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(words[index]);

            // diff represents how many gap we could insert our space b.w each word
            // "a b c" -> diff = 2, "a" -> diff = 0.    diff = the number of word - 1
            int diff = last - index - 1;

            // last line -> insert only one space b.w each word,
            if (last == words.length || diff == 0) {
                for (int i = index + 1; i < last; ++i) {
                    sb.append(" ");
                    sb.append(words[i]);
                }

                for (int i = sb.length(); i < maxWidth; ++i) {
                    sb.append(" ");
                }
            }
            else {
                // not the last line case
                int space = (maxWidth - count) / diff; // the number of space after each word
                int r = (maxWidth - count) % diff; // remaining number of sapce which we should insert from left to right

                for (int i = index + 1; i < last; ++i) {
                    for (int k = space; k > 0; --k) {
                        sb.append(" ");
                    }

                    if (r > 0) {
                        sb.append(" ");
                        r--;
                    }

                    sb.append(" ");
                    sb.append(words[i]);
                }

            }

            res.add(sb.toString());
            index = last;
        }

        return res;
    }
}
