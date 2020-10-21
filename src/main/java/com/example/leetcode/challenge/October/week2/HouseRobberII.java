package com.example.leetcode.challenge.October.week2;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 *
 * Input: nums = [0]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobberII {
    public static void main(String[] args) {

    }

    public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0],nums[1]);
        return Math.max(help(nums,0,nums.length - 2),help(nums,1,nums.length - 1));
    }

    public int help(int[] nums, int start,int end) {

        int[] dp = new int[nums.length];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start],nums[start + 1]);
        for(int i = 2; i < end - start + 1; i ++){
            dp[i] =  Math.max(nums[start + i] + dp[i-2],dp[i-1]);
        }

        return dp[end - start];

    }
}
