package com.jetbrains.zCompanyInterviewPrep.robinhood;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// "static void main" must be defined in a public class.
public class HouseTrade {
    /*
    https://leetcode.com/discuss/interview-question/882324/robinhood-phone-screen

    第四轮
    A trade is defined as a string containing the 4 properties given below separated by commas:

    Symbol (4 alphabetical characters, left-padded by spaces)
    Side (either "B" or "S" for buy or sell)
    Quantity (4 digits, left-padded by zeros)
    ID (6 alphanumeric characters)
    e.g.
    "AAPL,B,0100,ABC123"

    which represents a trade of a buy of 100 shares of AAPL with ID "ABC123"

    Given two lists of trades - called "house" and "street" trades, write code to create groups of matches between trades and return a list of
    unmatched house and street trades sorted alphabetically. Without any matching, the output list would contain all elements of both house
    and street trades. There are many ways to match trades, the first and most important way is an exact match:

    An exact match is a pair of trades containing exactly one house trade and exactly one street trade with identical symbol, side, quantity, and ID
    Note: Trades are distinct but not unique

    For example, given the following input:

    // house_trades:
    [
    "AAPL,B,0100,ABC123",
    "AAPL,B,0100,ABC123",
    "GOOG,S,0050,CDC333"
    ]

    // street_trades:
    [
    " FB,B,0100,GBGGGG",
    "AAPL,B,0100,ABC123"
    ]

    We would expect the following output:

    [
    " FB,B,0100,GBGGGG",
    "AAPL,B,0100,ABC123",
    "GOOG,S,0050,CDC333"
    ]

    Because the first (or second) house trade and second street trade form an exact match, leaving behind three unmatched trades.

    Follow-up 1 (Test 6,7,8,9): A "fuzzy" match is a house_trade+street_trade pair with identical symbol, type, and quantity ignoring ID.
    Prioritize exact matches over fuzzy matches. Prioritize matching the earliest alphabetical house trade with the earliest alphabetical
    street trade in case of ties.

    Follow-up 2: (Test 10) An offsetting match is a house_trade+house_trade or street_trade+street_trade pair where the symbol and quantity
    of both trades are the same, but the type is different (one is a buy and one is a sell). Prioritize exact and fuzzy matches over offsetting
    matches. Prioritize matching the earliest alphabetical buy with the earliest alphabetical sell.
    */

    // find unmatched trades by doing exact match
    // symobl,side,quantity,id
    // " FB,B,0100,GBGGGG"



//    private List<String> getUniqueMatch(String[] houseTrades, String[] streetTrades) {
//        // map<String, count>
//        Map<String, Integer> houseMap = new HashMap<>();
//        Map<String, Integer> streetMap = new HashMap<>();
//
//        for (String s : houseTrades) {
//            houseMap.put(s, houseMap.getOrDefault(s, 0) + 1);
//        }
//        for (String s : streetTrades) {
//            streetMap.put(s, streetMap.getOrDefault(s, 0) + 1);
//        }
//
//        List<String> res = new ArrayList<>();
//        for (String trade : )
//    }
    abstract class Filter {
        abstract boolean apply(String a, String b);
    }

    class ExactFilter extends Filter {

        @Override
        boolean apply(String a, String b) {
            return a.equals(b);
        }
    }

    class AttributeFilter extends Filter {

        @Override
        boolean apply(String a, String b) {
            return a.split(",")[1].equals(b.split(",")[1]);  // side
        }
    }

    private List<String> getUnMatchOrders(String[] houseTrade, String[] streetTrade ) {
        // iterate over each String array and create a map where key is " symbol and quantity" and value is the list of actual String
        Map<String, List<String>> houseMap = createMap(houseTrade);
        Map<String, List<String>> streetMap = createMap(streetTrade);

        // Q1
        // removeExactMatch(houseMap, streetMap);
        Filter filter1 = new ExactFilter();
        removeMatchesByFilter(houseMap, streetMap, filter1);

        // Q2
        // removeAttributeMatch(houseMap, streetMap);
        Filter filter2 = new AttributeFilter();
        removeMatchesByFilter(houseMap, streetMap, filter2);

        // Q3
//        Filter filter3 = new OffsetFilter();
        offsetMatches(houseMap, streetMap);

        List<String> unMatched = new ArrayList<>();
        for (List<String> val : houseMap.values()) {
            unMatched.addAll(val);
        }
        for (List<String> val : streetMap.values()) {
            unMatched.addAll(val);
        }
        Collections.sort(unMatched);

        return unMatched;
    }

