package com.example.leetcode.challenge.test2021.february.week1;


import java.util.Arrays;

/**
 * We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 *
 * Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.
 *
 * A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 2
 * Example 3:
 *
 * Input: nums = [1,1,1,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -109 <= nums[i] <= 109
 */
public class LongestHarmoniousSubsequence {
    public static void main(String[] args) {

    }

    public int findLHS(int[] nums) {
        int len = nums.length;
        int r = 0;
        Arrays.sort(nums);
        int start = 0;
        int newStart = 0;
        for (int i = 1; i < len; ++i) {
            if (nums[i] - nums[start] > 1) {
                start = newStart;
            }

            if (nums[i] != nums[i - 1]) {
                newStart = i;
            }

            if (nums[i] - nums[start] == 1) {
                r = r > i - start + 1 ? r : i - start + 1;
            }
        }
        return r;
    }
}
