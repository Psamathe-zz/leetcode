package com.example.leetcode.weeklycontest.test318;

import java.util.*;

/**
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
 *
 * The length of the subarray is k, and
 * All the elements of the subarray are distinct.
 * Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,4,2,9,9,9], k = 3
 * Output: 15
 * Explanation: The subarrays of nums with length 3 are:
 * - [1,5,4] which meets the requirements and has a sum of 10.
 * - [5,4,2] which meets the requirements and has a sum of 11.
 * - [4,2,9] which meets the requirements and has a sum of 15.
 * - [2,9,9] which does not meet the requirements because the element 9 is repeated.
 * - [9,9,9] which does not meet the requirements because the element 9 is repeated.
 * We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
 * Example 2:
 *
 * Input: nums = [4,4,4], k = 3
 * Output: 0
 * Explanation: The subarrays of nums with length 3 are:
 * - [4,4,4] which does not meet the requirements because the element 4 is repeated.
 * We return 0 because no subarrays meet the conditions.
 */
public class MaximumSumDistinctSubarrays {
    public static void main(String[] args) {
        MaximumSumDistinctSubarrays maximumSumDistinctSubarrays = new MaximumSumDistinctSubarrays();
        maximumSumDistinctSubarrays.maximumSubarraySum(new int[]{1,5,4,2,9,9,9}, 3);
    }

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long rst = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < k; i++) {
            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        if (map.size() == k) {
            rst = Math.max(rst, sum);
        }

        for (int i = k; i < n; i++) {
            // 右指针右移
            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);


            // 左指针右移
            sum -= nums[i - k];
            int cnt = map.get(nums[i - k]);
            if (cnt == 1) {
                map.remove(nums[i - k]);
            } else {
                map.put(nums[i - k], cnt - 1);
            }

            if (map.size() == k) {
                rst = Math.max(rst, sum);
            }
        }

        return rst;

    }
}
