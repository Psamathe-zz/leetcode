package com.example.leetcode.challenge.July.week5;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 *
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimeBuySellStockWithCooldown {
    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3,0,2};
        BestTimeBuySellStockWithCooldown bestTimeBuySellStockWithCooldown = new BestTimeBuySellStockWithCooldown();
        int res = bestTimeBuySellStockWithCooldown.maxProfit(prices);
        System.out.println(res);
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


    /**
     * faster solution
     * @param prices
     * @return
     */
    public int maxProfitV1(int[] prices) {
        long noStock = 0, hasStock = Integer.MIN_VALUE, prevNoStock = 0;
        for (int price : prices) {
            long tmp = noStock;
            noStock = Math.max(noStock, hasStock + price);
            hasStock = Math.max(hasStock, prevNoStock - price);
            prevNoStock = tmp;
        }
        return (int)noStock;
    }
}
