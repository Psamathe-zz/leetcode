package com.example.leetcode.challenge.test2022.march;

import com.example.leetcode.sometest.A;

/**
 * An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 *
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: 3
 * Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 0
 */
public class ArithmeticSlices {
    public static void main(String[] args) {
        ArithmeticSlices arithmeticSlices = new ArithmeticSlices();
        arithmeticSlices.numberOfArithmeticSlices(new int[]{1,3,5,7,9});
    }

    public int numberOfArithmeticSlices(int[] nums) {
        int length = nums.length;
        int[] diff = new int[length - 1];
        for (int i = 1; i < length; i++) {
            diff[i-1] = nums[i] - nums[i - 1];
        }

        int res = 0;
        int count = 1;
        for (int i = 1; i < diff.length; i++) {
            if(diff[i] == diff[i-1]) {
                count++;
            } else {
                res += helper(count + 1);
                count = 1;
            }
        }
        res += helper(count + 1);
        return res;
    }

    public int helper(int n) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res += (i - 2);
        }
        return res;
    }
}
