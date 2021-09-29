package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.*;

public class ResourceLog {
    // Q1: give user access resources logs,
    // return each user's min and max access timestamp
    // log: [[timestamp, username, resourceId]]
    /*
        input, String[][] or list<String> ....
        output, map<String, List<String>>

        Map: [username, [min, max]]
        time, O(n) ...
        space, O(m) ...
     */
    // 10:02
    public Map<String, List<Integer>> getUserMinMaxAccess(String[][] logs) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (String[] log : logs) {
            String name = log[1];
            int time = Integer.parseInt(log[0]);

            map.putIfAbsent(name, Arrays.asList(time, time));
            List<Integer> list = map.get(name);

            if (list.get(0) > time) {
                list.set(0, time);
            } else if (list.get(1) < time) {
                list.set(1, time);
            }
        }

        return map;
    }

    // Write a function that takes the logs and returns the resource with the highest number of accesses in any 5 minute window,
    // together with how many accesses it saw.
    // input, String[][] logs, output, Map: [resource_id, highest_number]
    /*
        //        String[][] logs2= new String[][]{
//                {"300", "user_1", "resource_3"},
//                {"599", "user_1", "resource_3"},
//                {"900", "user_1", "resource_3"},
//                {"1199", "user_1", "resource_3"},
//                {"1200", "user_1", "resource_3"},
//                {"1201", "user_1", "resource_3"},
//                {"1202", "user_1", "resource_3"}
//        };


            {resource_3=4}

            user

            Map: [resource, list of timestamp]
            iterate through the resource,
                for given resource,
                    sort,
                    sliding window(two pointer)

            time:O(m nlogn)
     */
    public Map<String, Integer> findHotestResource(String[][] logs) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (String[] log : logs) {
            String resource = log[2];
            int time = Integer.parseInt(log[0]);

            map.putIfAbsent(resource, new ArrayList<>());
            map.get(resource).add(time);
        }

        Map<String, Integer> res = new HashMap<>();
        int count = 0;
        String resName = "";
        for (String resource : map.keySet()) {
            List<Integer> timeList = map.get(resource);
            Collections.sort(timeList);

            for (int i = 0; i < timeList.size(); ++i) {
                int j = i;
                while (j < timeList.size() && timeList.get(j) - timeList.get(i) <= 300) {

                    int len = (j - i + 1);
                    if (len > count) {
                        count = len;
                        resName = resource;
                    }

                    j++;
                }
            }
        }

        res.put(resName, count);
        return res;
    }




    public static void main(String[] args) {
        String[][] logs = {{"58523", "user_1", "resource_1"},
        {"62314", "user_2", "resource_2"},
        {"54001", "user_1", "resource_3"},
        {"200", "user_6", "resource_5"},
        {"215", "user_6", "resource_4"},
        {"54060", "user_2", "resource_3"},
        {"53760", "user_3", "resource_3"},
        {"58522", "user_22", "resource_1"},
        {"53651", "user_5", "resource_3"},
        {"2", "user_6", "resource_1"},
        {"100", "user_6", "resource_6"},
        {"400", "user_7", "resource_2"},
        {"100", "user_8", "resource_6"},
        {"54359", "user_1", "resource_3"},
        };

        ResourceLog solution = new ResourceLog();
        System.out.println(solution.getUserMinMaxAccess(logs));

        String[][] logs2= new String[][]{
                {"300", "user_1", "resource_3"},
                {"599", "user_1", "resource_3"},
                {"900", "user_1", "resource_3"},
                {"1199", "user_1", "resource_3"},
                {"1200", "user_1", "resource_3"},
                {"1201", "user_1", "resource_3"},
                {"1202", "user_1", "resource_3"}
        };
        System.out.println("Q2:");
        System.out.println(solution.findHotestResource(logs));
        System.out.println(solution.findHotestResource(logs2));
        System.out.println("");

        //
        System.out.println("Q3:");
        System.out.println("Q3 with log1:");
        System.out.println(solution.buildResourcePossibilityMapping(logs));
        System.out.println("Q3 with log2:");
        System.out.println(solution.buildResourcePossibilityMapping(logs2));
    }




























    public Map<String, List<Integer>> getUserMinMaxAccessTime(String[][] logs) {
        Map<String, List<Integer>> res = new HashMap<>();
        for (String[] log : logs) {

            String user = log[1];
            Integer time = Integer.parseInt(log[0]);

            res.putIfAbsent(user, new ArrayList<>(Arrays.asList(time, time)));
            // min, max
            List<Integer> times = res.get(user);
            if (times.get(0) > time) {
                times.set(0, time);
            }
            if (times.get(1) < time) {
                times.set(1, time);
            }
        }
        return res;
    }

    // Write a function that takes the logs and returns the resource with the highest number of accesses in any 5 minute window, together with how many accesses it saw.
    public Map<String, Integer> findHotestResourceIn5MinWindow(String[][] logs) {
        Map<String, List<Integer>> resToTime = new HashMap<>();
        for (String[] log : logs) {
            Integer time = Integer.parseInt(log[0]);
            String resource = log[2];
            resToTime.putIfAbsent(resource, new ArrayList<>());
            resToTime.get(resource).add(time);
        }
        Map<String, Integer> res = new HashMap<>();
        String resName = "";
        int count = 0;
        for (String resource : resToTime.keySet()) {
            List<Integer> times = resToTime.get(resource);
            Collections.sort(times);

            for (int i = 0; i < times.size(); i++) {
                int j = i;
                while (j < times.size() && times.get(j) - times.get(i) <= 300) {
                    if (j - i + 1 > count) {
                        count = j - i + 1;
                        resName = resource;
                    }
                    j += 1;
                }
            }
        }
        res.put(resName, count);
        return res;
    }

    /*
    Follow Up Question 3 -
    Write a function that takes the logs as input, builds the transition graph and returns it as an adjacency list with probabilities. Add START and END states.

            Specifically, for each resource, we want to compute a list of every possible next step taken by any user, together with the corresponding probabilities.
            The list of resources should include START but not END, since by definition END is a terminal state.

    Expected output for logs1:
    transition_graph(logs1) # =>
    {
        'START': {'resource_1': 0.25, 'resource_2': 0.125, 'resource_3': 0.5, 'resource_6': 0.125},
        'resource_1': {'resource_6': 0.333, 'END': 0.667},
        'resource_2': {'END': 1.0},
        'resource_3': {'END': 0.4, 'resource_1': 0.2, 'resource_2': 0.2, 'resource_3': 0.2},
        'resource_4': {'END': 1.0},
        'resource_5': {'resource_4': 1.0},
        'resource_6': {'END': 0.5, 'resource_5': 0.5}
    }

    For example, of 8 total users, 4 users have resource_3 as a first visit (user_1, user_2, user_3, user_5), 2 users have resource_1 as a first visit (user_6, user_22),
    1 user has resource_2 as a first visit (user_7), and 1 user has resource_6 (user_8) so the possible next steps for START are resource_3 with probability 4/8,
    resource_1 with probability 2/8, and resource_2 and resource_6 with probability 1/8.

    These are the resource paths per user for the first logs example, ordered by access time:
    Map<String, List<String>> userToRes
    {
        'user_1': ['resource_3', 'resource_3', 'resource_1'],
        'user_2': ['resource_3', 'resource_2'],
        'user_3': ['resource_3'],
        'user_5': ['resource_3'],
        'user_6': ['resource_1', 'resource_6', 'resource_5', 'resource_4'],
        'user_7': ['resource_2'],
        'user_8': ['resource_6'],
        'user_22': ['resource_1'],
    }

    // build the calculate mapping...
    Map<String, Map<String, Double>> pos = new HashMap<>();  //
    preRes, Map: [nextRes, number_of_time]

    iterate through the userToRes table....
        process start, others, end

    // calculat the probability...




    Expected output for logs2:
    transition_graph(logs2) # =>
    {
        'START': {'resource_3': 1.0},
        'resource_3': {'resource_3: 0.857, 'END': 0.143}
    }
    */
    public Map<String, Map<String, Double>> buildResourcePossibilityMapping(String[][] logs) {
        if (logs == null || logs.length == 0) return new HashMap<>();

        // sort the access by time
        Arrays.sort(logs, (log1, log2) -> {
            int time1 = Integer.parseInt(log1[0]);
            int time2 = Integer.parseInt(log2[0]);
            return time1 - time2;
        });

        // build user profile
        Map<String, List<String>> userToRes = new HashMap<>();
        for (String[] log : logs) {
            String user = log[1];
            String resource = log[2];
            userToRes.putIfAbsent(user, new ArrayList<>());
            userToRes.get(user).add(resource);
        }

        // build calculate mapping
        Map<String, Map<String, Double>> pos = new HashMap<>();  //
        pos.put("START", new HashMap<>());
        for (String user : userToRes.keySet()) {
            List<String> resources = userToRes.get(user);
            // process START
            String startRes = resources.get(0);
            pos.get("START").putIfAbsent(startRes, 0.0);
            pos.get("START").put(startRes, pos.get("START").getOrDefault(startRes, 0.0) + 1.0);
            // process others
            for (int i = 1; i < resources.size(); i++) {
                String preRes = resources.get(i - 1);
                String nextRes = resources.get(i);
                pos.putIfAbsent(preRes, new HashMap<>());
                pos.putIfAbsent(nextRes, new HashMap<>());
                pos.get(preRes).putIfAbsent(nextRes, 0.0);
                pos.get(preRes).put(nextRes, pos.get(preRes).getOrDefault(nextRes, 0.0) + 1.0);
            }
            // process END
            String lastRes = resources.get(resources.size() - 1);
            pos.putIfAbsent(lastRes, new HashMap<>());
            pos.get(lastRes).put("END", pos.get(lastRes).getOrDefault("END", 0.0) + 1.0);
        }

        System.out.println(pos);

        // calculate
        for (String res : pos.keySet()) {
            Map<String, Double> posMap = pos.get(res);
            double total = 0.0;
            for (double val : posMap.values()) {
                total += val;
            }
            for (String resource : posMap.keySet()) {
                double prob = posMap.get(resource) / total;
                double val = (double) Math.round(prob * 1000d) / 1000d;
                posMap.put(resource, val);
            }
        }
        return pos;
    }


