package com.jetbrains.classic.ideologyAlgo.greedy;

public class GasStation {
    /*
        straight forward brute force

        time: O(n^2)
        space: O(1)
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        for (int i = 0; i < n; ++i) {
            if (canTravel(i, gas, cost)) {
                return i;
            }
        }

        return -1;
    }

    private boolean canTravel(int index, int[] gas, int[] cost) {
        int step = 0, tankLeft = gas[index];
        while (step < gas.length) {
            if (tankLeft < cost[index]) {
                return false;
            }
            tankLeft -= cost[index];
            index = (index + 1) % gas.length;
            tankLeft += gas[index];

            step++;
        }

        return true;
    }

    /*
        one pass

        time: O(n)
        space: O(1)
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int total_tank = 0, curr_tank = 0, index = 0;
        for (int i = 0; i < cost.length; i++) {
            // Greedily move to next
            int cur = gas[i] - cost[i];
            // update tank
            curr_tank += cur;
            // if last move consume more than available gas in curr_tank - this index cannot be answer
            // accumulate the negative cost from here, and start from index next to it
            if (curr_tank < 0) {//if sum < 0, index can only start from i + 1
                /*
                    explanation about index = i + 1 instead of index = index + 1;
                    gas = [1,2,3,4,5], cost = [3,4,5,1,2]

                    1,      2,      3,      4,      5
                    3,      4,      5,      1,      2

                    we just assume that we iterate teh array from 0 index, and at index 3, curr_tank is less than 0.
                    that means that total_cost[0...3] > total_input[0...3]
                    =>. we can conclude that total_cost[1...3] > total_input[1..3] since total_cost[0] < total_input[0]
                    =>. we can conclude that total_cost[2...3] > total_input[2..3] since total_cost[1] < total_input[1]
                    ...
                    so if curr_tank < 0, we need to explore from i + 1 index

                */

                index = i + 1;
                curr_tank = 0;
            }
            total_tank += cur;
        }
        return total_tank < 0 ? -1 : index;
    }
}
