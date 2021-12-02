package com.example.leetcode.challenge.test2021.december;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {
    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int res = houseRobber.rob(new int[]{1,2,3,1});
        System.out.println(res);
    }

    public int rob(int[] nums) {
        int length = nums.length;
        int[] dp1 = new int[length];
        int[] dp2 = new int[length];

        dp1[0] = nums[0];

        for (int i = 1; i < length; i++) {
            dp1[i] = dp2[i-1] + nums[i];
            dp2[i] = Math.max(dp1[i-1], dp2[i-1]);
        }
        return Math.max(dp1[length - 1], dp2[length - 1]);
    }
}
