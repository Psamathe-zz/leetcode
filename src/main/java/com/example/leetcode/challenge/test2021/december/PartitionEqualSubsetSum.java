package com.example.leetcode.challenge.test2021.december;

import java.util.Arrays;

/**
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {

    }

    Boolean[][] dp;
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        int sum = Arrays.stream(nums).sum();

        if(sum%2 == 1)
            return false;
        int half_sum = sum/2;

        dp = new Boolean[nums.length][half_sum+1];
        return findByNumber(nums, half_sum, nums.length-1, dp);
    }

    public boolean findByNumber(int[] nums, int sum, int i, Boolean[][] dp){
        if(i<0)
            return false;

        if(sum < 0)
            return false;

        if(sum==0)
            return true;

        if(dp[i][sum] != null)
            return dp[i][sum];

        boolean inc = findByNumber(nums, sum-nums[i], i-1, dp);
        boolean exc = findByNumber(nums, sum, i-1, dp);

        return dp[i][sum] = inc | exc;
    }
}
