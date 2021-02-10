package com.jetbrains.classic.dataStructure.dsGraph;

import java.util.LinkedList;

public class Dijkstra {
    /*
        single source shortest path algorithm

        time: O(ElogV)

        每个顶点可以连接到(V-1)个顶点，因此每个顶点的相邻边数为V-1。假设E表示连接到每个顶点的V-1边。
        在最小堆中查找和更新每个相邻顶点的权重为O(log(V))+ O(1)或O(log(V))。
        因此，从上面的步骤1和步骤2开始，更新顶点的所有相邻顶点的时间复杂度为E *(logV)。 或E*logV。
        因此，所有V个顶点的时间复杂度为V *(E * logV)，即O(VElogV)。 note, 这里的V是顶点数，E是连接到单个节点的最大边数。

        让我们将E重命名为N。因此，一种分析说O(ElogV)，而另一种说O(VNlogV)。两者都是正确的，实际上是E = O(VN)。区别在于ElogV是更严格的估计。
     */
    private LinkedList<Edge> adj[]; // 邻接表
    private int v; // 顶点个数

    public Dijkstra(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int w) { // 添加一条边
        this.adj[s].add(new Edge(s, t, w));
    }

    private class Edge {
        public int sid; // 边的起始顶点编号
        public int tid; // 边的终止顶点编号
        public int w; // 权重
        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }
    // 下面这个类是为了dijkstra实现用的
    private class Vertex {
        public int id; // 顶点编号ID
        public int dist; // 从起始顶点到这个顶点的距离
        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }


    // 因为Java提供的优先级队列，没有暴露更新数据的接口，所以我们需要重新实现一个
    private class PriorityQueue { // 根据vertex.dist构建小顶堆
        private Vertex[] nodes;
        private int count;
        public PriorityQueue(int v) {
            this.nodes = new Vertex[v+1];
            this.count = v;
        }
        public Vertex poll() { // TODO: 留给读者实现...
            return null;
        }
        public void add(Vertex vertex) { // TODO: 留给读者实现...
        }
        // 更新结点的值，并且从下往上堆化，重新符合堆的定义。时间复杂度O(logn)。
        public void update(Vertex vertex) { // TODO: 留给读者实现...
        }
        public boolean isEmpty() { // TODO: 留给读者实现...
            return false;
        }
    }

    public void dijkstra(int s, int t) { // 从顶点s到顶点t的最短路径
        int[] predecessor = new int[this.v]; // 用来还原最短路径
        Vertex[] vertexes = new Vertex[this.v];
        for (int i = 0; i < this.v; ++i) {
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        PriorityQueue queue = new PriorityQueue(this.v);// 小顶堆
        boolean[] inqueue = new boolean[this.v]; // 标记是否进入过队列
        vertexes[s].dist = 0;
        queue.add(vertexes[s]);
        inqueue[s] = true;  // 用于后面更改queue中元素的值
        while (!queue.isEmpty()) {
            Vertex minVertex= queue.poll(); // 取堆顶元素并删除
            if (minVertex.id == t) break; // 最短路径产生了
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                Edge e = adj[minVertex.id].get(i); // 取出一条minVetex相连的边
                Vertex nextVertex = vertexes[e.tid]; // minVertex-->nextVertex
                if (minVertex.dist + e.w < nextVertex.dist) { // 更新next的dist
                    nextVertex.dist = minVertex.dist + e.w;
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inqueue[nextVertex.id] == true) {
                        queue.update(nextVertex); // 更新队列中的dist值
                    } else {
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }
            }
        }
        // 输出最短路径
        System.out.print(s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.print("->" + t);
    }
}
