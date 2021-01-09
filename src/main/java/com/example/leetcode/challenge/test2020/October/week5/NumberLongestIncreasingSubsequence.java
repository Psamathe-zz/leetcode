package com.example.leetcode.challenge.test2020.October.week5;


import java.util.Arrays;

/**
 * Given an integer array nums, return the number of longest increasing subsequences.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 *
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 2000
 * -106 <= nums[i] <= 106
 */
public class NumberLongestIncreasingSubsequence {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/7603903.html
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int length = nums.length;
        int res = 0, mx = 0;
        int[]  len = new int[length];
        int[]  cnt = new int[length];
        Arrays.fill(len,1);
        Arrays.fill(cnt,1);
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] <= nums[j])
                    continue;
                if (len[i] == len[j] + 1)
                    cnt[i] += cnt[j];
                else if (len[i] < len[j] + 1) {
                    len[i] = len[j] + 1;
                    cnt[i] = cnt[j];
                }
            }
            if (mx == len[i])
                res += cnt[i];
            else if (mx < len[i]) {
                mx = len[i];
                res = cnt[i];
            }
        }
        return res;
    }
}
