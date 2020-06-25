package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class PerfectSquares {
    public static void main(String[] args) {
        int n = 13;
        PerfectSquares perfectSquares = new PerfectSquares();
        int result = perfectSquares.numSquares(n);
        System.out.println(result);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4800552.html
     * @param n
     * @return
     */

    int numSquares(int n) {
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;
        for (int a = 0; a * a <= n; ++a) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                return (a>0?1:0) + (b>0?1:0);
            }
        }
        return 3;
    }
    int numSquaresV1(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);   //dp[]数组初始化
        dp[0] = 0;
        for (int i = 0; i <= n; ++i) {
            for (int j = 1; i + j * j <= n; ++j) {
                dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);  //状态转移方程
            }
        }
        return dp[n];
    }

    int numSquaresV2(int n) {
        int res = n, num = 2;
        while (num * num <= n) {
            int a = n / (num * num), b = n % (num * num);
            res = Math.min(res, a + numSquares(b));
            ++num;
        }
        return res;
    }

    public int numSquaresOld(int n) {
        int max = (int)Math.sqrt(n);
        if(max == 1)
            return n;
        int result = Integer.MAX_VALUE;
        int count;
        int cur;
        int remain;
        for (int i = max/2; i <= max; i++) {
            remain = n;
            count = 0;
            while (remain > 0){
                cur = (int)Math.sqrt(remain);
                cur = cur > i?i:cur;
                remain -= Math.pow(cur,2);
                count++;
            }
            result = Math.min(result,count);
        }
        return result;
    }
}
