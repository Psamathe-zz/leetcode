package com.example.leetcode.challenge.test2021.september.week5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * Example 2:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 16
 * 1 <= nums[i] <= 104
 * The frequency of each element is in the range [1, 4].
 */
public class PartitionKEqualSum {
    public static void main(String[] args) {

    }

    /**
     * TODO
     * https://www.cnblogs.com/grandyang/p/7733098.html
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num : nums) sum += num;
        if(sum % k != 0) return false;
        int S = sum / k;
        int[] subsets = new int[k];
        return dfs(nums, subsets, 0, S);
    }
    public boolean dfs(int[] nums, int[] subsets, int index, int tgt) {
        if(index == nums.length) {
            for(int i = 0; i < subsets.length; i++) {
                if(subsets[i] != tgt) return false;
            }
            return true;
        }
        boolean res = false;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < subsets.length; i++) {
            if(tgt - subsets[i] >= nums[index] && set.add(subsets[i])) {  //2个减枝条件，1:某个集合加入nums[i]后大于tgt。2:nums相同的元素仅考虑一次
                subsets[i] += nums[index];
                res |= dfs(nums, subsets, index + 1, tgt);
                subsets[i] -= nums[index];
            }
        }
        return res;
    }
}
