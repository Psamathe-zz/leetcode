package com.example.leetcode.sometest;


/**
 * 2, 5, 10
 */
public class Coins {
    public static void main(String[] args) {
        Coins coins = new Coins();
        coins.getCoins(16);
    }

    class Coin{
        int coin2;
        int coin5;
        int coin10;
        int count;
    }
    public int[] getCoins(int s){
        int n=3;
        int max=Integer.MAX_VALUE;
        Coin[][] dp=new Coin[n][s+1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < s+1; j++) {
                dp[i][j] = new Coin();
            }
        }
        for(int j=1;j<=s;j++) {
            dp[0][j].count=max;
            if(j-10>=0 && dp[0][j-10].count!=max) {
                dp[0][j].count=dp[0][j-10].count+1;
                dp[0][j].coin10=dp[0][j-10].coin10+1;
            }
        }

        int left=0;
        for(int i=1;i<=s;i++) {
            left=max;
            if(i-5>=0 && dp[1][i-5].count!=max) {
                left=dp[1][i-5].count+1;
            }
            if(left < dp[0][i].count){
                dp[1][i].count = left ;
                dp[1][i].coin5 = dp[1][i-5].coin5+1 ;
                dp[1][i].coin10 = dp[1][i-5].coin10;
            } else {
                dp[1][i].count = dp[0][i].count;
                dp[1][i].coin5 = dp[0][i].coin5;
                dp[1][i].coin10 = dp[0][i].coin10;
            }
        }
        for(int i=1;i<=s;i++) {
            left=max;
            if(i-2>=0 && dp[2][i-2].count!=max) {
                left=dp[2][i-2].count+1;
            }
            if(left < dp[1][i].count){
                dp[2][i].count = left;
                dp[2][i].coin2 = dp[2][i-2].coin2+1;
                dp[2][i].coin10 = dp[2][i-2].coin10;
                dp[2][i].coin5 = dp[2][i-2].coin5;
            } else {
                dp[2][i].count = dp[1][i].count;
                dp[2][i].coin2 = dp[1][i].coin2;
                dp[2][i].coin5 = dp[1][i].coin5;
                dp[2][i].coin10 = dp[1][i].coin10;
            }
        }
        return new int[]{};
    }
}
