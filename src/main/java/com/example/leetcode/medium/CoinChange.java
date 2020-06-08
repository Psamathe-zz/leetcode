package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

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
