package com.jetbrains.classic.advancedDataStructure.dsGraph;

import java.util.*;

public class CourseSchedule {

    /*
        topological sort(Many real world situations can be modelled as a graph with directed edges where some events must occur before others)
        the diff between topological sort and undirected graph is that
        you need to have an auxiliary array to record the number of the prerequisites
        add to the queue if and only if the course has no prerequisites

        the basic idea is that put NODE with 0 indgree into the queue, then make indegree of NODE's successor dereasing 1.
        Keep the above steps with BFS. Finally, if each node' indgree equals 0, then it is validated DAG (Directed Acyclic Graph),
        which means the course schedule can be finished.
     */
    /*
    To better understand the above algorithm, we summarize a few points here:

    In order to find a global order, we can start from those nodes which do not have any prerequisites (i.e. indegree of node is zero), we then incrementally add new nodes to the global order, following the dependencies (edges).
    Once we follow an edge, we then remove it from the graph.
    With the removal of edges, there would more nodes appearing without any prerequisite dependency, in addition to the initial list in the first step.
    The algorithm would terminate when we can no longer remove edges from the graph. There are two possible outcomes:
        1). If there are still some edges left in the graph, then these edges must have formed certain cycles, which is similar to the deadlock situation. It is due to these cyclic dependencies that we cannot remove them during the above processes.
        2). Otherwise, i.e. we have removed all the edges from the graph, and we got ourselves a topological order of the graph.
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // convert prerequisites to the adjacency list
        int[] arr = new int[numCourses];   // to record the number of the prerequ...
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            adj.add(new ArrayList<>());
        }

        for (int[] pair : prerequisites) {
            adj.get(pair[1]).add(pair[0]);
            arr[pair[0]]++;
        }

        // to find a path based on the adjacency list
        // bfs to search for. a path
        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numCourses; ++i) {
            if (arr[i] == 0) {
                queue.offer(i);
                set.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int val = queue.poll();
            for (int neighbor : adj.get(val)) {
                arr[neighbor]--;
                if (arr[neighbor] == 0) {
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }
        return set.size() == numCourses;
    }

    /*
        follow up, what if I want to get the courses in topological order?
     */
    /*
        int[] order = new int[numCourses];
        ...

        order[i++] = course;
        ...

        if (i == numCourses) return order;
        return new int[0];
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] arr = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            adj.add(new ArrayList<>());
        }

        for (int[] pair : prerequisites) {
            adj.get(pair[1]).add(pair[0]);
            arr[pair[0]]++;
        }

        List<Integer> list = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < numCourses; ++i) {
            if (arr[i] == 0) {
                queue.offer(i);
                set.add(i);
                list.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int val = queue.poll();
            for (int neighbor : adj.get(val)) {
                arr[neighbor]--;
                if (arr[neighbor] == 0) {
                    queue.offer(neighbor);
                    set.add(neighbor);
                    list.add(neighbor);
                }
            }
        }

        if (set.size() != numCourses) {
            return new int[0];
        }

        return list.stream().mapToInt(x -> x).toArray();
    }
}
