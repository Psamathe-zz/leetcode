package com.example.leetcode.challenge.test2021.november;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 *
 * answer[i] % answer[j] == 0, or
 * answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,2]
 * Explanation: [1,3] is also accepted.
 * Example 2:
 *
 * Input: nums = [1,2,4,8]
 * Output: [1,2,4,8]
 */
public class LargestDivisibleSubset {
    public static void main(String[] args) {

    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        if (nums.length == 0)
            return ans;
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n], index = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(index, -1);
        int max_index = 0, max_dp = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1 ; j >= 0 ; j--) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    index[i] = j;
                }
            }
            if (max_dp < dp[i]) {
                max_dp = dp[i];
                max_index = i;
            }
        }
        for (int i = max_index; i != -1; i = index[i])
            ans.add(nums[i]);
        return ans;
    }
}