    // Map<String, List<String>>  houseMap, streetMap
    // removeMatches()

    private void removeMatches(Map<String, List<String>> houseMap, Map<String, List<String>> streetMap) {

    }



    private void offsetMatches(
            Map<String, List<String>> houseMap,
            Map<String, List<String>> streetMap
    ) {
        offsetMatcheHelper(houseMap);
        offsetMatcheHelper(streetMap);
    }

    public void offsetMatcheHelper(Map<String, List<String>> map){
        List<String> mapKey = new ArrayList<>(map.keySet());
        for(String key : mapKey){
            List<String> list = map.get(key);
            Queue<String> q1 = new LinkedList<>(); // for buy
            Queue<String> q2 = new LinkedList<>(); // for sell
            for(String s : list){
                if(s.split(",")[1].equals("B")){
                    if(!q2.isEmpty()){
                        q2.poll();
                    }else{
                        q1.offer(s);
                    }
                }else{
                    if(!q1.isEmpty()){
                        q1.poll();
                    }else{
                        q2.offer(s);
                    }
                }
            }
            List<String> new_list = new ArrayList<>();

            while (!q1.isEmpty()){
                new_list.add(q1.poll());
            }
            while (!q2.isEmpty()){
                new_list.add(q2.poll());
            }
            map.put(key, new_list);
        }
    }

    private void removeMatchesByFilter(
            Map<String, List<String>> houseMap,
            Map<String, List<String>> streetMap,
            Filter filter
    ) {
        List<String> houseMapKeys = new ArrayList<>(houseMap.keySet());
        Collections.sort(houseMapKeys);   //
        for (String key : houseMapKeys) {
            if (streetMap.containsKey(key)) {
                // has same symbol and quantity, try to cancel out
                List<Integer> hTradeRemove = new ArrayList<>();
                List<Integer> sTradeRemove = new ArrayList<>();

                List<String> hListOfTrades = houseMap.get(key);
                List<String> sListOfTrades = streetMap.get(key);

                Collections.sort(hListOfTrades);
                Collections.sort(sListOfTrades);

                for (int i = 0; i < hListOfTrades.size(); i++) {
                    String hTrade = hListOfTrades.get(i);
                    for (int j = 0; j < sListOfTrades.size(); j++) {
                        if (sTradeRemove.contains(j)) {
                            // already matched
                            continue;
                        }
                        String sTrade = sListOfTrades.get(j);
                        if (filter.apply(hTrade, sTrade)) {
                            hTradeRemove.add(i);
                            sTradeRemove.add(j);
                            break;
                        }
                    }
                }

                // update list with remaining trades after matches
                houseMap.put(key, getRemainList(hListOfTrades, hTradeRemove));
                streetMap.put(key, getRemainList(sListOfTrades, sTradeRemove));
            }
        }
    }

