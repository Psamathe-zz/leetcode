package com.example.leetcode.weeklycontest.test187;

/**
 * Given an array of integers nums and an integer limit,
 * return the size of the longest continuous subarray such that the absolute difference between any two elements is less than or equal to limit.
 *
 * In case there is no subarray satisfying the given condition return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * Example 2:
 *
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 *
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 */
public class LongestContinuousSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{8,2,4,7};
        int limit = 4;
        LongestContinuousSubarray longestContinuousSubarray = new LongestContinuousSubarray();
        int result = longestContinuousSubarray.longestSubarray(nums,limit);
        System.out.println(result);
    }

    public int longestSubarray(int[] nums, int limit) {
        int result = 0;
        int tempLength;
        int max;
        int min;
        for(int i = 0; i< nums.length - result;i++){
            tempLength = 1;
            max = nums[i];
            min = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[j] > max){
                    max = nums[j];
                }
                if(nums[j] < min){
                    min = nums[j];
                }
                if(max - min > limit)
                    break;
                else
                    tempLength++;

            }
            if(tempLength>result)
                result = tempLength;
        }

        return result;
    }
}
