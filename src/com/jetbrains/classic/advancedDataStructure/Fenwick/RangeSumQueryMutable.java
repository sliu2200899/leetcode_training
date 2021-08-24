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
    int size;

    public void NumArray(int[] nums) {
        this.size = nums.length;
        this.BIT = new int[size + 1];
        this.nums = nums;

        for (int i = 0; i < size; ++i) {
            updateTree(i, nums[i]);
        }
    }

    /*
        update one value:
            i += lowbit(i),  i <= n   lowbit(x) = x & (-x)

            input: [1,2,3,4,5,6,7,8]     n = 8
                                                                                36
                                   10                    11            7
                    3       3                     5
            1
           0001   0010    0011    0100          0101    0110          0111     1000
     */
    public void updateTree(int i, int val) {
        i++;
        while (i <= size) {
            BIT[i] += val;
            i += (i & (-i));  // parent_index = cur_index + lowbit(cur_index)   <=  lowbit(i) = i & (-i)
        }
    }

    public void update(int i, int val) {
        updateTree(i, val - nums[i]);
        nums[i] = val;
    }

    /*
        query one value:
            i -= lowbit(i), i > 0     lowbit(x) = x & (-x)

            1     3                10                                           36
                           3                     5       11
                                                                        7
           0001   0010    0011    0100          0101    0110          0111     1000
     */
    private int queryTree(int i) {
        int sum = 0;
        i++;
        while(i > 0) {
            sum += BIT[i];
            i -= (i & (-i));   // another tree, go to the ancestor
        }
        return sum;
    }

    public int sumRange(int left, int right) {
        if (left == 0) return queryTree(right);
        return queryTree(right) - queryTree(left - 1);
    }
}
