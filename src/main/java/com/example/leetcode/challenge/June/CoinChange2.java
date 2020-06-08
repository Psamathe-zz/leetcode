package com.example.leetcode.challenge.June;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 *
 * Note:
 *
 * You can assume that
 *
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 *
 * https://www.cnblogs.com/grandyang/p/7669088.html
 */
public class CoinChange2 {
    public static void main(String[] args) {
        int amount = 0;
        int[] coins = new int[]{};
        CoinChange2 coinChange2 = new CoinChange2();
        int result = coinChange2.change(amount,coins);
        System.out.println(result);
    }

    /**
     * 其中 dp[i][j] 表示用前i个硬币组成钱数为j的不同组合方法，怎么算才不会重复，也不会漏掉呢？
     * 我们采用的方法是一个硬币一个硬币的增加，每增加一个硬币，都从1遍历到 amount，
     * 对于遍历到的当前钱数j，组成方法就是不加上当前硬币的拼法 dp[i-1][j]，还要加上，去掉当前硬币值的钱数的组成方法，当然钱数j要大于当前硬币值，
     * @param amount
     * @param coins
     * @return
     *
     */
    public int change(int amount, int[] coins) {
        int temp;
        int pre;
        int[][] dp = new int[coins.length + 1][ amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length ; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount ; j++) {
                pre =  j - coins[i - 1];
                temp = (j >= coins[i - 1] ? dp[i][pre] : 0);
                dp[i][j] = dp[i - 1][j] + temp;
            }
        }
        return dp[coins.length][amount];
    }

    public int changeV1(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin : coins){
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    int count;
    public int changeOld(int amount, int[] coins) {
        count = 0;
        Arrays.sort(coins);
        help(amount,coins,0);
        return count;
    }

    public void help(int amount, int[] coins,int start){
        if(amount == 0)
            count++;
        for (int i = start; i < coins.length; i++) {
            if(amount - coins[i] < 0)
                break;
            help(amount - coins[i],coins,i);
        }
    }
}
