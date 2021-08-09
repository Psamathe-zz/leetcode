package com.example.leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 *
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
 *
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 *
 *
 *
 * Example 1:
 *
 * Input: [5,3,4,5]
 * Output: true
 * Explanation:
 * Alex starts first, and can only take the first 5 or the last 5.
 * Say he takes the first 5, so that the row becomes [3, 4, 5].
 * If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
 * If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 */
public class StoneGame {
    public static void main(String[] args) {
        int[] piles = new int[]{1,3,4,5};
        StoneGame stoneGame = new StoneGame();
        boolean result = stoneGame.stoneGame(piles);
        System.out.println(result);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/10828725.html
     *
     * https://www.cnblogs.com/liaohuiqiang/p/9747678.html
     *
     * @param piles
     * @return
     */

    public boolean stoneGame(int[] piles) {
        boolean result = help(piles, 0, 0, 0, (int)piles.length, 0);
        return result;
    }
    boolean help(int[] piles, int cur0, int cur1, int left, int right, int player){
        if (left > right)
            return cur0 > cur1;
        if (player == 0) {
            boolean b1 =  help(piles, cur0 + piles[left], cur1, left + 1, right, 1);
            boolean b2 =  help(piles, cur0 + piles[right], cur1, left + 1, right, 1);
            return b1 || b2;
        } else {
            boolean b1 =  help(piles, cur0, cur1 + piles[left], left, right - 1, 0);
            boolean b2 =  help(piles, cur0, cur1 + piles[right], left, right - 1, 0);
            return b1 || b2;
        }
    }

    public boolean stoneGameV1(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i)
            dp[i][i] = piles[i];
        for (int len = 1; len < n; ++len) {
            for (int i = 0; i < n - len; ++i) {
                int j = i + len;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }

    public boolean stoneGameV2(int[] piles) {
        if(piles.length == 1) return true;  //  Corner case with only one pile

        Queue<Integer> pileQ = new PriorityQueue<>((a, b) -> b - a);

        int aSum = 0;
        int bSum = 0;

        for(int pile : piles){
            pileQ.offer(pile);
        }

        while(!pileQ.isEmpty()){
            if(pileQ.peek() == null) {
                return false;
            }else{
                aSum += pileQ.poll();
            }

            if(pileQ.peek() == null) {
                return true;
            }else{
                bSum += pileQ.poll();
            }
        }

        return aSum > bSum;
    }
}
