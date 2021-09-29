package com.jetbrains.zCompanyInterviewPrep.robinhood;

import java.util.*;
import java.util.stream.Collectors;

public class MarginCall {
    // "static void main" must be defined in a public class.

        /*
            Our goal is to build a simplified version of a real Robinhood system that reads a customer's trades from a stream, maintains what they own, and rectifies running out of cash (through a process called a "margin call", which we'll define later). We’re looking for clean code, good naming, testing, etc. We're not particularly looking for the most performant solution.

        **Step 1 (tests 1-4): Parse trades and build a customer portfolio**

        Your input will be a list of trades, each of which is itself a list of strings in the form [timestamp, symbol, B/S (for buy/sell), quantity, price],
        e.g.

        [["1", "AAPL", "B", "10", "10"], ["3", "GOOG", "B", "20", "5"], ["10", "AAPL", "S", "5", "15"]]

        is equivalent to buying 10 shares (i.e. units) of AAPL for 5 each at timestamp 3, and selling 5 shares of AAPL for $15 at timestamp 10.

        **Input assumptions:**

        - The input is sorted by timestamp
        - All numerical values are nonnegative integers
        - Trades will always be valid (i.e. a customer will never sell more of a stock than they own).

        From the provided list of trades, our goal is to maintain the customer's resulting portfolio (meaning everything they own),
        **assuming they begin with $1000**. For instance, in the above example, the customer would end up with $875, 5 shares of AAPL,
        and 20 shares of GOOG. You should return a list representing this portfolio, formatting each individual position as a list of
        strings in the form [symbol, quantity], using 'CASH' as the symbol for cash and sorting the remaining stocks alphabetically
        based on symbol. For instance, the above portfolio would be represented as

        [["CASH", "875"], ["AAPL", "5"], ["GOOG", "20"]]

        questions
            1. does the User have the enough money to buy the stocks? can we assume all trades are valid...


        **Step 2 (tests 5-7): Margin calls**

        If the customer ever ends up with a negative amount of cash **after a buy**, they then enter a process known as a **margin call**
        to correct the situation. In this process, we forcefully sell stocks in the customer's portfolio (sometimes including the shares
        we just bought) until their cash becomes non-negative again.

        We sell shares from the most expensive to least expensive shares (based on each symbol's most-recently-traded price) with ties
        broken by preferring the alphabetically earliest symbol. Assume we're able to sell any number of shares in a symbol at that
        symbol's most-recently-traded price.

        For example, for this input:

        ```
                                                    cash: 1000
        [["1", "AAPL", "B", "10", "100"],           cash: 0      aapl: 10
        ["2", "AAPL", "S", "2", "80"],              cash: 160      aapl: 8
        ["3", "GOOG", "B", "15", "20"]]             cash: -140      aapl: 8   GOOG: 15
                                                    cash: 20      aapl: 6   GOOG: 15
        ```

        The customer would be left with 8 AAPL shares, 15 GOOG shares, and 80 a share) to cover the deficit. Afterwards,
        they would have 6 shares of AAPL, 15 shares of GOOG, and a cash balance of $20.

        The expected output would be

        [["CASH", "20"], ["AAPL", "6"], ["GOOG", "15"]]

        **Step 3/Extension 1 (tests 8-10): Collateral**

        Certain stocks have special classifications, and require the customer to also own another "collateral" stock,
        meaning it cannot be sold during the margin call process. Our goal is to handle a simplified version of this phenomenon.


        // question,
        // 1. can we assume all of the stocks ending with teh 'O' is teh special stock...
        // 2. since, the customer must hold at least as many shares of the collateral stock as they do the special stock
        //  the margin call process will now sell the most valuable **non-collateral** share until the balance is positive again.
        //  can we sell the collateral stock if quantity of the collateral is larger than the quantity of the special?    yes

        Formally, we'll consider stocks with symbols ending in "O" to be special, with the remainder of the symbol identifying
        its collateral stock. For example, AAPLO is special, and its collateral stock is AAPL. **At all times**, the customer
        must hold at least as many shares of the collateral stock as they do the special stock; e.g. they must own at least as
        many shares of AAPL as they do of AAPLO.

        As a result, the margin call process will now sell the most valuable **non-collateral** share until the balance is
        positive again. Note that if this sells a special stock, some of the collateral stock may be freed up to be sold.

        For example, if the customer purchases 5 shares of AAPL for 75 each, then finally 5 shares of AAPLO for 125,
        but their shares of AAPL can no longer be used to cover the deficit (since they've become collateral for AAPLO).
        As a result, 2 shares of GOOG would be sold back (again at 25, 5 AAPL, 5 AAPLO, and 3 GOOG. Thus, with an input of

        [["1", "AAPL", "B", "5", "100"], ["2", "GOOG", "B", "5", "75"], ["3", "AAPLO", "B", "5", "50"]]

        the corresponding output would be

        [["CASH", "25"], ["AAPL", "5"], ["AAPLO", "5"], ["GOOG", "3"]
        */
        private static final String CASH = "CASH";
        private static final int INIT_NUM = 1000;

