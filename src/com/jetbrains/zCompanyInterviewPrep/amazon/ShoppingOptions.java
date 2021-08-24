package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingOptions {
    /*
        example:
            priceOfJeans = [2,3]
            priceOfShoes = [4]
            priceOfSkirts = [2,3]
            priceOfTops = [1,2]
            budgeted = 10

        the answer would be 4..

        the function must return an integer which represents the number of options present to buy the four items
     */
    public long getNumberOfOptions(List<Integer> priceOfJeans, List<Integer> priceOfShoes, List<Integer> priceOfSkirts, List<Integer> priceOfTops, int dollars) {
        // combine 2 Lists together and sort the new list in ascending order
        List<Integer> option1 = combineListsAndSort(priceOfJeans, priceOfShoes, dollars);
        List<Integer> option2 = combineListsAndSort(priceOfSkirts, priceOfTops, dollars);

        int left = 0;
        int right = option2.size() - 1;
        long count = 0;

        while (left < option1.size() && right >= 0) {
            long sum = option1.get(left) + option2.get(right);
            if (sum <= dollars) {
                count += (right + 1);
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    private List<Integer> combineListsAndSort(List<Integer> a, List<Integer> b, int target) {
        List<Integer> results = new ArrayList<>();
        for (int price1 : a) {
            for (int price2 : b) {
                int sum = price1 + price2;
                if (sum <= target) {
                    results.add(sum);
                }
            }
        }
        Collections.sort(results);
        return results;
    }
}
