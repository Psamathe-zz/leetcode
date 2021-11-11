package com.example.leetcode.challenge.test2021.octobre;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 *
 * Input: nums = []
 * Output: []
 * Example 3:
 *
 * Input: nums = [0]
 * Output: []
 * [0,0,0,0]
 */
public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        threeSum.threeSum(new int[]{-1,0,1,2,-1,-4});
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list;
        Arrays.sort(nums);
        int length = nums.length;
        int target;
        for (int i = 0; i < length - 2; i++) {
            if(nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            target = 0 - nums[i];
            int j = i + 1, k = length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    while (j < k && nums[j] == nums[j + 1])
                        j++;
                    while (j < k && nums[k] == nums[k - 1])
                        k--;
                    j++;
                    k--;
                } else if (nums[j] + nums[k] < target)
                    j++;
                else
                    k--;
            }
        }
        return res;
    }
}
