package com.jetbrains.classic.advancedDataStructure.dsGraph;

import com.jetbrains.classic.advancedDataStructure.unionFind.NumberOfIsland2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NumberOfIslands {

    // assuming that char[][] grid is immutablle...
    /*
        time complexity: O(m * n)

        space complexity: dfs O(m * n)
                          bfs O(min(m, n))   // just draw a graph

     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        // breadth first search
        int count = 0;
        for (int i = 0; i< m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j, visited);  // dfs(grid, i, j, visited)
                }
            }
        }

        return count;
    }


    private void bfs (char[][] grid, int row, int col, boolean[][] visited) {
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});

        visited[row][col] = true;  // if grid can be modified, replace this statement with grid[row][col] = '0' // mark as visited

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int[] pair : pairs) {
                int x = pair[0] + node[0];
                int y = pair[1] + node[1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length &&
                        grid[x][y] == '1' && !visited[x][y]) {
                    queue.offer(new int[]{x, y});

                    visited[x][y] = true;
                }
            }
        }
    }

    /*
    Linear scan the 2d grid map, if a node contains a '1', then it is a root node that triggers a Depth First Search. During DFS,
    every visited node should be set as '0' to mark as visited node. Count the number of root nodes that trigger DFS,
    this number would be the number of islands since each DFS starting at some root identifies an island.
     */

    private void dfs(char[][] grid, int row, int col, boolean[][] visited) {
        // base case
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != '1' || visited[row][col]) {
            return;
        }

        // recursive case
        visited[row][col] = true;
        for (int[] pair : pairs) {
            int x = row + pair[0];
            int y = col + pair[1];

            dfs(grid, x, y, visited);
        }
    }

    private int[][] pairs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};


    // 3rd method: union find
    // time complexity is O(m * n)
    // space complexity is O(m * n)

    // Note that Union operation takes essentially constant time when UnionFind is implemented with both path compression and union by rank.
    /*
        what union by rank says that make the guy who has lower rank the child, and make the guy who has high rank the parent.
        the reason behind is that make teh depth of the tree as minimum as possible which will improve our time.
     */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        UnionFind uf = new UnionFind(m * n);

        // count how many islands it originally have
        // can add to the constructor of the unionfind
        int num = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') num++;
            }
        }
        uf.set_count(num);

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    for (int[] pair : pairs2) {
                        int x = pair[0] + i;
                        int y = pair[1] + j;

                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            uf.connect(i * n + j, x * n + y);
                        }
                    }
                }
            }
        }

        return uf.query();
    }

    private int[][] pairs2 = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private class UnionFind {
        public int[] father;
        public int count;
        public int[] rank;

        public UnionFind(int n) {
            rank = new int[n];
            father = new int[n];
            for (int i = 0; i < n; ++i) {
                father[i] = i;
            }
        }

        public void connect (int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                if (rank[root_a] > rank[root_b]) {
                    father[root_b] = root_a;
                } else if (rank[root_a] < rank[root_b]) {
                    father[root_a] = root_b;
                } else {
                    father[root_b] = root_a;
                    rank[root_a] += 1;
                }
                count--;
            }
        }

        public int find(int x) {
            if (x == father[x]) {
                return x;
            }

            return father[x] = find(father[x]);     // path compression
        }

        public int query() {
            return count;
        }

        public void set_count(int n) {
            count = n;
        }
    }

    // follow up: number of islands 2
    /*

     */
    private class UnionFind2{

        private int[] father;
        private int[] rank;
        private int count;

        public UnionFind2(int n) {
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


    public List<Integer> numIslands3(int m, int n, int[][] positions) {
        List<Integer> list = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0].length == 0) return list;

        int[][] matrix = new int[m][n];

        UnionFind2 uf = new UnionFind2(m * n);
        for (int[] position : positions) {
            int row = position[0];
            int col = position[1];

            if (matrix[row][col] != 1) {

                matrix[row][col] = 1;
                uf.set_count(uf.query() + 1);

                for (int[] pair : pairs) {
                    int x = pair[0] + row;
                    int y = pair[1] + col;

                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == 1) {
                        uf.connect(row * n + col, x * n + y);
                    }
                }
            }

            list.add(uf.query());
        }

        return list;
    }
}