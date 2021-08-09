package com.example.leetcode.challenge.test2021.august.week1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSum {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if(nums[i] + nums[j] == target)
                    return new int[]{i, j};
            }
        }
        return new int[]{0,0};
    }

    /**
     * fater solution
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumV1(int[] nums, int target) {
        Map<Integer, Integer> a = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (a.containsKey(target - nums[i])) {
                return new int[]{a.get(target - nums[i]), i};
            }
            a.put(nums[i], i);
        }
        return null;
    }
}
