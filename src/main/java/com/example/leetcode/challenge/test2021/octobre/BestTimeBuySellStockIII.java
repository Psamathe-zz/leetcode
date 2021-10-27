package com.example.leetcode.challenge.test2021.octobre;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * Example 4:
 *
 * Input: prices = [1]
 * Output: 0
 * [6,1,3,2,4,7]
 * [1,2,4,2,5,7,2,4,9,0]
 */
public class BestTimeBuySellStockIII {
    public static void main(String[] args) {
        BestTimeBuySellStockIII bestTimeBuySellStockIII = new BestTimeBuySellStockIII();
        bestTimeBuySellStockIII.maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0});
    }

    public int maxProfit(int[] prices) {
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
    public int maxProfitVOld(int[] prices) {
        int length = prices.length;
        int[] buy = new int[length];
        int[] sell = new int[length];
        boolean[] status = new boolean[length];
        int index = 0;
        buy[index] = prices[0];
        for (int i = 1; i < length; i++) {
            if(prices[i] < sell[index]){
                index++;
                buy[index] = prices[i];
            } else if(prices[i] < buy[index]){
                buy[index] = prices[i];
            } else if(prices[i] > buy[index] && !status[index]){
                sell[index] = prices[i];
                status[index] = true;
            } else if(prices[i] > sell[index] && status[index]){
                sell[index] = prices[i];
            }
        }
        int max0 = 0;
        int max1 = 0;
        int diff;
        for (int i = 0; i <= index; i++) {
            diff = sell[i] - buy[i];
            if(diff > max0){
                if(diff > max1){
                    max0 = max1;
                    max1 = diff;
                } else
                    max0 = diff;
            }
        }
        return max0 + max1;
    }


}
