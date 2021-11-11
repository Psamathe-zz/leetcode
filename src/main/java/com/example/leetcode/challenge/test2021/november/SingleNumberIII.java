package com.example.leetcode.challenge.test2021.november;

/**
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 * Example 2:
 *
 * Input: nums = [-1,0]
 * Output: [-1,0]
 * Example 3:
 *
 * Input: nums = [0,1]
 * Output: [1,0]
 */
public class SingleNumberIII {
    public static void main(String[] args) {

    }

    public int[] singleNumber(int[] nums) {
        if(nums.length <= 2)
            return nums;
        int[] result  = new int[2];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum ^= nums[i];
        }
        /**
         * 3, 5 最高一位不同是4
         * https://www.cnblogs.com/grandyang/p/4741122.html
         */
        sum = Integer.highestOneBit(sum);
        for (int i = 0; i < nums.length; i++) {
            int value = sum & nums[i];
            if((value) == 0){
                result[0] ^= nums[i];
            } else {
                result[1] ^= nums[i];
            }
        }

        return result;
    }
}