    private static List<String> getRemainList(List<String> original, List<Integer> remove) {
        List<String> remain = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            if (remove.contains(i)) continue;
            remain.add(original.get(i));
        }
        return remain;
    }

    private Map<String, List<String>> createMap(String[] trades){
        //create a Map with Symbol+ " "+ Quantity as key and value as a List<String> which contains actual String
        Map<String, List<String>> symbolToTrade = new HashMap<>();
        for (String trade : trades) {
            String[] h = trade.split(",");
            String symbol = h[0];
            String quantity = h[2];
            String key = symbol + " " + quantity;
            symbolToTrade.putIfAbsent(key, new ArrayList<>());
            symbolToTrade.get(key).add(trade);
        }
        return symbolToTrade;
    }

    public static void main(String[] args) {
        HouseTrade solution = new HouseTrade();

        String[] houseTrades = {
                "AAPL,B,0100,ABC123", "AAPL,B,0100,ABC123", "GOOG,S,0050,CDC333"
        };
        String[] streetTrades = {
                " FB,B,0100,GBGGGG", "AAPL,B,0100,ABC123"
        };
        System.out.println("Test case 0: " + solution.getUnMatchOrders(houseTrades, streetTrades));

        houseTrades = new String[] {
                "AAPL,B,0100,ABC123",
                "GOOG,S,0050,CDC333"
        };
        streetTrades = new String[] {
                "  FB,B,0100,GBGGGG",
                "AAPL,B,0100,ABC123"
        };
        System.out.println("Test case 1: " + solution.getUnMatchOrders(houseTrades, streetTrades));

        houseTrades = new String[] {
                "AAPL,S,0010,ZYX444",
                "AAPL,S,0010,ZYX444",
                "AAPL,B,0010,ABC123",
                "GOOG,S,0050,GHG545"
        };
        streetTrades = new String[] {
                "GOOG,S,0050,GHG545",
                "AAPL,S,0010,ZYX444",
                "AAPL,B,0010,TTT222"
        };
        System.out.println("Test case 2: " + solution.getUnMatchOrders(houseTrades, streetTrades));

        houseTrades = new String[] {
                "AAPL,B,0010,ABC123",
                "AAPL,S,0015,ZYX444",
                "AAPL,S,0015,ZYX444",
                "GOOG,S,0050,GHG545"
        };
        streetTrades = new String[] {
                "GOOG,S,0050,GHG545",
                "AAPL,S,0015,ZYX444",
                "AAPL,B,0500,TTT222"
        };
        System.out.println("Test case 3: " + solution.getUnMatchOrders(houseTrades, streetTrades));

        houseTrades = new String[] {
                "AAPL,B,0100,ABC123",
                "AAPL,B,0100,ABC123",
                "AAPL,B,0100,ABC123",
                "GOOG,S,0050,CDC333"
        };
        streetTrades = new String[] {
                "  FB,B,0100,GBGGGG",
                "AAPL,B,0100,ABC123",
                "AAPL,B,0100,ABC123",
                "GOOG,S,0050,CDC333",
        };
        System.out.println("Test case 4: " + solution.getUnMatchOrders(houseTrades, streetTrades));

        houseTrades = new String[] {
                "AAPL,B,0100,ABC123",
                "AAPL,B,0100,ABC123",
                "AAPL,B,0100,ABC123",
                "GOOG,S,0050,CDC333"
        };
        streetTrades = new String[] {
                "  FB,B,0100,GBGGGG",
                "AAPL,B,0100,ABC123",
                "AAPL,B,0100,ABC123",
                "GOOG,S,0050,CDC333",
                "AAPL,S,0100,ABC124"
        };

        /*
            "AAPL,B,0100,ABC123",
            "  FB,B,0100,GBGGGG","AAPL,S,0100,ABC124"
         */
        // with offset pair
        System.out.println("Test case 5: " + solution.getUnMatchOrders(houseTrades, streetTrades));

        houseTrades = new String[] {
                "AAPL,B,0010,ABC123",
                "AAPL,S,0015,ZYX444",
                "AAPL,S,0015,ZYX444",
                "AAPL,S,0015,ZYX444",
                "AAPL,S,0015,ZYX444",
                "AAPL,S,0015,ZYX444",
                "GOOG,S,0050,GHG545"
        };
        streetTrades = new String[] {
                "GOOG,S,0050,GHG545",
                "AAPL,S,0015,ZYX444",
                "AAPL,S,0015,ZYX444",
                "AAPL,S,0015,ZYX444",
                "AAPL,S,0015,ZYX444",
                "AAPL,B,0500,TTT222"
        };
        System.out.println("Test case 6 (leetcode discuss): " + solution.getUnMatchOrders(houseTrades, streetTrades));
    }

}