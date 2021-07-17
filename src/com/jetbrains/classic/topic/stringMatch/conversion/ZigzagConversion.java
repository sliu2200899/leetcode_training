package com.jetbrains.classic.topic.stringMatch.conversion;

import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {
    /*
    clarify:
        1. input, output, example
        2.
    algo:
        s = "PAYPALISHIRING", numRows = 4
        PAYPALISHIRING

        P     I     N     -> row 0
        A   L S   I G     -> row 1
        Y A   H  R        -> row 2
        P     I           -> row 3

        map row as key and list of chars as value
        PAYPALISHIRING
        convert the i to the row no.
           0 -> 0
           1 -> 1
           2 -> 2
           3 -> 3
           4 -> 2
           5 -> 1
           6 ...0
           7 -> 1

           boolean turn

        time: O(n)  where n == len(s)
        space: O(n)
*/
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            rows.add(new StringBuilder());
        }

        /*
            Iterate through s from left to right, appending each character to the appropriate row.
            The appropriate row can be tracked using two variables: the current row and the current direction.
            The current direction changes only when we moved up to the topmost row or moved down to the bottommost row.
         */

        boolean turn = true;
        int curRow = 0;

        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);

            rows.get(curRow).append(ch);
            // rule !!!
            curRow += (turn ? 1 : -1);
            if (curRow == 0 || curRow == numRows - 1) {
                turn = !turn;
            }
        }

        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < numRows; ++i) {
            ret.append(rows.get(i).toString());
        }
        return ret.toString();
    }
}
