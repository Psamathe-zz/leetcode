package com.example.leetcode.challenge.test2020.June.week5;

public class UniquePaths {
    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        int result = uniquePaths.uniquePaths(3,2);
        System.out.println(result);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4353555.html
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j ==0){
                    dp[i][j] = 1;
                } else if(i == 0){
                    dp[i][j] = dp[i][j-1];
                } else if(j == 0){
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    int uniquePathsV1(int m, int n) {
        double num = 1, denom = 1;
        int small = m > n ? n : m;
        for (int i = 1; i <= small - 1; ++i) {
            num *= m + n - 1 - i;
            denom *= i;
        }
        return (int)(num / denom);
    }



}
