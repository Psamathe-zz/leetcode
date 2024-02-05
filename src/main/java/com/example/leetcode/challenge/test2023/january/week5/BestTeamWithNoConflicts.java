package com.example.leetcode.challenge.test2023.january.week5;

import com.example.leetcode.java.java14.record.User2;

import java.util.*;

/**
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.
 *
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.
 *
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.
 *
 *
 *
 * Example 1:
 *
 * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * Output: 34
 * Explanation: You can choose all the players.
 * Example 2:
 *
 * Input: scores = [4,5,6,5], ages = [2,1,2,1]
 * Output: 16
 * Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
 * Example 3:
 *
 * Input: scores = [1,2,3,5], ages = [8,9,10,1]
 * Output: 6
 * Explanation: It is best to choose the first 3 players.
 */
public class BestTeamWithNoConflicts {
    public static void main(String[] args) {
        BestTeamWithNoConflicts bestTeamWithNoConflicts = new BestTeamWithNoConflicts();
        bestTeamWithNoConflicts.bestTeamScore(new int[]{9,2,8,8,2}, new int[]{4,1,3,3,5});
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        // corner case
        if (scores == null || scores.length <= 1) {
            return scores[0];
        }

        // normal case
        int n = scores.length;
        int[][] people = new int[n][2];
        for (int i = 0; i < n; i++) {
            people[i][0] = ages[i];
            people[i][1] = scores[i];
        }
        // 年龄一样，得分小的在前；否则年龄小的在前
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // DP定义：以 i 为结尾的一票人的得分，类似LIS
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = people[i][1];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (people[j][1] <= people[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + people[i][1]);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    public record Player(int score, int age) {
        public static Player of(int score, int age) {
            return new Player(score, age);
        }
    }
}
