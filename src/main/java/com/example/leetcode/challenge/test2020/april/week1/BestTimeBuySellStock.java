package com.example.leetcode.challenge.test2020.april.week1;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
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
 *
 */
public class BestTimeBuySellStock {
    public static void main(String[] args) {
        int[] nums1 = new int[]{7,6,4,3,1};
        BestTimeBuySellStock bestTimeBuySellStock = new BestTimeBuySellStock();
        int result = bestTimeBuySellStock.maxProfit(nums1);
        System.out.println(result);
    }

    public int maxProfit(int[] prices) {
        int result = 0;
        int length = prices.length;
        int temp;
        if(prices == null || length < 2)
            return result;
        for(int i = 0; i < length -1;i++){
            temp = prices[i+1] - prices[i];
            if(temp>= 0){
                result += temp;
            }
        }
        return result;
    }


    /**
     *
     * @param prices
     * @return
     * the same thing as mine
     */
    public int maxProfitV2(int[] prices) {
        int result = 0;
        for(int i=0; i<prices.length-1; i++)
        {
            if(prices[i] <prices[i+1])
            {
                result += prices[i+1] - prices[i];
            }
        }
        return result;
    }
}
