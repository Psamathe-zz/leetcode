package com.example.leetcode.biweeklycontest.test2024.contest126;

/**
 * You are given an integer array nums of length n and a positive integer k.
 *
 * The power of an array of integers is defined as the number of subsequences with their sum equal to k.
 *
 * Return the sum of power of all subsequences of nums.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input:  nums = [1,2,3], k = 3
 *
 * Output:  6
 *
 * Explanation:
 *
 * There are 5 subsequences of nums with non-zero power:
 *
 * The subsequence [1,2,3] has 2 subsequences with sum == 3: [1,2,3] and [1,2,3].
 * The subsequence [1,2,3] has 1 subsequence with sum == 3: [1,2,3].
 * The subsequence [1,2,3] has 1 subsequence with sum == 3: [1,2,3].
 * The subsequence [1,2,3] has 1 subsequence with sum == 3: [1,2,3].
 * The subsequence [1,2,3] has 1 subsequence with sum == 3: [1,2,3].
 * Hence the answer is 2 + 1 + 1 + 1 + 1 = 6.
 *
 * Example 2:
 *
 * Input:  nums = [2,3,3], k = 5
 *
 * Output:  4
 *
 * Explanation:
 *
 * There are 3 subsequences of nums with non-zero power:
 *
 * The subsequence [2,3,3] has 2 subsequences with sum == 5: [2,3,3] and [2,3,3].
 * The subsequence [2,3,3] has 1 subsequence with sum == 5: [2,3,3].
 * The subsequence [2,3,3] has 1 subsequence with sum == 5: [2,3,3].
 * Hence the answer is 2 + 1 + 1 = 4.
 *
 * Example 3:
 *
 * Input:  nums = [1,2,3], k = 7
 *
 * Output:  0
 *
 * Explanation: There exists no subsequence with sum 7. Hence all subsequences of nums have power = 0.
 */
public class FindTheSumOfPower {
    public static void main(String[] args) {

    }

    public int sumOfPower(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        int n = nums.length;
        int[][] f = new int[k + 1][n + 1];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= nums[i]; j--) {
                for (int c = i + 1; c > 0; c--) {
                    f[j][c] = (f[j][c] + f[j - nums[i]][c - 1]) % MOD;
                }
            }
        }

        long ans = 0;
        int pow2 = 1;
        for (int i = n; i > 0; i--) {
            ans = (ans + (long) f[k][i] * pow2) % MOD;
            pow2 = pow2 * 2 % MOD;
        }
        return (int) ans;
    }
}
