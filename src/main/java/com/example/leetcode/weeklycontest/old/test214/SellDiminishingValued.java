package com.example.leetcode.weeklycontest.old.test214;


import java.util.Arrays;

/**
 * You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.
 *
 * The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color you currently have in your inventory. For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball. After the transaction, there are only 5 yellow balls left, so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).
 *
 * You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color that you initially own. You are also given an integer orders, which represents the total number of balls that the customer wants. You can sell the balls in any order.
 *
 * Return the maximum total value that you can attain after selling orders colored balls. As the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: inventory = [2,5], orders = 4
 * Output: 14
 * Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4 + 3).
 * The maximum total value is 2 + 5 + 4 + 3 = 14.
 * Example 2:
 *
 * Input: inventory = [3,5], orders = 6
 * Output: 19
 * Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5 + 4 + 3 + 2).
 * The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.
 * Example 3:
 *
 * Input: inventory = [2,8,4,10,6], orders = 20
 * Output: 110
 * Example 4:
 *
 * Input: inventory = [1000000000], orders = 1000000000
 * Output: 21
 * Explanation: Sell the 1st color 1000000000 times for a total value of 500000000500000000. 500000000500000000 modulo 109 + 7 = 21.
 *
 *
 * Constraints:
 *
 * 1 <= inventory.length <= 105
 * 1 <= inventory[i] <= 109
 * 1 <= orders <= min(sum(inventory[i]), 109)[773160767]
 * 252264991
 */
public class SellDiminishingValued {
    public static void main(String[] args) {
        int[] inventory = new int[]{773160767};
        int orders = 252264991;
        SellDiminishingValued sellDiminishingValued = new SellDiminishingValued();
        int res = sellDiminishingValued.maxProfit(inventory,orders);
        System.out.println(res);
    }

    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);

        long res = 0, mod = 1000000007;
        int length = inventory.length;
        int j = length - 1 ;
        while(orders > 0) {
            while(j >= 0 && inventory[j] >= inventory[length - 1]) {
                j--;
            }
            int next = 0;
            if(j >= 0) {
                next = inventory[j];
            }
            long bucks = length - 1 - j, delta = inventory[length - 1] - next;
            long rem = bucks * delta;
            if(rem > orders) {
                long dec = orders / bucks;
                long a1 = inventory[length - 1] - dec + 1, an = inventory[length - 1];
                res += (((a1 + an) * dec) / 2) * bucks;
                res += (inventory[length - 1] - dec) * (orders % bucks);
            } else {
                long a1 = next + 1, an = inventory[length - 1];
                res += (((a1 + an) * delta) / 2) * bucks;
                inventory[length - 1] = next;
            }
            orders -= rem;
            res %= mod;
        }
        return (int)res;
    }

}
