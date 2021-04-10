package com.jetbrains.zCompanyInterviewPrep.amazon;

import javafx.scene.control.Cell;

import java.util.*;

public class CardPlayer {


    private int[] playCardTwoPlayerNoDuplicate(int[][] matrix) {

        int[] ans = new int[matrix.length];
        for (int i = 0; i < matrix[0].length; ++i) {
            if (matrix[0][i] < matrix[1][i]) {
                ans[1] += 2;
            } else if (matrix[0][i] > matrix[1][i]) {
                ans[0] += 2;
            }
        }

        return ans;
    }

    int[] index;
    private int[] playCardMultiplePlayerDuplicate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int[] ans = new int[matrix.length];
        index = new int[matrix.length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; ++i) {
            list.add(i);
        }
        Map<Integer, Integer> map = countOfScore(matrix, list);
        for (int key : map.keySet()) {
            ans[key] = map.get(key);
        }
        return ans;
    }

    private Map<Integer, Integer> countOfScore(int[][] matrix, List<Integer> list) {
        Map<Integer, Integer> ans = new HashMap<>();
        PriorityQueue<int[]> pqmax = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));

        for (int i = 0; i < list.size(); ++i) {
            if (index[list.get(i)] < matrix[0].length) {
                pqmax.offer(new int[]{list.get(i), index[list.get(i)], matrix[list.get(i)][index[list.get(i)]]});
                index[list.get(i)]++;
            }
        }

        while (!pqmax.isEmpty()) {

            int cards = pqmax.size();

            int[] max = pqmax.poll();
            List<Integer> temp = new ArrayList<>();
            temp.add(max[0]);
            while (!pqmax.isEmpty() && pqmax.peek()[2] == max[2]) {
                int[] cur = pqmax.poll();
                temp.add(cur[0]);
            }

            while (!pqmax.isEmpty()) {
                pqmax.poll();
            }

            if (temp.size() == 1) {
                ans.put(max[0], ans.getOrDefault(max[0], 0) + cards);
            } else {
                Map<Integer, Integer> tmp = countOfScore(matrix, temp);
                for (Map.Entry<Integer, Integer> entry : tmp.entrySet()) {
                    ans.put(entry.getKey(),
                            ans.getOrDefault(entry.getKey(), 0)
                                    + entry.getValue() + cards
                    );
                }
            }
            for (int i = 0; i < list.size(); ++i) {
                if (index[list.get(i)] < matrix[0].length) {
                    pqmax.offer(new int[]{list.get(i), index[list.get(i)], matrix[list.get(i)][index[list.get(i)]]});
                    index[list.get(i)]++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        /*
            a:  1 3 4
            b:  2 3 2
            c:  0 1 9
         */
        int[][] matrix = {{1,3,4,2},{2,3,4,1},{0,1,9,3}};

        CardPlayer s = new CardPlayer();
        int[] out = s.playCardMultiplePlayerDuplicate(matrix);
        for (int i : out) {
            System.out.println(i);
        }
    }
}
