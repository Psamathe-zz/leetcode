package com.example.leetcode.sometest;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int arraydemo[]= {10,5,2};
        int aim=16;

        int answer= exchangeWays(arraydemo,aim);
        System.out.println("# ANSWER= "+answer);

    }
    private static int exchangeWays(int[] arraydemo, int aim) {
        // TODO Auto-generated method stub

        if(arraydemo==null || arraydemo.length==0 || aim<0) {
            return -1;
        }

        int n=arraydemo.length;
        int max=Integer.MAX_VALUE;
        int[][] dp=new int[n][aim+1];
        for(int j=1;j<=aim;j++) {
            dp[0][j]=max;
            if(j-arraydemo[0]>=0 && dp[0][j-arraydemo[0]]!=max) {
                dp[0][j]=dp[0][j-arraydemo[0]]+1;
            }
        }

        int left=0;
        for(int i=1;i<n;i++) {
            for(int j=1;j<=aim;j++) {
                left=max;
                if(j-arraydemo[i]>=0 && dp[i][j-arraydemo[i]]!=max) {
                    left=dp[i][j-arraydemo[i]]+1;
                }
                dp[i][j]=Math.min(left, dp[i-1][j]);
            }
        }


        return dp[n-1][aim] != max? dp[n-1][aim]:-1;
    }

}
