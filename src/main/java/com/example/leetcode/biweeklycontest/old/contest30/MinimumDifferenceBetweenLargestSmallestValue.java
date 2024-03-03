package com.example.leetcode.biweeklycontest.old.contest30;

import java.util.Arrays;

/**
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 *
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 * 2 3 4 5
 *
 * Example 2:
 *
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 * Example 3:
 *
 * Input: nums = [6,6,0,1,1,4,6]
 * Output: 2
 * Example 4:
 * 0 1 1 4 6 6 6
 *
 * Input: nums = [1,5,6,14,15]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * [82,81,95,75,20]
 */
public class MinimumDifferenceBetweenLargestSmallestValue {
    public static void main(String[] args) {
        int[] nums = new int[]{6,6,0,1,1,4,6};
        MinimumDifferenceBetweenLargestSmallestValue minimumDifferenceBetweenLargestSmallestValue = new MinimumDifferenceBetweenLargestSmallestValue();
        int res = minimumDifferenceBetweenLargestSmallestValue.minDifference(nums);
        System.out.println(res);
    }

    public int minDifference(int[] nums) {
        int length = nums.length;
        if(length <= 4)
            return 0;
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        int max;
        int min;
        for (int i = 0; i <= 3 ; i++) {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int j = 0; j < length - 3 ; j++) {
                max = Math.max(max,nums[i+j]);
                min = Math.min(min,nums[i+j]);
            }
            res = Math.min(res,max - min);
        }
        return res;
    }

}
