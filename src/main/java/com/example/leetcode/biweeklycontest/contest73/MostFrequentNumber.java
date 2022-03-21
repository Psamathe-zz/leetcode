package com.example.leetcode.biweeklycontest.contest73;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed integer array nums. You are also given an integer key, which is present in nums.
 *
 * For every unique integer target in nums, count the number of times target immediately follows an occurrence of key in nums. In other words, count the number of indices i such that:
 *
 * 0 <= i <= n - 2,
 * nums[i] == key and,
 * nums[i + 1] == target.
 * Return the target with the maximum count. The test cases will be generated such that the target with maximum count is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,100,200,1,100], key = 1
 * Output: 100
 * Explanation: For target = 100, there are 2 occurrences at indices 1 and 4 which follow an occurrence of key.
 * No other integers follow an occurrence of key, so we return 100.
 * Example 2:
 *
 * Input: nums = [2,2,2,2,3], key = 2
 * Output: 2
 * Explanation: For target = 2, there are 3 occurrences at indices 1, 2, and 3 which follow an occurrence of key.
 * For target = 3, there is only one occurrence at index 4 which follows an occurrence of key.
 * target = 2 has the maximum number of occurrences following an occurrence of key, so we return 2.
 * [2,1000,2,1000,2,3]
 * 2
 */
public class MostFrequentNumber {
    public static void main(String[] args) {
        MostFrequentNumber mostFrequentNumber = new MostFrequentNumber();
        mostFrequentNumber.mostFrequent(new int[]{2,1000,2,1000,2,3}, 2);
    }

    public int mostFrequent(int[] nums, int key) {
        Map<Integer, Integer> count = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if(nums[i] == key) {
                count.compute(nums[i+1], (k,v) -> {
                    if(v == null)
                        return 1;
                    else
                        return v + 1;
                });
            }
        }
        int max = 0;
        int res = 0;
        for (Map.Entry<Integer,Integer> entry : count.entrySet()) {
            if(entry.getValue() > max) {
                max = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;

    }
}