        //  section 1
        public List<List<String>> getUserPortfolio(String[][] records) {
            // map of <symbol, shareCount>
            Map<String, Integer> symbolToShares = new HashMap<>();
            symbolToShares.put(CASH, INIT_NUM);
            // Map<String, Integer> prices = new HashMap<>();
            for (String[] record : records) {
                // update shares
                tradeStock(symbolToShares, record);
                // update balance
                updateCash(symbolToShares, record);
            }

            return generateOutput(symbolToShares);
        }


        //  section 2
        public List<List<String>> getUserPortfolioWithMarginCall(String[][] records) {
            Map<String, Integer> symbolToShares = new HashMap<>();
            Map<String, Integer> symbolToPrices = new HashMap<>();

            symbolToShares.put(CASH, INIT_NUM);

            for (String[] record : records) {
                tradeStock(symbolToShares, record);
                updatePrice(symbolToShares, symbolToPrices, record);
                updateCash(symbolToShares, record);
                marginCall(symbolToShares, symbolToPrices);
            }

            return generateOutput(symbolToShares);
        }

        //  section 3
        public List<List<String>> getUserPortfolioWithCollateralMarginCall(String[][] records) {
            Map<String, Integer> symbolToShares = new HashMap<>();
            Map<String, Integer> symbolToPrices = new HashMap<>();

            symbolToShares.put(CASH, INIT_NUM);

            for (String[] record : records) {
                tradeStock(symbolToShares, record);
                updatePrice(symbolToShares, symbolToPrices, record);
                updateCash(symbolToShares, record);
                marginCallWithCollateral(symbolToShares, symbolToPrices);
            }

            return generateOutput(symbolToShares);
        }


        //---------------------inner methods---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // ["1", "AAPL", "B", "10", "10"]
        private  void tradeStock(Map<String, Integer> symbolToShares, String[] record) {
            String symbol = record[1];
            String side = record[2];
            String shares = record[3];

            if (side.equals("B")) {
                symbolToShares.put(symbol, symbolToShares.getOrDefault(symbol, 0) + Integer.parseInt(shares));
            } else {
                symbolToShares.put(symbol, symbolToShares.get(symbol) - Integer.parseInt(shares));
            }
        }

        private void updateCash(Map<String, Integer> symbolToShares, String[] record) {
            String side = record[2];
            int quantity = Integer.parseInt(record[3]);
            int price = Integer.parseInt(record[4]);
            int sign = (side.equals("B")) ? -1 : 1;
            int total = quantity * price * sign;

            symbolToShares.put(CASH, symbolToShares.get(CASH) + total);
        }

        private void updatePrice(Map<String, Integer> symbolToShares, Map<String, Integer> symbolToPrices,  String[] record) {
            String symbol = record[1];
            int price = Integer.parseInt(record[4]);
            symbolToPrices.put(symbol, price);

            // remove from current prce if quantity is 0;   ?????
            if (symbolToShares.get(symbol) == 0) {
                symbolToPrices.remove(symbol);
            }
        }

        private void marginCall(Map<String, Integer> symbolToShares, Map<String, Integer> symbolToPrices) {
            int cash = symbolToShares.get(CASH);
            if (cash >= 0) return;

            while (cash < 0) {
                String symbol = getHighestStock(symbolToPrices);
                int price = symbolToPrices.get(symbol);
                int quantity = symbolToShares.get(symbol);
                while (quantity > 0 && cash < 0) {
                    cash += price;
                    --quantity;
                }

                symbolToShares.put(symbol, quantity);
                if (quantity == 0) {
                    // remove from price list if no share left
                    symbolToPrices.remove(symbol);
                }
            }

            symbolToShares.put(CASH, cash);
        }

        private void marginCallWithCollateral(Map<String, Integer> symbolToShares, Map<String, Integer> symbolToPrices) {
            int cash = symbolToShares.get(CASH);
            if (cash >= 0) {
                return;
            }

            /*
                if the highest stock is the other stock, it's safe to sell multiple shares at a time
                                     is the special stock, it's good to sell one share since
                                     is the collatoral stock, we need to check if # of shares > # of the special

                considering the fact that if this sells a special stock, some of the collateral stock may be freed up to be sold.

             */

            while (cash < 0) {
                String symbol = getHighestStockWithCollateral(symbolToShares, symbolToPrices);

                int price = symbolToPrices.get(symbol);
                int quantity = symbolToShares.get(symbol);

                cash += price;
                quantity--;


                symbolToShares.put(symbol, quantity);
                if (quantity == 0) {
                    // remove from price list if no share left
                    symbolToPrices.remove(symbol);
                }
            }
            symbolToShares.put(CASH, cash);
        }

        private String getHighestStock(Map<String, Integer> symbolToPrices) {
            List<String> symbols = new ArrayList<>(symbolToPrices.keySet());

            Collections.sort(symbols, (a, b) -> {
                if (symbolToPrices.get(a).equals(symbolToPrices.get(b))) {
                    return a.compareTo(b);
                }
                return Integer.compare(symbolToPrices.get(b), symbolToPrices.get(a));  // sorted descreasing order
            });
            return symbols.get(0);
        }

