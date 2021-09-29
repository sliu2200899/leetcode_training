package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.*;

public class BadgeAccess {
    /**
     Question 1:

     We are working on a security system for a badged-access room in our company's building.
     1. Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:
     a. All employees who didn't use their badge while exiting the room – they recorded an enter without a matching exit.
     b. All employees who didn't use their badge while entering the room – they recorded an exit without a matching enter.

     Sample input:
     badge_records = [
     ["Martha",   "exit"],
     ["Paul",     "enter"],
     ["Martha",   "enter"],
     ["Martha",   "exit"],
     ["Jennifer", "enter"],
     ["Paul",     "enter"],
     ["Curtis",   "enter"],
     ["Paul",     "exit"],
     ["Martha",   "enter"],
     ["Martha",   "exit"],
     ["Jennifer", "exit"]
     ]

     Expected output: ["Paul", "Curtis"], ["Martha"]
     **/

    // Map<String, Boolean> :   [name, state]

    // (()   -> valid one or not... (  )
    // variable state of the valid expression...
    // exit -1
    // enter +1
    // variable < 0 || variable > 1    ->  keep

    public List<List<String>> invalidBadgeRecords(List<List<String>> records) {
        Set<String> missEntry = new HashSet<>();
        Set<String> missExit = new HashSet<>();

        List<List<String>> res = new ArrayList<>();
        if (records == null || records.size() == 0) {
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        for (List<String> record : records) {
            String name = record.get(0);
            String state = record.get(1);

            map.put(name, map.getOrDefault(name, 0) + (state.equals("exit") ? -1 : 1));

            if (map.get(name) < 0) {
                missEntry.add(name);
            } else if (map.get(name) > 1) {
                missExit.add(name);
            }
        }

        // ()(
        for (String name : map.keySet()) {
            if (map.get(name) == 1) {
                missExit.add(name);
            }
        }

        return Arrays.asList(new ArrayList<>(missExit), new ArrayList<>(missEntry));
    }









//    public List<List<String>> invalidBadgeRecords(List<List<String>> records) {
//        Set<String> missExit = new HashSet<>();
//        Set<String> missEntry = new HashSet<>();
//
//        List<List<String>> res = new ArrayList<>();
//        for (int i = 0; i < 2; ++i) {
//            res.add(new ArrayList<>());
//        }
//
//        if (records == null || records.size() == 0) {
//            return res;
//        }
//
//        // check if this is a valid parenthesis... ()
//        Map<String, Integer> map = new HashMap<>();   // exit: -1, enter: 1
//        for (List<String> record : records) {
//            String name = record.get(0);
//            String state = record.get(1);
//
//            map.put(name, map.getOrDefault(name, 0) + (state.equals("exit") ? -1 : 1));
//
//            if (map.get(name) < 0) {
//                missEntry.add(name);
//            } else if (map.get(name) > 1) {
//                missExit.add(name);
//            }
//        }
//
//        for (String name : map.keySet()) {
//            if (map.get(name) == 1) {  // opening paren
//                missExit.add(name);
//            }
//        }
//
//        return Arrays.asList(new ArrayList<>(missExit), new ArrayList<>(missEntry));
//    }

    // find unusual often employees who badged into the room 3 or more times in a 1-hour period,
    // and returns each time that they badged in during that period. (If there are multiple 1- hour
    // periods where this was true, just return the first one.)

    // input  [["Paul", 1315],...]
    // output {paul: [time1, time2...]}
    public Map<String, List<String>> findUnusualEntries(List<List<String>> records) {
        // build a map of employee to times
        Map<String, List<Integer>> map = new HashMap<>();
        for (List<String> record : records) {
            String name = record.get(0);
            int time = Integer.parseInt(record.get(1));

            map.putIfAbsent(name, new ArrayList<>());
            map.get(name).add(time);
        }

        Map<String, List<String>> unusual = new HashMap<>();
        for (String name : map.keySet()) {
            List<Integer> entries = map.get(name);
            Collections.sort(entries);

            if (entries.size() < 3) {
                continue;
            }

            // O(m*nlog(n)) m: people count, n: times
            for (int i = 0; i < entries.size() - 2; i++) {
                int j = i + 2; // more than 3 times
                while (j < entries.size() && entries.get(j) - entries.get(i) >= 0
                        && entries.get(j) - entries.get(i) <= 100) {
                    j += 1;

                    unusual.putIfAbsent(name, new ArrayList<>());
                    List<String> timeStrs = new ArrayList<>();
                    for (int start = i; start < j; start++) {
                        timeStrs.add("" + entries.get(start));
                        unusual.put(name, timeStrs);
                    }
                }

                // only need frist time period
                if (unusual.containsKey(name)) {
                    break;
                }
            }
        }

        return unusual;
    }


    public static void main(String[] args) {
        BadgeAccess solution = new BadgeAccess();
        // Q1
        System.out.println("Q1: ");
        List<List<String>> records = Arrays.asList(
                Arrays.asList("Martha",   "exit"),
                Arrays.asList("Paul",     "enter"),
                Arrays.asList("Martha",   "enter"),
                Arrays.asList("Martha",   "exit"),
                Arrays.asList("Jennifer", "enter"),
                Arrays.asList("Paul",     "enter"),
                Arrays.asList("Curtis",   "enter"),
                Arrays.asList("Paul",     "exit"),
                Arrays.asList("Martha",   "enter"),
                Arrays.asList("Martha",   "exit"),
                Arrays.asList("Jennifer", "exit"));
        System.out.println(solution.invalidBadgeRecords(records));

        // Q2
        System.out.println("Q2: ");

        List<List<String>> badgeRecords2 = Arrays.asList(
                Arrays.asList("Paul", "1315"),
                Arrays.asList("Jennifer", "1910"),
                Arrays.asList("John", "830"),
                Arrays.asList("Paul", "1355"),
                Arrays.asList("John", "835"),
                Arrays.asList("Paul", "1405"),
                Arrays.asList("Paul", "1630"),
                Arrays.asList("John", "855"),
                Arrays.asList("John", "915"),
                Arrays.asList("John", "930"),
                Arrays.asList("Jennifer", "1335"),
                Arrays.asList("Jennifer", "730"),
                Arrays.asList("John", "163")
        );

        System.out.println(solution.findUnusualEntries(badgeRecords2));
    }
}
