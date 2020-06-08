package com.example.leetcode.medium;

import java.util.Arrays;

/**
*Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
 *
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 *
 * Return the maximum profit you can make.
 *
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Note:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 * [1,3,7,5,10,3]
 * 3
 */
public class BestTimeBuyAndSell {
    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        BestTimeBuyAndSell bestTimeBuyAndSell = new BestTimeBuyAndSell();
        int result = bestTimeBuyAndSell.maxProfit_greedy(prices,fee);
        System.out.println(result);
    }

    /**
     * dp[i][j][] i buy and j sell => profit
     * dp[i][j][]
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[] sold = new int[len];
        int[] hold = new int[len];
        hold[0] = -prices[0];
        for (int i = 1; i < len; ++i) {
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i] - fee);
        }
        return sold[len - 1];
    }
    public int maxProfitOld(int[] prices, int fee) {
        int[] dp = new int[prices.length];
        int temp;
        int dpTemp;
        int v;

        for (int i = 1; i < prices.length ; i++) {
            dpTemp = 0;
            for (int j = 0; j < i ; j++) {
                v = prices[i] - prices[j] - fee;
                if(v > 0){
                    temp = 0;
                    for (int k = 0; k < j; k++) {
                        temp = Math.max(temp,dp[k]);
                    }

                    dpTemp = Math.max(v + temp,dpTemp);
                }
            }
            dp[i] = dpTemp;
        }

        return Arrays.stream(dp).max().orElse(0);
    }

    /**
     * 贪心算法
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit_greedy(int[] prices, int fee) {
        int profit = 0;
        int curProfit = 0;
        int minP = prices[0];
        int maxP = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minP = Math.min(minP, prices[i]);
            maxP = Math.max(maxP, prices[i]);
            curProfit = Math.max(curProfit, prices[i] - minP - fee);
            //当买入股票的前一天i-1的股票价值大于（第i天购入时的价值+手续费fee），那么就可以在第i-1天出售这股票，然后在第i天重入购入股票
            if ((maxP - prices[i]) >= fee) {
                profit += curProfit;
                curProfit = 0;
                minP = prices[i];
                maxP = prices[i];
            }
        }
        //curProfit记录了当前一次交易能得到的最大收益，只有当maxP-prices[i]>=fee时，才将curProfit累加到总的收益中。
        // 最后一次交易不需要考虑是否早卖了，所以直接累加最后一次的curProfit。
        return profit + curProfit;
    }

    /**
     * 动态规划
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit_dp(int[] prices, int fee) {
        if (prices == null || prices.length < 2){
            return 0;
        }
        int len = prices.length;

        /**
         * 第i天，如果手中有stock
         * hold[i]表示第i天，你保持这份股票，你可以获得的最大利润,
         * 第i天有两种决定，继续保持这份股票，买入这份股票；
         * 如果第i天保持这股票，那么hold[i] = hold[i-1]，
         * 如果第i天买入这股票（说明前一天已经出售这只股票了），那么hold[i] = sold[i-1] - prices[i]
         * hold[i] = max(hold[i-1], sold[i-1] - prices[i])
         */
        int[] hold = new int[len];

        /**
         * 第i天，如果手中没有stock
         * sold[i]表示第i天，你出售这份股票，你可以获得的最大利润
         * 第i天有两种决定，继续保持这份股票，出售这份股票；
         * 如果第i天保持这股票，那么sold[i] = sold[i-1]，
         * 如果第i天出售这股票，那么sold[i] = hold[i-1] + prices[i] - fee
         * sold[i] = max(sold[i-1], hold[i-1] + prices[i] - fee)
         */
        int[] sold = new int[len];

        hold[0] = -prices[0];
        for (int i = 1; i < len; ++i) {
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i] - fee);
        }
        return sold[len - 1];
    }

}
