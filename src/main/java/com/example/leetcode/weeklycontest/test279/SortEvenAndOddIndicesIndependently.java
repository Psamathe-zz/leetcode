package com.example.leetcode.weeklycontest.test279;


/**
 * You are given a 0-indexed integer array nums. Rearrange the values of nums according to the following rules:
 *
 * Sort the values at odd indices of nums in non-increasing order.
 * For example, if nums = [4,1,2,3] before this step, it becomes [4,3,2,1] after. The values at odd indices 1 and 3 are sorted in non-increasing order.
 * Sort the values at even indices of nums in non-decreasing order.
 * For example, if nums = [4,1,2,3] before this step, it becomes [2,1,4,3] after. The values at even indices 0 and 2 are sorted in non-decreasing order.
 * Return the array formed after rearranging the values of nums.
 */
public class SortEvenAndOddIndicesIndependently {
    public static void main(String[] args) {

    }

    public int[] sortEvenOdd(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = i + 2; j < nums.length; j += 2) {
                if(nums[j] < nums[i]) {
                    temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }

        for (int i = 1; i < nums.length; i += 2) {
            for (int j = i + 2; j < nums.length; j += 2) {
                if(nums[j] > nums[i]) {
                    temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }

        return nums;
    }
}
