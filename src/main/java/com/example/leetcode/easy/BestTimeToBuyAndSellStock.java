package com.example.leetcode.easy;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
        int result = bestTimeToBuyAndSellStock.maxProfit(prices);
        System.out.println(result);
    }

    public int maxProfit(int[] prices) {
        int length = prices.length;
        int result = 0;

        int max = -1;
        for (int i = length - 1; i >= 0 ; i--) {
            result = Math.max((max - prices[i]),result);
            max = Math.max(max,prices[i]);
        }
        return result;
    }
}
