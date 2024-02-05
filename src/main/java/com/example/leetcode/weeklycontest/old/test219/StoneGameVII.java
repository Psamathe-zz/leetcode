package com.example.leetcode.weeklycontest.old.test219;


/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * There are n stones arranged in a row. On each player's turn, they can remove either the leftmost stone or the rightmost stone from the row and receive points equal to the sum of the remaining stones' values in the row. The winner is the one with the higher score when there are no stones left to remove.
 *
 * Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize the score's difference. Alice's goal is to maximize the difference in the score.
 *
 * Given an array of integers stones where stones[i] represents the value of the ith stone from the left, return the difference in Alice and Bob's score if they both play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [5,3,1,4,2]
 * Output: 6
 * Explanation:
 * - Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0, stones = [5,3,1,4].
 * - Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones = [3,1,4].
 * - Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones = [1,4].
 * - Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
 * - Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
 * The score difference is 18 - 12 = 6.
 * Example 2:
 *
 * Input: stones = [7,90,5,1,100,10,10,2]
 * Output: 122
 *
 */
public class StoneGameVII {
    public static void main(String[] args) {

    }

    /**
     * http://shangdixinxi.com/detail-1580335.html
     * @param stones
     * @return
     */
    public int stoneGameVII(int[] stones) {

        int n = stones.length;
        int[][] dp = new int[n][n];

        int[] pre = new int[n + 1]; // 前缀和数组
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + stones[i];
        }

        for (int i = 0; i < n; i++) {
            dp[i][i] = 0; // 当只有一个石子时，AB差值为0
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                // [0, j] - [0, i] = (i, j]     dp[i + 1][j]
                // [0, j - 1] - [0, i - 1] = [i, j)     dp[i][j - 1]
                dp[i][j] = Math.max(pre[j + 1] - pre[i + 1] - dp[i + 1][j], pre[j] - pre[i] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }
}