        private String getHighestStockWithCollateral(Map<String, Integer> symbolToShares, Map<String, Integer> symbolToPrices) {
            List<String> symbols = new ArrayList<>(symbolToPrices.keySet());

            Collections.sort(symbols, (a, b) -> {
                if (symbolToPrices.get(a).equals(symbolToPrices.get(b))) {
                    return a.compareTo(b);
                }
                return Integer.compare(symbolToPrices.get(b), symbolToPrices.get(a));
            });

            int i = 0;
            while (i < symbols.size()) {

                String symbol = symbols.get(i);
                if ((symbol.charAt(symbol.length() - 1) != 'O' && !symbolToPrices.containsKey(symbol + "O"))    // other stock
                      || (symbol.charAt(symbol.length() - 1) == 'O')   // special
                      || (symbol.charAt(symbol.length() - 1) != 'O'    // collateral with some extra shares that could be sold
                                && symbolToPrices.containsKey(symbol + "O")
                                && symbolToShares.get(symbol) > symbolToShares.get(symbol+"O"))) {

                    break;
                }
                i++;
            }

            return symbols.get(i);
        }

        private List<List<String>> generateOutput(Map<String, Integer> symbolToShares) {
            List<List<String>> res = new ArrayList<>();
            for (String symbol : symbolToShares.keySet()) {
                if (symbolToShares.get(symbol) != 0) {
                    res.add(Arrays.asList(symbol, symbolToShares.get(symbol) + ""));
                }
            }

            Collections.sort(res, (a, b) -> {
                if (a.get(0).equals(CASH)) {
                    return -1;
                }
                if (b.get(0).equals(CASH)) {
                    return -1;
                }
                return a.get(0).compareTo(b.get(0));
            });

            return res;
        }

        public static void main(String[] args) {
            MarginCall solution = new MarginCall();

            String[][] records = new String [][]{
                    {"1", "AAPL", "B", "10", "10"},
                    {"3", "GOOG", "B", "20", "5"},
                    {"10", "AAPL", "S", "5", "15"}
            };
            System.out.println("Q1 test case 0: " + solution.getUserPortfolio(records));

            records = new String [][]{
                    {"1", "AAPL", "B", "10", "10"},
                    {"3", "GOOG", "B", "20", "5"},
                    {"4", "  FB", "B", "5", "12"},
                    {"3", "GOOG", "S", "3", "8"},
                    {"3", "GOOG", "B", "5", "10"},
                    {"10", "AAPL", "S", "5", "15"}
            };
            System.out.println("Q1 test case 1: " + solution.getUserPortfolio(records));

            String[][] record2 = new String [][]{
                    {"1", "AAPL", "B", "10", "100"},
                    {"2", "AAPL", "S", "2", "80"},
                    {"3", "GOOG", "B", "15", "20"}
            };
            System.out.println("Q2 test case 1: " + solution.getUserPortfolioWithMarginCall(record2));

            record2 = new String [][]{
                    {"1", "AAPL", "B", "5", "100"},
                    {"2", "ABPL", "B", "5", "100"},
                    {"3", "AAPL", "S", "2", "80"},
                    {"4", "ABPL", "S", "2", "80"},
                    // has tie on price, take alpha first
                    {"5", "GOOG", "B", "15", "30"}
            };
            System.out.println("Q2 test case 2: " + solution.getUserPortfolioWithMarginCall(record2));

            record2 = new String [][]{
                    {"1", "AAPL", "B", "5", "100"},
                    {"2", "ABPL", "B", "5", "100"},
                    {"3", "AAPL", "S", "2", "80"},
                    {"4", "ABPL", "S", "2", "120"},
                    // pick high price first
                    {"5", "GOOG", "B", "15", "30"}
            };
            System.out.println("Q2 test case 3: " + solution.getUserPortfolioWithMarginCall(record2));

            record2 = new String [][]{
                    {"1", "AAPL", "B", "5", "100"},
                    {"2", "ABPL", "B", "5", "100"},
                    {"3", "AAPL", "S", "2", "80"},
                    {"4", "ABPL", "S", "2", "120"},
                    // need to sell multiple stocks
                    {"5", "GOOG", "B", "10", "80"}
            };
            System.out.println("Q2 test case 4: " + solution.getUserPortfolioWithMarginCall(record2));

            String[][] record3 = new String[][]{
                    {"1", "AAPL", "B", "5", "100"},
                    {"2", "GOOG", "B", "5", "75"},
                    {"3", "AAPLO", "B", "5", "50"}
            };
            System.out.println("Q3 test case 1: " + solution.getUserPortfolioWithCollateralMarginCall(record3));

            record3 = new String[][]{
                    {"1", "AAPL", "B", "5", "100"},
                    {"2", "GOOG", "B", "5", "75"},
                    {"3", "AAPLO", "B", "5", "90"}
            };
            System.out.println("Q3 test case 2: " + solution.getUserPortfolioWithCollateralMarginCall(record3));
        }
    }
