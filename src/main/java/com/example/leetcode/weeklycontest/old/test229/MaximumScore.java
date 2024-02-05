package com.example.leetcode.weeklycontest.old.test229;

import java.util.Arrays;

/**
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
 *
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * Return the maximum score after performing m operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 * Example 2:
 *
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 103
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 */
public class MaximumScore {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int[] multipliers = new int[]{3,2,1};
        MaximumScore maximumScore = new MaximumScore();
        maximumScore.maximumScore(nums,multipliers);
    }

    /**
     * https://leetcode-cn.com/problems/maximum-score-from-performing-multiplication-operations/solution/dong-tai-gui-hua-er-wei-zhuang-tai-ding-slg21/
     * 一共从nums数组中选取m个数字，将其与multipliers中的m个数字对应相乘，取最大分数即可。multipliers中每一个数字对应有一头一尾两个选择，由于n >= m,那么暴力法（dfs或者bfs），可能性将达到2^1000次，超时。如何剪枝是关键。
     * 这里采用动态规划的解法，关键点在于如何定义状态。
     *
     * 解题方法
     * 定义二维数组dp[m + 1][m + 1]。dp[i][j]：表示nums数组中前i个数和后j个数组成的最大分数。
     * base case：
     * dp[0][0] = 0;
     * dp[i][0]：此状态表示nums数组中前i个数和后0个数组成的最大分数，此状态只可能由dp[i - 1][0]转移得到。
     * dp[0][j]：此状态表示nums数组中前0个数和后j个数组成的最大分数，此状态只可能由dp[0][j - 1]转移得到。
     * dp[i][j]：该状态表示nums数组中前i个数和后j个数组成的最大分数，可能由状态dp[i - 1][j]和状态dp[i][j - 1]转移得到，取其中得分最大的一个。
     * 遍历所有可能的组合（满足i + j = m）获得最大得分。
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length,m = multipliers.length;
        int[][] dp = new int[1000 + 5][1000 + 5];
        dp[0][0] = 0;
        for (int i = 1;i <= m;++i)
            dp[i][0] = dp[i - 1][0] + nums[i - 1] * multipliers[i - 1];
        for (int j = 1;j <= m;++j)
            dp[0][j] = dp[0][j - 1] + nums[n - j] * multipliers[j - 1];
        for (int i = 1;i <= m;++i){
            for (int j = 1;i + j <= m;++j){
                dp[i][j] = Math.max(dp[i - 1][j] + nums[i - 1] * multipliers[i + j - 1],dp[i][j - 1] + nums[n - j] * multipliers[i + j - 1]);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0;i <= m;++i)
            ans = Math.max(ans,dp[i][m - i]);
        return ans;
    }

    public int maximumScoreV1(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int[][] dp = new int[m+1][m+1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MIN_VALUE);
        }
        for (int i = 0; i <= m; i++) {
            dp[i][m-i] = 0;
        }
        for (int k = m-1; k >= 0; k--) {
            for (int l = 0; l <= k; l++) {
                int r = k-l;
                dp[l][r] = Math.max(
                        dp[l+1][r] + nums[l] * multipliers[k],
                        dp[l][r+1] + nums[n-r-1] * multipliers[k]
                );
            }
        }
        return dp[0][0];
    }
}
