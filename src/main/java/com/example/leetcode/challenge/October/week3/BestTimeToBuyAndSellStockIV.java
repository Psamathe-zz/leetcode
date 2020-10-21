package com.example.leetcode.challenge.October.week3;

import java.util.*;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Notice that you may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * -1,4,-1,-5,3
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 *
 * Constraints:
 *
 * 0 <= k <= 109
 * 0 <= prices.length <= 104
 * 0 <= prices[i] <= 1000
 * 2
 * [1,2,4,2,5,7,2,4,9,0]
 */
public class BestTimeToBuyAndSellStockIV {
    public static void main(String[] args) {
        int k = 2;
        int[] prices = new int[]{1,2,4,2,5,7,2,4,9,0};
        BestTimeToBuyAndSellStockIV bestTimeToBuyAndSellStockIV = new BestTimeToBuyAndSellStockIV();
        bestTimeToBuyAndSellStockIV.maxProfit(k,prices);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4295761.html
     * 这里我们需要两个递推公式来分别更新两个变量local和global，参见网友Code Ganker的博客，我们其实可以求至少k次交易的最大利润。
     * g我们定义local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。然后我们定义global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。它们的递推式为：
     *
     * local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
     *
     * global[i][j] = max(local[i][j], global[i - 1][j])，
     *
     * 其中局部最优值是比较前一天并少交易一次的全局最优加上大于0的差值，和前一天的局部最优加上差值后相比，两者之中取较大值，而全局最优比较局部最优和前一天的全局最优。
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int length = prices.length;
        if(length == 0)
            return 0;
        if (k >= length)
            return solveMaxProfit(prices);
        int[] g = new int[k+1];
        int[] l = new int[k+1];
        for (int i = 0; i < length - 1; ++i) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; --j) {
                l[j] = Math.max(g[j - 1] + Math.max(diff, 0), l[j] + diff);
                g[j] = Math.max(g[j], l[j]);
            }
        }
        return g[k];
    }
    int solveMaxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] - prices[i - 1] > 0) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}
