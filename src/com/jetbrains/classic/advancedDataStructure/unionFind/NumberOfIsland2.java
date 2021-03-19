package com.jetbrains.classic.advancedDataStructure.unionFind;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIsland2 {
    /*
    have UnionFind class
*/
    private class UnionFind{

        private int[] father;
        private int[] rank;
        private int count;

        public UnionFind(int n) {
            rank = new int[n];
            father = new int[n];
            for (int i = 0; i < n; ++i) {
                father[i] = i;
            }
        }

        private void connect(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);

            if (root_a != root_b) {
                if (rank[root_a] < rank[root_b]) {
                    father[root_a] = root_b;
                } else if (rank[root_a] > rank[root_b]) {
                    father[root_b] = root_a;
                } else {
                    father[root_b] = root_a;
                    rank[root_a]++;
                }

                count--;
            }
        }

        private int find(int a) {
            if (a == father[a]) {
                return a;
            }
            return father[a] = find(father[a]);
        }

        private int query() {
            return count;
        }

        private void set_count(int n) {
            this.count = n;
        }
    }


    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> list = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0].length == 0)  return list;

        int[][] matrix = new int[m][n];

        UnionFind uf = new UnionFind(m*n);
        for (int[] position : positions) {
            int row = position[0];
            int col = position[1];

            if (matrix[row][col] != 1) {

                matrix[row][col] = 1;
                uf.set_count(uf.query()+1);

                for (int[] pair : pairs) {
                    int x = pair[0] + row;
                    int y = pair[1] + col;

                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == 1) {
                        uf.connect(row*n + col, x*n + y);
                    }
                }
            }

            list.add(uf.query());
        }

        return list;
    }

    private int[][] pairs = new int[][]{{-1,0}, {1,0},{0,1},{0,-1}};
}
