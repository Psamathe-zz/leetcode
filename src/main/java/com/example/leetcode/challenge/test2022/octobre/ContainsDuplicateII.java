package com.example.leetcode.challenge.test2022.octobre;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 */
public class ContainsDuplicateII {
    public static void main(String[] args) {
        ContainsDuplicateII containsDuplicateII = new ContainsDuplicateII();
        boolean res = containsDuplicateII.containsNearbyDuplicate(new int[]{1,2,3,1}, 3);
        System.out.println(res);
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int length = nums.length;
        Map<Integer, Integer> index = new HashMap<>();

        for (int i = 0; i < length; i++) {
            if(index.containsKey(nums[i]) && i - index.get(nums[i]) <= k) {
                return true;
            } else
                index.put(nums[i], i);

        }
        return false;
    }
}
