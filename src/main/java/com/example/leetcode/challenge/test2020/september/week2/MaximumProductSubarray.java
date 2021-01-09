package com.example.leetcode.challenge.test2020.september.week2;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,-2,4};
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        maximumProductSubarray.maxProduct(nums);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4028713.html
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int res = nums[0];
        int[] f = new int[length];
        int[] g = new int[length];
        f[0] = nums[0];
        g[0] = nums[0];
        for (int i = 1; i < length; ++i) {
            f[i] = Math.max(Math.max(f[i - 1] * nums[i], g[i - 1] * nums[i]), nums[i]);
            g[i] = Math.min(Math.min(f[i - 1] * nums[i], g[i - 1] * nums[i]), nums[i]);
            res = Math.max(res, f[i]);
        }
        return res;
    }
}
