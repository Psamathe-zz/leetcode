package com.example.leetcode.challenge.test2023.january.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 *
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
 * It can be proven that there is no longer path that satisfies the conditions.
 * Example 2:
 *
 *
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 */
public class LongestPathWithDifferentAdjacent {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/longest-path-with-different-adjacent-characters/solution/by-endlesscheng-92fw/
     */
    List<Integer>[] g;
    String s;
    int ans;

    public int longestPath(int[] parent, String s) {
        this.s = s;
        var n = parent.length;
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var i = 1; i < n; i++) g[parent[i]].add(i);

        dfs(0);
        return ans + 1;
    }

    int dfs(int x) {
        var maxLen = 0;
        for (var y : g[x]) {
            var len = dfs(y) + 1;
            if (s.charAt(y) != s.charAt(x)) {
                ans = Math.max(ans, maxLen + len);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
}
