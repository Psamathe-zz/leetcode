package com.example.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * [186,419,83,408]
 * 6249
 * https://www.cnblogs.com/grandyang/p/5138186.html
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins =new int[]{186,419,83,408};
        int amount = 6249;
        CoinChange coinChange = new CoinChange();
        int result = coinChange.coinChange(coins,amount);
        System.out.println(result);
    }

    public int coinChange(int[] coins, int amount) {
        int length = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < length; ++j) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return (dp[amount] > amount) ? -1 : dp[amount];
    }

    /**
     * faster solution
     */
    int total = Integer.MAX_VALUE;
    public int coinChangeV1(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        count(amount, coins.length-1, coins, 0);
        return total == Integer.MAX_VALUE?-1:total;
    }
    void count(int amount, int index, int[] coins, int count){
        if (index<0 || count>=total-1) return;
        int c = amount/coins[index];
        for (int i = c;i>=0;i--){
            int newCount = count + i;
            int rem = amount - i*coins[index];

            if (rem>0 && newCount<total)
                count(rem, index-1, coins, newCount);
            else if (newCount<total)
                total = newCount;
            else if (newCount>=total-1)
                break;
        }
    }


    public int coinChangeVOld(int[] coins, int amount) {
        Arrays.sort(coins);
        int result = 0;
        int index = coins.length - 1;
        while (index >= 0 && amount > 0){
            int value = coins[index];
            while (amount >= value){
                amount -= value;
                result++;
            }
            index--;
        }
        return amount==0?result:-1;
    }


}
