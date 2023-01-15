package com.example.leetcode.challenge.test2022.december;


import java.util.ArrayList;
import java.util.List;

/**
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.
 *
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4] and group2 [2,3].
 * Example 2:
 *
 * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 */
public class PossibleBipartition {
    public static void main(String[] args) {

    }

    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<List<Integer>> allList = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            allList.add(new ArrayList<>());
        }
        for(int i = 0; i < dislikes.length; ++i) {
            int a = dislikes[i][0]-1, b = dislikes[i][1]-1;
            allList.get(a).add(b);
            allList.get(b).add(a);
        }

        int[] groupTable = new int[N];
        for(int i = 0; i < N; ++i) {
            if(groupTable[i] != 0)
                continue;  // it's already set
            if(!dfs(groupTable, allList, i, 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int[] groupTable, List<List<Integer>> allList, int i, int groupId) {
        if(groupTable[i] != 0) {
            return groupTable[i] == groupId;
        }

        groupTable[i] = groupId;
        List<Integer> dislikeList = allList.get(i);

        for(int dislike: allList.get(i)) {
            if(!dfs(groupTable, allList, dislike, (groupId == 1) ? 2 : 1)) {
                return false;
            }
        }
        return true;
    }
}
