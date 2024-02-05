package com.example.leetcode.weeklycontest.old.test330;

/**
 * Given a 0-indexed integer array nums of size n containing all numbers from 1 to n, return the number of increasing quadruplets.
 *
 * A quadruplet (i, j, k, l) is increasing if:
 *
 * 0 <= i < j < k < l < n, and
 * nums[i] < nums[k] < nums[j] < nums[l].
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,4,5]
 * Output: 2
 * Explanation:
 * - When i = 0, j = 1, k = 2, and l = 3, nums[i] < nums[k] < nums[j] < nums[l].
 * - When i = 0, j = 1, k = 2, and l = 4, nums[i] < nums[k] < nums[j] < nums[l].
 * There are no other quadruplets, so we return 2.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Explanation: There exists only one quadruplet with i = 0, j = 1, k = 2, l = 3, but since nums[j] < nums[k], we return 0.
 */
public class CountIncreasingQuadruplets {
    public static void main(String[] args) {

    }

    /**https://leetcode.cn/problems/count-increasing-quadruplets/solution/you-ji-qiao-de-mei-ju-yu-chu-li-pythonja-exja/
     *
     * @param nums
     * @return
     */
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][] great = new int[n][n + 1];
        for (int k = n - 2; k >= 2; k--) {
            great[k] = great[k + 1].clone();
            for (int x = nums[k + 1] - 1; x > 0; x--)
                great[k][x]++; // x < nums[k+1]，对于 x，大于它的数的个数 +1
        }

        long ans = 0;
        int[] less = new int[n + 1];
        for (int j = 1; j < n - 2; j++) {
            for (int x = nums[j - 1] + 1; x <= n; x++)
                less[x]++; // x > nums[j-1]，对于 x，小于它的数的个数 +1
            for (int k = j + 1; k < n - 1; k++)
                if (nums[j] > nums[k])
                    ans += less[nums[k]] * great[k][nums[j]];
        }
        return ans;
    }
}
