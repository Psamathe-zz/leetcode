package com.example.leetcode.weeklycontest.old.test211;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
 *
 *
 * Constraints:
 *
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 106
 * 1 <= ages[i] <= 1000
 * [9,2,8,8,2]
 * [4,1,3,3,5]
 */
public class BestTeamWithNoConflicts {
    public static void main(String[] args) {
        int[] scores = new int[]{9,2,8,8,2};
        int[] ages = new int[]{4,1,3,3,5};
        BestTeamWithNoConflicts bestTeamWithNoConflicts = new BestTeamWithNoConflicts();
        bestTeamWithNoConflicts.bestTeamScore(scores,ages);

    }

    public int bestTeamScore(int[] scores, int[] ages) {
        int length = scores.length;
        List<Player> list = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            list.add(new Player(scores[i],ages[i]));
        }
        Collections.sort(list, new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                if(player1.age != player2.age){
                    return player1.age - player2.age;
                } else {
                    return player1.score - player2.score;
                }
            }
        });
        int score;
        int temp;
        int max = 0;
        for (int i = 0; i < length; i++) {
            score = list.get(i).score;
            temp = score;
            for (int j = i + 1; j < length; j++) {
                if(list.get(j).score >= score)
                    temp += list.get(j).score;
            }
            System.out.println(temp);
            max = Math.max(max,temp);
        }

        return max;
    }

    public class Player  {
        int score;
        int age;

        public Player(int score, int age) {
            this.score = score;
            this.age = age;
        }
    }
}
