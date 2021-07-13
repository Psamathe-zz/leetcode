package com.example.leetcode.weeklycontest.test249;


/**
 * You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.
 *
 * Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 1, n = 1
 * Output: 3
 * Explanation: The three possible colorings are shown in the image above.
 * Example 2:
 *
 *
 * Input: m = 1, n = 2
 * Output: 6
 * Explanation: The six possible colorings are shown in the image above.
 * Example 3:
 *
 * Input: m = 5, n = 5
 * Output: 580986
 *
 */
public class PaintingGrid {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/painting-a-grid-with-three-different-colors/solution/javazhuang-tai-ya-suo-by-xiaoshuaila-dgd7/
     * @param m
     * @param n
     * @return
     */
    public int colorTheGrid(int m, int n) {
        int mod=1000000007;
        int len=(int)Math.pow(3,m);
        long[][]dp=new long[n][len];
        boolean[]isok=new boolean[len];
        int[][]arr=new int[len][];
        for(int i=0;i<len;i++) {
            int num=i;
            int[]temarr=new int[m];
            int sta=0;
            while(num!=0){
                temarr[sta++]=num%3;
                num/=3;
            }
            arr[i]=temarr;
            for(int j=1;j<m;j++)
                if(temarr[j]==temarr[j-1])
                {
                    dp[0][i]--;
                    isok[i]=true;
                    break;
                }
            dp[0][i]++;
        }
        for(int i=1;i<n;i++) {
            for(int j=0;j<len;j++)
            {
                if(isok[j])continue;
                long jia=dp[i-1][j];
                int num=j;
                int[]temarr=arr[j];
                for(int k=0;k<len;k++)
                {
                    if(isok[k])continue;
                    int numnum=k;
                    int[]temtemarr=arr[k];
                    for(int a=0;a<m;a++)
                    {
                        if(temtemarr[a]==temarr[a]){
                            dp[i][k]-=jia%mod;
                            break;
                        }
                    }
                    dp[i][k]+=jia%mod;
                }
            }
        }
        long sum=0;
        for(int i=0;i<len;i++)
            sum=(sum+dp[n-1][i])%mod;
        return (int)sum;
    }
}
