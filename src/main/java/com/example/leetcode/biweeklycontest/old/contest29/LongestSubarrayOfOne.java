package com.example.leetcode.biweeklycontest.old.contest29;

/**
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 *
 * Return 0 if there is no such subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * Example 2:
 *
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 * Example 3:
 *
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 * Example 4:
 *
 * Input: nums = [1,1,0,0,1,1,1,0,1]
 * Output: 4
 * Example 5:
 *
 * Input: nums = [0,0,0]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * [0,1,1,1,0,1,1,0,1]
 */
public class LongestSubarrayOfOne {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,1,1,0,1,1,0,1};
        LongestSubarrayOfOne longestSubarrayOfOne = new LongestSubarrayOfOne();
        longestSubarrayOfOne.longestSubarray(nums);
    }

    public int longestSubarray(int[] nums) {
        int length = nums.length;
        int left = 0;
        int count0 = 0;
        int result = 0;

        for (int i = 0; i < length; i++) {
            if(nums[i] == 0){
                count0++;
            }
            while (count0 > 1){
                if(nums[left] == 0){
                    count0--;
                }
                left++;
            }
            result = Math.max(result,i - left);
        }
        return result;
    }
}
