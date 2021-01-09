package com.example.leetcode.challenge.test2020.August.week3;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * [3,2,6,5,0,3]
 * [1,2,3,4,5]
 */
public class BestTimeBuySellStockIII {
    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3,4,5};
        BestTimeBuySellStockIII bestTimeBuySellStockIII = new BestTimeBuySellStockIII();
        bestTimeBuySellStockIII.maxProfit(prices);

    }

    int maxProfit(int[] prices) {
        if (prices.length == 0 )
            return 0;
        int n = prices.length;
        int[] global = new int[3];
        int[] local = new int[3];
        for (int i = 0; i < n - 1; ++i) {
            int diff = prices[i + 1] - prices[i];
            for (int j = 2; j >= 1; --j) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[2];

    }
    public int maxProfitOld(int[] prices) {
        int a = 0;
        int b = 0;
        int length = prices.length;
        if(length < 2)
            return 0;
        int start = prices[0];
        int val;
        for (int i = 1; i < length; i++) {
            if(prices[i] - prices[i-1] < 0 ){
                start = prices[i];
            }

            if(prices[i] > start){
                val = prices[i] - start;
                if(a == 0){
                    a = val;
                } else if(b == 0){
                    if(val > a){
                        b = val;
                    } else {
                        b = a;
                        a = val;
                    }
                } else if(val >= b){
                    b = val;
                    a = b;
                }  else if (val > a) {
                    a = val;
                }
            }
        }

        return a + b;
    }
}
