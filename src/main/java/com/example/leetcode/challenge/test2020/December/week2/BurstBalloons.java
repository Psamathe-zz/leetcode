package com.example.leetcode.challenge.test2020.December.week2;


/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/huenchao/p/5956561.html
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] newNums = new int[nums.length + 2];

        newNums[0] = newNums[nums.length + 1] = 1;
        for (int i = 0; i < nums.length; ++i) {
            newNums[i + 1] = nums[i];
        }

        int n = newNums.length;

        int[][] dp = new int[n][n];

        for (int i = 2; i < n; ++i) {
            for (int l = 0; l < n - i; ++l) {
                int r = l + i;
                for (int j = l + 1; j < r; ++j) {
                    dp[l][r] = Math.max(dp[l][r],
                            newNums[l] * newNums[j] * newNums[r]
                                    + dp[l][j] + dp[j][r]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
