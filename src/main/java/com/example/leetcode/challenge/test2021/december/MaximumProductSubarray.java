package com.example.leetcode.challenge.test2021.december;

/**
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 *
 * It is guaranteed that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {

    }

    public int maxProduct(int[] nums) {
        int length = nums.length;
        int temp;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            temp = 1;
            for (int j = i; j < length; j++) {
                temp *= nums[j];
                result = Math.max(result,temp);
                if(temp == 0)
                    break;
            }
        }
        return result;
    }
}
