package com.example.leetcode.challenge.test2021.june.week3;


/**
 * We are given an array nums of positive integers, and two positive integers left and right (left <= right).
 *
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least left and at most right.
 *
 * Example:
 * Input:
 * nums = [2, 1, 4, 3]
 * left = 2
 * right = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Note:
 *
 * left, right, and nums[i] will be an integer in the range [0, 109].
 * The length of nums will be in the range of [1, 50000].
 * [2,9,2,5,6]
 * 2
 * 8
 * [73,55,36,5,55,14,9,7,72,52]
 * 32
 * 69
 */
public class NumberSubarrays {
    public static void main(String[] args) {
        NumberSubarrays numberSubarrays = new NumberSubarrays();
        numberSubarrays.numSubarrayBoundedMax(new int[]{73,55,36,5,55,14,9,7,72,52}, 32, 69);
    }

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int res = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > right)
                continue;
            int curMax = Integer.MIN_VALUE;
            for (int j = i; j < n; ++j) {
                curMax = Math.max(curMax, nums[j]);
                if (curMax > right)
                    break;
                if (curMax >= left)
                    ++res;
            }
        }
        return res;
    }
}
