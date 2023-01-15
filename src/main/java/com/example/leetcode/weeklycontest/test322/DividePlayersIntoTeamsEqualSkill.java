package com.example.leetcode.weeklycontest.test322;

import java.util.Arrays;

/**
 * You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player. Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.
 *
 * The chemistry of a team is equal to the product of the skills of the players on that team.
 *
 * Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into teams such that the total skill of each team is equal.
 *
 *
 *
 * Example 1:
 *
 * Input: skill = [3,2,5,1,3,4]
 * Output: 22
 * Explanation:
 * Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
 * The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.
 * Example 2:
 *
 * Input: skill = [3,4]
 * Output: 12
 * Explanation:
 * The two players form a team with a total skill of 7.
 * The chemistry of the team is 3 * 4 = 12.
 * Example 3:
 *
 * Input: skill = [1,1,2,3]
 * Output: -1
 * Explanation:
 * There is no way to divide the players into teams such that the total skill of each team is equal.
 */
public class DividePlayersIntoTeamsEqualSkill {
    public static void main(String[] args) {

    }

    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        int length = skill.length;
        int left = 0;
        int right = length - 1;
        int sum = skill[left] + skill[right];
        long total = skill[left] * skill[right];
        while (left < right) {
            left++;
            right--;

            if(left >= right)
                break;
            if(skill[left] + skill[right] != sum)
                return -1;
            total += skill[left] * skill[right];
        }
        return total;

    }
}
