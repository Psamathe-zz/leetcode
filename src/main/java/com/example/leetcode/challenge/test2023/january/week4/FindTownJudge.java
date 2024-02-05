package com.example.leetcode.challenge.test2023.january.week4;

/**
 * In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 *
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
 *
 * Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2, trust = [[1,2]]
 * Output: 2
 * Example 2:
 *
 * Input: n = 3, trust = [[1,3],[2,3]]
 * Output: 3
 * Example 3:
 *
 * Input: n = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 *
 */
public class FindTownJudge {
    public static void main(String[] args) {

    }

    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n];
        boolean[] trustSomeElse = new boolean[n];
        for(int[] t : trust) {
            count[t[1] - 1]++;
            trustSomeElse[t[0] - 1] = true;
        }

        for (int i = 0; i < n; i++) {
            if(count[i] == n - 1 && !trustSomeElse[i]) {
                return i + 1;
            }
        }
        return -1;
    }
}
