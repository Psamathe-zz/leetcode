package com.example.leetcode.challenge.test2021.octobre;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 */
public class BuySellStockWithCooldown {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4997417.html
     * 此题需要维护三个一维数组buy, sell，和rest。其中：
     *
     * buy[i]表示在第i天之前最后一个操作是买，此时的最大收益。
     *
     * sell[i]表示在第i天之前最后一个操作是卖，此时的最大收益。
     *
     * rest[i]表示在第i天之前最后一个操作是冷冻期，此时的最大收益。
     *
     * 我们写出递推式为：
     *
     * buy[i]  = max(rest[i-1] - price, buy[i-1])
     * sell[i] = max(buy[i-1] + price, sell[i-1])
     * rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE, pre_buy = 0, sell = 0, pre_sell = 0;
        for (int price : prices) {
            pre_buy = buy;
            buy = Math.max(pre_sell - price, pre_buy);
            pre_sell = sell;
            sell = Math.max(pre_buy + price, pre_sell);
        }
        return sell;
    }
}
