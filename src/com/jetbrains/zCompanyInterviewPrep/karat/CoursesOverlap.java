package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.*;

public class CoursesOverlap {
    /*
        You are a developer for a university. Your current project is to develop a system for students to find courses they
        share with friends. The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs.

        Write a function that takes in a list of (student ID number, course name) pairs and returns, for every pair of students, a list of all courses they share.

        Sample Input:

        student_course_pairs_1 = [
          ["58", "Software Design"],
          ["58", "Linear Algebra"],
          ["94", "Art History"],
          ["94", "Operating Systems"],
          ["17", "Software Design"],
          ["58", "Mechanics"],
          ["58", "Economics"],
          ["17", "Linear Algebra"],
          ["17", "Political Science"],
          ["94", "Economics"],
          ["25", "Economics"],
        ]

        Sample Output (pseudocode, in any order):

        find_pairs(student_course_pairs_1) =>
        {
          [58, 17]: ["Software Design", "Linear Algebra"]
          [58, 94]: ["Economics"]
          [58, 25]: ["Economics"]
          [94, 25]: ["Economics"]
          [17, 94]: []
          [17, 25]: []
        }

        Additional test cases:

        Sample Input:

        student_course_pairs_2 = [
          ["42", "Software Design"],
          ["0", "Advanced Mechanics"],
          ["9", "Art History"],
        ]

        Sample output:

        find_pairs(student_course_pairs_2) =>
        {
          [0, 42]: []
          [0, 9]: []
          [9, 42]: []
        }

     */
    public static List<String> courseOverlaps(List<List<String>> studentCoursePairs) {
        List<String> res = new ArrayList<>();

        Map<String, List<String>> idAllCoursePairs = new HashMap<>();
        for (List<String> pair : studentCoursePairs) {

            String id = pair.get(0);
            String course = pair.get(1);
            idAllCoursePairs.putIfAbsent(id, new ArrayList<>());
            idAllCoursePairs.get(id).add(course);
        }

        //
        List<String> list = new ArrayList<>(idAllCoursePairs.keySet());

        for (int i = 0; i < list.size(); ++i) {
            for (int j = i + 1; j < list.size(); ++j) {
                String id1 = list.get(i);
                String id2 = list.get(j);

                List<String> overlaps = new ArrayList<>();
                for (String id1_course : idAllCoursePairs.get(id1)) {
                    for (String id2_course : idAllCoursePairs.get(id2)) {
                        if (id1_course.equals(id2_course)) {
                            overlaps.add(id1_course);
                        }
                    }
                }

                res.add("[" + id1 + ", " + id2 + "]: " + overlaps);
            }
        }
        return res;
    }
    public static void main(String[] args){
        List<List<String>> student_course_pairs = new ArrayList<>();
        student_course_pairs.add(Arrays.asList("58", "Software Design"));
        student_course_pairs.add(Arrays.asList("58", "Linear Algebra"));
        student_course_pairs.add(Arrays.asList("94", "Art History"));
        student_course_pairs.add(Arrays.asList("94", "Operating Systems"));
        student_course_pairs.add(Arrays.asList("17", "Software Design"));
        student_course_pairs.add(Arrays.asList("58", "Mechanics"));
        student_course_pairs.add(Arrays.asList("58", "Economics"));
        student_course_pairs.add(Arrays.asList("17", "Linear Algebra"));
        student_course_pairs.add(Arrays.asList("17", "Political Science"));
        student_course_pairs.add(Arrays.asList("94", "Economics"));
        student_course_pairs.add(Arrays.asList("25", "Economics"));
        System.out.println(courseOverlaps(student_course_pairs));

    }
}
