package com.jetbrains.classic.ideologyAlgo.greedy;

public class JumpGame {
    /*
        dfs + memo

     */
        /*
        clarify:
            1. input, output, example

        algo:
            nums = [2,3,1,1,4]
                    i

                    recursion to solve the problem + memo
                    canJump(0) = canJump(1) || canJump(2)

        time: O(n^2)
            for every element in the array, say i, we are looking at teh next nums[i] elements to its right. nums[i] can be at most n.

        space: O(n)
    */
    public boolean canJump(int[] nums) {
        return canJump(nums, 0, new Boolean[nums.length]);
    }
    private boolean canJump(int[] nums, int index, Boolean[] memo) {
        if (index == nums.length - 1) {
            return true;
        }

        Boolean ans = memo[index];
        if (ans != null) {
            return ans;
        }

        for (int i = 1; i <= nums[index]; ++i) {
            if (canJump(nums, index + i, memo)) {
                memo[index] = true;
                return true;
            }
        }

        memo[index] = false;
        return false;
    }

    /*
        greedy
        time: O()
        space: O()
     */
    public boolean canJump2(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    /*
        jump game 2
        greedy

        the key is first jump to the currentJumpEnd
        then check the farthest next jump reach from the [i, currentJumpEnd)
        if i == currentJumpEnd, we need to be forced to jump again.

        [2,               3,               1,              1,            4]
                         i
                                    currentJumpEnd
                                       farthest

     */
    public int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            // we continuously find how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            if(i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        return jumps;
    }
}
