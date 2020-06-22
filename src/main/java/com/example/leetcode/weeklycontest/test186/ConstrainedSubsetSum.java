package com.example.leetcode.weeklycontest.test186;

/**
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subset of that array such that for every two consecutive integers in the subset, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 *
 * A subset of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subset is [10, 2, 5, 20].
 * Example 2:
 *
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subset must be non-empty, so we choose the largest number.
 * Example 3:
 *
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subset is [10, -2, -5, 20].
 */
public class ConstrainedSubsetSum {

    public static void main(String[] args) {
        int[] nums = new int[]{10,2,-10,5,20};
        int k = 2;
        ConstrainedSubsetSum constrainedSubsetSum = new ConstrainedSubsetSum();
        constrainedSubsetSum.constrainedSubsetSum(nums,k);
    }


    public int constrainedSubsetSum(int[] nums, int k) {
        int result = 0;


        int[] dp = new int[nums.length];
        help(nums,k,dp.length-1,dp);
        for(int value: dp){
            result = Math.max(result,value);
        }
        return result;
    }

    public void help(int[] nums , int k , int prefix,int[] dp){

        for(int i = prefix - k ; i< prefix; i++){
            dp[prefix] = nums[prefix] + Math.max(dp[prefix],dp[i]);
        }
    }
}
