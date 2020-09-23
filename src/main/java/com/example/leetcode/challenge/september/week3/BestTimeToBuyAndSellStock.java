package com.example.leetcode.challenge.september.week3;

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

    }

    public int maxProfit(int[] prices) {
        int res = 0;
        int length = prices.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                res = Math.max(prices[j] - prices[i],res);
            }
        }
        return res;
    }

    /***
     * faster solution
     * @param prices
     * @return
     */
    public int maxProfitV1(int[] prices) {
        int maxProfit = 0;
        int minPrice= Integer.MAX_VALUE;
        if (prices == null || prices.length == 0) return 0;

        for (int curPrice: prices) {
            if (curPrice < minPrice)
                minPrice= curPrice;
            else
                maxProfit = Math.max(maxProfit, curPrice- minPrice);
        }
        return maxProfit;
    }
}
