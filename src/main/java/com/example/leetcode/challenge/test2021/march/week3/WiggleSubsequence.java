package com.example.leetcode.challenge.test2021.march.week3;


/**
 * Given an integer array nums, return the length of the longest wiggle sequence.
 *
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
 *
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) are alternately positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
 * A subsequence is obtained by deleting some elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence.
 * Example 2:
 *
 * Input: nums = [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * Example 3:
 *
 * Input: nums = [1,2,3,4,5,6,7,8,9]
 * Output: 2
 *
 */
public class WiggleSubsequence {
    public static void main(String[] args) {
        int[] nums = new int[]{1,17,5,10,13,15,10,5,16,8};
        WiggleSubsequence wiggleSubsequence = new WiggleSubsequence();
        wiggleSubsequence.wiggleMaxLength(nums);
    }

    public int wiggleMaxLength(int[] nums) {
        int length = nums.length;
        int[] bigger = new int[length];
        int[] smaller = new int[length];
        int cur = nums[0];
        for (int i = 1; i < length; i++) {
            if(nums[i] > cur) {
                bigger[i] = Math.max(bigger[i - 1], smaller[i - 1] + 1);
                smaller[i] = smaller[i - 1];
            } else if(nums[i] < cur) {
                smaller[i] = Math.max(smaller[i - 1], bigger[i - 1] + 1);
                bigger[i] = bigger[i - 1];
            }  else {
                smaller[i] = smaller[i - 1];
                bigger[i] = bigger[i - 1];
            }
            cur = nums[i];
        }

        return Math.max(smaller[length - 1], bigger[length - 1]) + 1;
    }
}
