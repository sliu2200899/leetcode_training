package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.*;

public class CoursesMidWay {
    /*
        Students may decide to take different "tracks" or sequences of courses in the Computer Science curriculum.
        There may be more than one track that includes the same course, but each student follows a single linear track from a "root" node to a "leaf" node.
        In the graph below, their path always moves left to right.

        Write a function that takes a list of (source, destination) pairs, and returns the name of all of the courses that the students could be taking when they are halfway through their track of courses.


        Sample input:
        all_courses = [
            ["Logic", "COBOL"],
            ["Data Structures", "Algorithms"],
            ["Creative Writing", "Data Structures"],
            ["Algorithms", "COBOL"],
            ["Intro to Computer Science", "Data Structures"],
            ["Logic", "Compilers"],
            ["Data Structures", "Logic"],
            ["Creative Writing", "System Administration"],
            ["Databases", "System Administration"],
            ["Creative Writing", "Databases"],
            ["Intro to Computer Science", "Graphics"],
        ]

        Sample output (in any order):
                  ["Data Structures", "Creative Writing", "Databases", "Intro to Computer Science"]

        All paths through the curriculum (midpoint *highlighted*):

        *Intro to C.S.* -> Graphics
        Intro to C.S. -> *Data Structures* -> Algorithms -> COBOL
        Intro to C.S. -> *Data Structures* -> Logic -> COBOL
        Intro to C.S. -> *Data Structures* -> Logic -> Compiler
        Creative Writing -> *Databases* -> System Administration
        *Creative Writing* -> System Administration
        Creative Writing -> *Data Structures* -> Algorithms -> COBOL
        Creative Writing -> *Data Structures* -> Logic -> COBOL
        Creative Writing -> *Data Structures* -> Logic -> Compilers

        Visual representation:

                            ____________
                            |          |
                            | Graphics |
                       ---->|__________|
                       |                          ______________
        ____________   |                          |            |
        |          |   |    ______________     -->| Algorithms |--\     _____________
        | Intro to |   |    |            |    /   |____________|   \    |           |
        | C.S.     |---+    | Data       |   /                      >-->| COBOL     |
        |__________|    \   | Structures |--+     ______________   /    |___________|
                         >->|____________|   \    |            |  /
        ____________    /                     \-->| Logic      |-+      _____________
        |          |   /    ______________        |____________|  \     |           |
        | Creative |  /     |            |                         \--->| Compilers |
        | Writing  |-+----->| Databases  |                              |___________|
        |__________|  \     |____________|-\     _________________________
                       \                    \    |                       |
                        \--------------------+-->| System Administration |
                                                 |_______________________|

        Complexity analysis variables:

        n: number of pairs in the input

        two more questions:
            1. can we assume that each course has only one next course
            2. what do you mean by the track? does that mean that we need to take all of the prequitisite courses before this course?
            3. can we assunm that this is a DAG?
    */
    // basic version: each course has only one next course [pre, course]
    public String findMedianCourseI(String[][] courses) {
        // adj
        Map<String, String> adj = new HashMap<>();
        // ind
        Map<String, Integer> ind = new HashMap<>();

        for (String[] course : courses) {
            String prev = course[0], cur = course[1];
            adj.put(prev, cur);
            ind.put(prev, ind.getOrDefault(prev, 0));
            ind.put(cur, ind.getOrDefault(cur, 0) + 1);
        }

        List<String> path = new ArrayList<>();
        for (String course : ind.keySet()) {
            if (ind.get(course) == 0) {

                while (course != null) {
                    path.add(course);
                    course = adj.get(course);
                }
            }
        }

        return path.get((path.size() - 1) / 2);
    }


    public List<String> findMedianCourseII(String[][] allCourses) {
        // adj
        Map<String, List<String>> adj = new HashMap<>();   // all of the courses and its next course.
        // ind
        Map<String, Integer> ind = new HashMap<>();  // all of the courses and its corresponding indegree number

        for (String[] pair : allCourses) {
            adj.putIfAbsent(pair[0], new ArrayList<>());
            adj.get(pair[0]).add(pair[1]);

            ind.put(pair[1], ind.getOrDefault(pair[1], 0) + 1);
            ind.put(pair[0], ind.getOrDefault(pair[0], 0));
        }

        Set<String> res = new HashSet<>();
        for (String key : ind.keySet()) {
            if (ind.get(key) == 0) {
                dfs(adj, key, new ArrayList<>(), res);
            }
        }

        return new ArrayList<>(res);
    }


    private void dfs(Map<String, List<String>> adj, String cur, List<String> path, Set<String> res) {
        if (!adj.containsKey(cur)) {
            // reach to the leaf node
            path.add(cur);
            res.add(path.get((path.size() - 1) / 2));
            path.remove(path.size() - 1);
            return;
        }

        path.add(cur);

        for (String next : adj.get(cur)) {
            dfs(adj, next, path, res);
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args){
        CoursesMidWay solution = new CoursesMidWay();

        System.out.println("Question 1: ");

        String[][] singleCourseList = new String[][]{
                {"Course_1", "Course_2"},
                // {"Course_3", "Course_4"},
                {"Course_2", "Course_3"}
        };

        System.out.println(solution.findMedianCourseI(singleCourseList));

        System.out.println("Question 2: ");
        String[][] courseList = new String[][]{
                {"Course_3", "Course_7"},
                {"Course_1", "Course_2"},
                {"Course_2", "Course_3"},
                {"Course_3", "Course_4"},
                {"Course_4", "Course_5"},
                {"Course_5", "Course_6"},
                // {"Course_6", "Course_7"}
        };
        String[][] courseList2 = new String[][] {
                {"Logic", "COBOL"},
                {"Data Structures", "Algorithms"},
                {"Creative Writing", "Data Structures"},
                {"Algorithms", "COBOL"},
                {"Intro to Computer Science", "Data Structures"},
                {"Logic", "Compilers"},
                {"Data Structures", "Logic"},
                {"Graphics", "Networking"},
                {"Networking", "Algorithms"},
                {"Creative Writing", "System Administration"},
                {"Databases", "System Administration"},
                {"Creative Writing", "Databases"},
                {"Intro to Computer Science", "Graphics"}
        };
        System.out.println(solution.findMedianCourseII(courseList));
        System.out.println(solution.findMedianCourseII(courseList2));
    }
}
