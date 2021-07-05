package com.example.leetcode.biweeklycontest.contest55;


/**
 * The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of the elements at odd indices.
 *
 * For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
 * Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements of the subsequence).
 *
 * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,5,3]
 * Output: 7
 * Explanation: It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.
 * Example 2:
 *
 * Input: nums = [5,6,7,8]
 * Output: 8
 * Explanation: It is optimal to choose the subsequence [8] with alternating sum 8.
 * Example 3:
 *
 * Input: nums = [6,2,1,2,4,5]
 * Output: 10
 * Explanation: It is optimal to choose the subsequence [6,1,5] with alternating sum (6 + 5) - 1 = 10.
 *
 */
public class MaximumAlternatingSubsequenceSum {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximum-alternating-subsequence-sum/solution/dp-dong-tai-gui-hua-xiang-jie-by-hu-li-h-8ji0/
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[][] f = new long[n][2];
        f[0][0] = nums[0];
        f[0][1] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0], Math.max(nums[i], f[i - 1][1] + nums[i]));
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - nums[i]);
        }
        return f[n - 1][0];
    }
}
