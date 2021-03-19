package com.jetbrains.classic.advancedDataStructure.dsGraph;

public class NumberOfProvinces {
    /*
        use DFS to solve this connected components in undirected graph with adjacency matrix

        time: O(n^2)  the complete matrix of size n^2 is traversed
        space: O(n)  visited array of size n is used
     */
    public int findCircleNum1(int[][] M) {
        boolean[] visited = new boolean[M.length]; //visited[i] means if ith person is visited in this algorithm
        int count = 0;
        for(int i = 0; i < M.length; i++) { // explore the matrix row by row
            if(!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
    private void dfs(int[][] M, boolean[] visited, int person) {
        for(int other = 0; other < M.length; other++) {     // explore the current person's friend col by col
            if(M[person][other] == 1 && !visited[other]) {
                //We found an unvisited person in the current friend cycle
                visited[other] = true;
                dfs(M, visited, other); //Start DFS on this new found person
            }
        }
    }

    /*
        union find
     */
    private class UnionFind{
        private int[] rank;
        private int[] father;
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
                }
                else if (rank[root_a] > rank[root_b]) {
                    father[root_b] = root_a;
                }
                else {
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

    public int findCircleNum2(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0 || isConnected[0].length == 0) return 0;

        int m = isConnected.length, n = isConnected[0].length;

        UnionFind uf = new UnionFind(m);

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (isConnected[i][j] == 1) {
                    uf.connect(i, j);
                }
            }
        }

        return uf.query();
    }
}
