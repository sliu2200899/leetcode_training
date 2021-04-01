package com.jetbrains.classic.sorting;

import java.util.Arrays;

public class ReorderLogFiles {
    /*
         technique of custom sort
         in java, we can use Comparator class or we can use anonymous class inside Arrays.sort() method... directly

         what we need to do is to define our own proper comparator according to the description of the problem. We can translate the problem into the following rules:
            1). The letter-logs should be prioritized above all digit-logs.
            2). Among the letter-logs, we should further sort them firstly based on their contents, and then on their identifiers if the contents are identical.
            3). Among the digit-logs, they should remain in the same order as they are in the collection.
     */
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a, b) -> {
            int spIndA = a.indexOf(' '), spIndB = b.indexOf(' ');
            boolean isALetter = Character.isLetter(a.charAt(spIndA + 1)), isBLetter = Character.isLetter(b.charAt(spIndB + 1));
            String contA = a.substring(spIndA+1), contB = b.substring(spIndB+1);
            String idA = a.substring(0, spIndA), idB = b.substring(0, spIndB);

            if (isALetter == isBLetter) {

                if (isALetter) {
                    // first compare the content
                    if (contA.equals(contB)) {
                        // logs of same content, compare the identifiers
                        return idA.compareTo(idB);
                    }
                    return contA.compareTo(contB);
                }
                else {
                    //  both logs are digit-log
                    return 0;
                }

            } else {

                if (isALetter && !isBLetter) {
                    // The letter-logs come before all digit-logs
                    return -1;
                }
                if (isBLetter && !isALetter) {
                    return 1;
                }

                return 0;
            }
        });


        return logs;
    }
}
