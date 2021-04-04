package com.example.leetcode.challenge.test2021.march.week3;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Example 2:
 *
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 < prices.length <= 5 * 104
 * 0 < prices[i], fee < 5 * 104
 */
public class BestTimeBuySellStockWithTransactionFee {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/7776979.html
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int length = prices.length;
        int[] sold = new int[length];
        int[] hold = new int[length];

        hold[0] = -prices[0];
        for (int i = 1; i < length; i++) {
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i] - fee);
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
        }
        return sold[length - 1];
    }
}
