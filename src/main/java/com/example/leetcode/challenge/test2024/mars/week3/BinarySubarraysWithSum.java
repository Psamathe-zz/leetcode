package com.example.leetcode.challenge.test2024.mars.week3;

/**
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 *
 * A subarray is a contiguous part of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: The 4 subarrays are bolded and underlined below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * Example 2:
 *
 * Input: nums = [0,0,0,0,0], goal = 0
 * Output: 15
 */
public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        BinarySubarraysWithSum binarySubarraysWithSum = new BinarySubarraysWithSum();
        binarySubarraysWithSum.numSubarraysWithSum(new int[]{1,0,1,0,1}, 2);
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        int left = 0;
        int result = 0;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum = sum + nums[right];
            while(sum > goal && left < right) {
                sum = sum - nums[left];
                left++;
            }
            if (sum == goal) {
                result++;
                for (int mid = left; mid < right && nums[mid] ==0; mid++) {
                    result++;
                }
            }
        }
        return result;
    }
}
