package com.jetbrains.zCompanyInterviewPrep.linkedIn;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        /*
            We can find out the extra maximum number of flowers, count, that can be planted for the given flowerbed arrangement.
            To do so, we can traverse over all the elements of the flowerbed and find out those elements which are 0.
            For every such element, we check if its both adjacent positions are also empty.

            if the count obtained is greater than or equals to n, the required number of flowers to be planted, we can plant
            n flowers in the empty spaces, otherwise not.
        */
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0)
                    && (i == flowerbed.length - 1 || flowerbed[i+1] == 0)) {
                flowerbed[i++] = 1;
                count++;
            }

            if (count >= n) {
                return true;
            }

            i++;
        }

        return false;
    }
}
