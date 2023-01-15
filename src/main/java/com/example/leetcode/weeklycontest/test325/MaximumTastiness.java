package com.example.leetcode.weeklycontest.test325;

import java.util.Arrays;

/**
 * You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
 *
 * The store sells baskets of k distinct candies. The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
 *
 * Return the maximum tastiness of a candy basket.
 *
 *
 *
 * Example 1:
 *
 * Input: price = [13,5,1,8,21,2], k = 3
 * Output: 8
 * Explanation: Choose the candies with the prices [13,5,21].
 * The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
 * It can be proven that 8 is the maximum tastiness that can be achieved.
 * Example 2:
 *
 * Input: price = [1,3,1], k = 2
 * Output: 2
 * Explanation: Choose the candies with the prices [1,3].
 * The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
 * It can be proven that 2 is the maximum tastiness that can be achieved.
 * Example 3:
 *
 * Input: price = [7,7,7,7], k = 2
 * Output: 0
 * Explanation: Choosing any two distinct candies from the candies we have will result in a tastiness of 0.
 */
public class MaximumTastiness {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/solution/c-by-code_learner-595u/
     */
    int [] price;
    int k;

    boolean check(int delta){
        int cnt = 1;
        int cur = price[0];
        for (int x: price){
            if (x - cur >= delta){
                cnt ++;
                cur = x;
            }
        }
        return cnt >= k;
    }

    public int maximumTastiness(int[] price, int k) {
        this.price = price;
        this.k = k;

        int n = price.length;
        Arrays.sort(this.price);

        int l = 0;
        int r = price[n - 1] - price[0];
        while (l < r){
            int mid = (l + r + 1) / 2;
            if (check(mid)){
                l = mid;
            }else{
                r = mid - 1;
            }
        }

        return l;
    }

}
