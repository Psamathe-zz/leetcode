package com.example.leetcode.easy;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * Constraints:
 *
 * 1 <= n <= 45
 */
public class ClimbingStairs {
    public static void main(String[] args) {

    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n ; i++) {
            dp[i] = dp[i-1];
            if(i >= 2)
                dp[i] +=  dp[i-2];
        }
        return dp[n];
    }

    /**
     * less memory
     * @param n
     * @return
     */
    public int climbStairsV1(int n) {
        if(n == 1)
            return 1;
        int first = 1;
        int second = 2;
        for(int i=3; i<=n; i++)
        {
            int latest = first + second;
            first = second;
            second = latest;
        }
        return second;
    }
}
