package com.example.leetcode.challenge.may.week4;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 *
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 *
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 * Example 2:
 *
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 * 1 2 3 4 5
 * 2 1 1 1 1
 * Note:
 *
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * There does not exist i != j for which dislikes[i] == dislikes[j].
 *
 * http://www.noteanddata.com/leetcode-886-Possible-Bipartition-java-solution-note.html
 */
public class PossibleBipartition {
    public static void main(String[] args) {
        int N = 4;
        int[][] dislikes = new int[][]{{1,2},{1,3},{2,4}};
        PossibleBipartition possibleBipartition = new PossibleBipartition();
        boolean result = possibleBipartition.possibleBipartitionV1(N,dislikes);
        System.out.println(result);
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

    /**
     * faster solution
     */
    public boolean possibleBipartitionV1(int N, int[][] dislikes) {
        int[] group = new int[N+1];
        for (int i = 0; i <= N; i++) {
            group[i] = i; // initial
        }

        for (int[] dislike : dislikes) {
            int a = dislike[0];
            int b = dislike[1];

            if (group[a] == a && group[b] == b) {
                group[a] = b;
                group[b] = a;
            } else if (group[a] == a && group[b] != b) {
                // let a go to group that all hate b;
                group[a] = group[group[b]];
            } else if (group[b] ==b && group[a] != a) {
                group[b] = group[group[a]];
            } else if (group[b] == group[a]) {
                return false;
            }
        }
        return true;
    }

}
