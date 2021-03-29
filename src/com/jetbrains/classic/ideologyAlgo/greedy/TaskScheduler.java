package com.jetbrains.classic.ideologyAlgo.greedy;

import java.util.*;

public class TaskScheduler {
    /*
        greedy:  map + priorityqueue

        The idea used here is similar to - https://leetcode.com/problems/rearrange-string-k-distance-apart
        We need to arrange the characters in string such that each same character is K distance apart, where distance in this problems is time b/w two similar task execution.

        Idea is to add them to a priority Q and sort based on the highest frequency.
        And pick the task in each round of 'n' with highest frequency. As you pick the task, decrease the frequency, and put them back after the round.


     */
    private class Cell{
        char c;
        int freq;
        public Cell(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c: tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Cell> pqmax = new PriorityQueue<>((a, b) -> {
            if (a.freq == b.freq) {
                return a.c - b.c;
            }
            return Integer.compare(b.freq, a.freq);
        });

        for (Character c : map.keySet()) {
            pqmax.offer(new Cell(c, map.get(c)));
        }

        int num = 0;

        while (!pqmax.isEmpty()) {
            List<Cell> list = new ArrayList<>();
            int i = 0;
            while (!pqmax.isEmpty() && i <= n) {
                Cell c = pqmax.poll();
                c.freq--;
                if (c.freq != 0) {
                    list.add(c);
                }
                ++i;
            }

            if (pqmax.isEmpty() && list.size() == 0) {
                num += i;
                break;
            }

            for (Cell c: list) {
                pqmax.offer(c);
            }

            num += (n+1);
        }
        return num;
    }

    /*
        greedy: math
        There is one crucial point for you:
        the ONLY thing you need to care is the max number of one task!
        We set apart each max task with interval n, and we hope to put all other tasks into those intervals.
        If the number of those tasks exceeds the interval space, then we don't need any idle interval at all.
        If not, the interval space plus the max tasks will be the least interval. Be care for the existent of multiple max tasks.


         there is a special case:  # tasks > ans
         that means we can schedule most frequent tasks without idling
         => we can schedule all the tasks without idling
         => ans = # of tasks

     */
    public int leastInterval2(char[] tasks, int n) {
        int[] freq = new int[26];
        for (int t : tasks) {
            freq[t - 'A']++;
        }

        // max frequency
        int f_max = 0;
        for (int f : freq) {
            f_max = Math.max(f_max, f);
        }

        // count teh most freq tasks
        int n_max = 0;
        for (int f : freq) {
            if (f == f_max) {
                n_max++;
            }
        }

        int ans = (f_max - 1) * (n + 1) + n_max;  // least interval that we can schedule all of the most freq tasks

        return Math.max(tasks.length, (f_max-1) * (n+1) + n_max);
    }

}
