package com.jetbrains.classic.ideologyAlgo.greedy;

public class JumpGame {
    /*
        approach 1: backtracking


     */
    public boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }

        return false;
    }

    public boolean canJump(int[] nums) {
        return canJumpFromPosition(0, nums);
    }

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
    public boolean canJump1(int[] nums) {
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
        dynamic programming: bottom-up approach

        Top-down to bottom-up conversion is done by eliminating recursion. In practice, this achieves better performance as we no longer have
        the method stack overhead and might even benefit from some caching. More importantly, this step opens up possibilities for future optimization.
        The recursion is usually eliminated by trying to reverse the order of the steps from the top-down approach.


        The observation to make here is that we only ever jump to the right. This means that if we start from the right of the array, every time we will query a position to our right,
        that position has already be determined as being GOOD or BAD. This means we don't need to recurse anymore, as we will always hit the memo table.
     */
    public boolean canJump2(int[] nums) {
        boolean[] memo = new boolean[nums.length];

        memo[nums.length - 1] = true;

        for (int i = nums.length - 2; i >= 0; --i) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);

            for (int j = i + 1; j <= furthestJump; ++j) {
                if (memo[j] == true) {
                    memo[i] = true;
                    break;
                }
            }
        }

        return memo[0];
    }


    /*
        greedy:  update the leftmost good index

        algo:
            Iterating right-to-left, for each position we check if there is a potential jump that reaches a GOOD index
            (currPosition + nums[currPosition] >= leftmostGoodIndex). If we can reach a GOOD index, then our position is itself GOOD.
            Also, this new GOOD position will be the new leftmost GOOD index.

            Iteration continues until the beginning of the array. If first position is a GOOD index
            then we can reach the last index from the first position.

        time: O(n)
        space: O(1)
     */
    public boolean canJump3(int[] nums) {
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

    The main idea is based on greedy.
    Let's say the range of the current jump is [curBegin, curEnd], curFarthest is the farthest point that all points in [curBegin, curEnd] can reach.
    Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, then keep the above steps, as the following:

     */
    public int jump(int[] nums) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
}
