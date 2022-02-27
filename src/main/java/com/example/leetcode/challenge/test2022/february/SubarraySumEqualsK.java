package com.example.leetcode.challenge.test2022.february;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {

    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i=1;i<nums.length;i++) {
            sum[i] = sum[i-1] + nums[i];
        }
        int count = 0;
        for (int i=0;i<sum.length;i++) {
            if (sum[i] == k) {
                count++;
            }
            if (m.containsKey(sum[i] - k)) {
                count += m.get(sum[i] - k);
            }
            m.computeIfAbsent(sum[i], key -> 0);
            m.put(sum[i], m.get(sum[i]) + 1);
        }
        return count;
    }
}
