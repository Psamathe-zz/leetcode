package com.example.leetcode.medium;

/**
 * Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
 *
 * Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 *
 *
 * Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.
 *
 * Example 1:
 *
 * Input: 3
 * Output: 3
 * Explanation:
 * Intitally, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 *
 *
 * Note:
 *
 * The n will be in the range [1, 1000].
 *https://www.cnblogs.com/grandyang/p/7439616.html
 */
public class TwoKeysKeyboard {
    public static void main(String[] args) {

    }

    public int minSteps(int n) {
        int[] dp = new int[n+1];
        for (int i = 2; i <= n; ++i) {
            dp[i] = i;
            for (int j = i - 1; j > 1; --j) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                }
            }
        }
        return dp[n];
    }


    /**
     * faster solution
     */
    public int minStepsV1(int n) {

        int ans = 0;
        int d = 2;
        while(n > 1) {
            while(n%d == 0){
                ans = ans+d;
                n = n/d;
            }
            d++;
        }
        return ans;
    }


    int minStepsV2(int n) {
        int res = 0;
        for (int i = 2; i <= n; ++i) {
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        return res;
    }
}
