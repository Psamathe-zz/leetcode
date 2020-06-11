package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        int result = longestIncreasingSubsequence.lengthOfLISV1(nums);
        System.out.println(result);
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    public int lengthOfLISV1(int[] nums) {
        List<Integer> dp = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int left = 0, right = dp.size();
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (dp.get(mid) < nums[i])
                    left = mid + 1;
                else
                    right = mid;
            }
            if (right >= dp.size())
                dp.add(nums[i]);
            else
                dp.set(right,nums[i]);
        }
        return dp.size();

    }
}
