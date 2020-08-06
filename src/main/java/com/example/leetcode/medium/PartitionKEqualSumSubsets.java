package com.example.leetcode.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 *
 * Note:
 *
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */
public class PartitionKEqualSumSubsets {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        PartitionKEqualSumSubsets partitionKEqualSumSubsets = new PartitionKEqualSumSubsets();
        boolean res = partitionKEqualSumSubsets.canPartitionKSubsets(nums,k);
        System.out.println(res);
    }

    /**
     * TODO
     * https://www.cnblogs.com/grandyang/p/7733098.html
     * @param nums
     * @param k
     * @return
     */
    int[] v;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0)
            return  false;
        int div = sum / k;
        Arrays.sort(nums);
        v = new int[nums.length];
        return helper(nums, div,  nums.length - 1);
    }

    boolean helper(int[] nums, int target, int idx) {
         if (idx == -1) {
            for (int t : v) {
                if (t != target)
                    return false;
            }
            return true;
        }
        int num = nums[idx];
        for (int i = 0; i < v.length; ++i) {
            if (v[i] + num > target) continue;
            v[i] += num;
            if (helper(nums, target, idx - 1))
                return true;
            v[i] -= num;
        }
        return false;
    }
}
