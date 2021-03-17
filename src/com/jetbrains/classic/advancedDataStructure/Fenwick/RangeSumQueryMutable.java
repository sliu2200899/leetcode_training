package com.jetbrains.classic.advancedDataStructure.Fenwick;

public class RangeSumQueryMutable {
    /*
        get to know what is fenwick tree
            refer to: https://cs.stackexchange.com/questions/10538/bit-what-is-the-intuition-behind-a-binary-indexed-tree-and-how-was-it-thought-a
            refer to: https://www.youtube.com/watch?v=WbafSgetDDk&t=1033s&ab_channel=HuaHua
            refer to: https://stackoverflow.com/questions/64190332/fenwick-tree-vs-segment-tree

        time complexity:  update  O(logN)
        \                 query  O(logN)
                          preprocessing  O(nlogn)
     */
    int[] nums;
    int[] BIT;
    int n;

    public void NumArray(int[] nums) {
        this.nums = nums;

        n = nums.length;

        BIT = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            init(i, nums[i]);
        }
    }

    /*
        update one value:
     */
    public void init(int i, int val) {
        i++;
        while (i <= n) {
            BIT[i] += val;
            i += (i & -i);
        }
    }

    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        init(i, diff);
    }

    public int sumRange(int left, int right) {
        return getSum(right) - getSum(left - 1);
    }

    /*
        query one value:
     */
    private int getSum(int i) {
        int sum = 0;
        i++;
        while(i > 0) {
            sum += BIT[i];
            i -= (i & -i);
        }
        return sum;
    }
}