//    public static void main(String[] args) {
//        ResourceLog solution = new ResourceLog();
//
//        String[][] logs = new String[][]{
//                {"58523", "user_1", "resource_1"},
//                {"62314", "user_2", "resource_2"},
//                {"54001", "user_1", "resource_3"},
//                {"200", "user_6", "resource_5"},
//                {"215", "user_6", "resource_4"},
//                {"54060", "user_2", "resource_3"},
//                {"53760", "user_3", "resource_3"},
//                {"58522", "user_22", "resource_1"},
//                {"53651", "user_5", "resource_3"},
//                {"2", "user_6", "resource_1"},
//                {"100", "user_6", "resource_6"},
//                {"400", "user_7", "resource_2"},
//                {"100", "user_8", "resource_6"},
//                {"54359", "user_1", "resource_3"},
//        };
//        System.out.println("Q1:");
//        System.out.println(solution.getUserMinMaxAccessTime(logs));
//        System.out.println("");
//
//        String[][] logs2= new String[][]{
//                {"300", "user_1", "resource_3"},
//                {"599", "user_1", "resource_3"},
//                {"900", "user_1", "resource_3"},
//                {"1199", "user_1", "resource_3"},
//                {"1200", "user_1", "resource_3"},
//                {"1201", "user_1", "resource_3"},
//                {"1202", "user_1", "resource_3"}
//        };
//        System.out.println("Q2:");
//        System.out.println(solution.findHotestResourceIn5MinWindow(logs));
//        System.out.println(solution.findHotestResourceIn5MinWindow(logs2));
//        System.out.println("");
//
//        System.out.println("Q3:");
//        System.out.println("Q3 with log1:");
//        System.out.println(solution.buildResourcePossibilityMapping(logs));
//        System.out.println("Q3 with log2:");
//        System.out.println(solution.buildResourcePossibilityMapping(logs2));
//
//
//    }
}
