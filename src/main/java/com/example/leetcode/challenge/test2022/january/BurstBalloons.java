package com.example.leetcode.challenge.test2022.january;

/**
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
 *
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * Example 2:
 *
 * Input: nums = [1,5]
 * Output: 10
 */
public class BurstBalloons {
    public static void main(String[] args) {

    }

    public int maxCoinsV1 (int[] nums) {
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


    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int l, int r) {
        if (l > r) {
            return 0;
        }

        int max = nums[l];
        for (int i = l; i <= r; ++i) {
            int cur = helper(nums, l, i - 1)
                    + get(nums, l - 1) * nums[i] * get(nums, r + 1)
                    + helper(nums, i + 1, r);

            max = Math.max(max, cur);
        }

        return max;
    }

    private int get(int[] nums, int i) {
        if (i < 0 || i >= nums.length) {
            return 1;
        }

        return nums[i];
    }
}
