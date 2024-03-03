package com.example.leetcode.biweeklycontest.old.contest55;


import java.util.Arrays;

/**
 * Given a 0-indexed integer array nums, return true if it can be made strictly increasing after removing exactly one element, or false otherwise. If the array is already strictly increasing, return true.
 *
 * The array nums is strictly increasing if nums[i - 1] < nums[i] for each index (1 <= i < nums.length).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,10,5,7]
 * Output: true
 * Explanation: By removing 10 at index 2 from nums, it becomes [1,2,5,7].
 * [1,2,5,7] is strictly increasing, so return true.
 * Example 2:
 *
 * Input: nums = [2,3,1,2]
 * Output: false
 * Explanation:
 * [3,1,2] is the result of removing the element at index 0.
 * [2,1,2] is the result of removing the element at index 1.
 * [2,3,2] is the result of removing the element at index 2.
 * [2,3,1] is the result of removing the element at index 3.
 * No resulting array is strictly increasing, so return false.
 * Example 3:
 *
 * Input: nums = [1,1,1]
 * Output: false
 * Explanation: The result of removing any element is [1,1].
 * [1,1] is not strictly increasing, so return false.
 * Example 4:
 *
 * Input: nums = [1,2,3]
 * Output: true
 * Explanation: [1,2,3] is already strictly increasing, so return true.
 *
 */
public class RemoveOneElement {
    public static void main(String[] args) {
        RemoveOneElement removeOneElement = new RemoveOneElement();
        boolean res = removeOneElement.canBeIncreasing(new int[]{1,1,1});
        System.out.println(res);
    }

    public boolean canBeIncreasing(int[] nums) {
        int length = nums.length;
        int cur;
        int count;
        for (int i = 0; i < length; i++) {
            cur = 0;
            count = 0;
            for (int j = 0; j < length; j++) {
                if (i == j)
                    continue;
                if (nums[j] <= cur) {
                    count++;
                }
                cur = nums[j];
            }
            if (count == 0)
                return true;
        }
        return false;
    }
}
