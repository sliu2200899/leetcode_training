package com.jetbrains.classic.topic.randomAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShuffleArray {
    /*
        design an algorithm to randomly shuffle the array, i.e.
        the probability of each permutation of given array is even likely.
     */
    /*
        method 1: brute force
        If we put each number in a "hat" and draw them out at random, the order in which we draw them will define a random ordering.

        algorithm
        The brute force algorithm essentially puts each number in the aforementioned "hat", and draws them at random (without replacement)
        until there are none left. Mechanically, this is performed by copying the contents of array into a second auxiliary array named aux
        before overwriting each element of array with a randomly selected one from aux. After selecting each random element, it is removed
        from aux to prevent duplicate draws. The implementation of reset is simple, as we just store the original state of nums on construction.

        time:
            O(n ^ 2) quadratic time complexity arises from the calls to list.remove (or list.pop), which run in linear time. n
            linear list removals occur, which results in a fairly easy quadratic analysis.
        space:
            O(n) Because the problem also asks us to implement reset, we must use linear additional space to store the original array.
            Otherwise, it would be lost upon the first call to shuffle

     */
    private int[] array;
    private int[] original;

    Random rand = new Random();

    public void Solution(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = original;
        original = original.clone();
        return array;
    }

    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<>();
        for (int i = 0; i < array.length; ++i) {
            asList.add(array[i]);
        }
        return asList;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();

        for (int i = 0; i < array.length; ++i) {
            int removeIdx = rand.nextInt(aux.size());
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }

        return array;
    }

    /*
        method 2: Fisher-Yates algorithm

        intuition:
            We can cut down the time and space complexities of shuffle with a bit of cleverness - namely,
            by swapping elements around within the array itself, we can avoid the linear space cost of the auxiliary array
            and the linear time cost of list modification.

        algorithm:
            The Fisher-Yates algorithm is remarkably similar to the brute force solution. On each iteration of the algorithm,
            we generate a random integer between the current index and the last index of the array. Then, we swap the elements
            at the current index and the chosen index - this simulates drawing (and removing) the element from the hat, as the
            next range from which we select a random index will not include the most recently processed one.
            One small, yet important detail is that it is possible to swap an element with itself - otherwise,
            some array permutations would be more likely than others. To see this illustrated more clearly, consider the animation below:
     */

    // Solution, reset method is the same as above...
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle2() {

        for (int i = 0; i < array.length; ++i) {
            int index = rand.nextInt(array.length - i) + i;
            swap(array, i, index);
        }

        return array;
    }
}
