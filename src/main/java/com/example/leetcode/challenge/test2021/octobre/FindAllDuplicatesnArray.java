package com.example.leetcode.challenge.test2021.octobre;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.
 *
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 * Example 2:
 *
 * Input: nums = [1,1,2]
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: []
 *
 */
public class FindAllDuplicatesnArray {
    public static void main(String[] args) {
        FindAllDuplicatesnArray findAllDuplicatesnArray = new FindAllDuplicatesnArray();
        findAllDuplicatesnArray.findDuplicates(new int[]{4,3,2,7,8,2,3,1});
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] < 0)
                res.add(idx + 1);
            nums[idx] = - nums[idx];
        }

        return res;
    }
}
