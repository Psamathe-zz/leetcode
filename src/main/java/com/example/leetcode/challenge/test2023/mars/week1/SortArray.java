package com.example.leetcode.challenge.test2023.mars.week1;

/**
 * Given an array of integers nums, sort the array in ascending order and return it.
 *
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
 * Example 2:
 *
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * Explanation: Note that the values of nums are not necessairly unique.
 */
public class SortArray {
    public static void main(String[] args) {

    }

    public int[] sortArray(int[] nums) {
        int length = nums.length;
        int temp;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if(nums[j] < nums[i] ){
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }
}
