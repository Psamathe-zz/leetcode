package com.example.leetcode.challenge.test2021.January.week2;


import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
 *
 * Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 * Example 2:
 *
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 * Example 3:
 *
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * 1 <= x <= 109
 */
public class MinimumOperations {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,20,1,1,3};
        int x = 10;
        MinimumOperations minimumOperations = new MinimumOperations();
        minimumOperations.minOperations(nums,x);
    }

    public int minOperations(int[] nums, int x) {
        int length = nums.length;
        int[] left = new int[length + 1];
        int[] right = new int[length + 1];

        for (int i = 0; i < length; i++) {
            left[i + 1] += left[i] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        int idx = 1;
        for (int i = length - 1; i >= 0; i--) {
            right[idx] += right[idx - 1] + nums[i];
            map.put(right[idx],idx);
            idx++;
        }

        if (left[length] < x || right[length] < x) return -1;//避免多次计算
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < length + 1; i++) {
            int pre = left[i];
            if (map.containsKey(x - pre)) {
                res = Math.min(res, map.get(x-pre)+i);
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
