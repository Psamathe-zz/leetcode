package com.example.leetcode.weeklycontest.test248;


import java.util.HashSet;

/**
 * There is a country of n cities numbered from 0 to n - 1. In this country, there is a road connecting every pair of cities.
 *
 * There are m friends numbered from 0 to m - 1 who are traveling through the country. Each one of them will take a path consisting of some cities. Each path is represented by an integer array that contains the visited cities in order. The path may contain a city more than once, but the same city will not be listed consecutively.
 *
 * Given an integer n and a 2D integer array paths where paths[i] is an integer array representing the path of the ith friend, return the length of the longest common subpath that is shared by every friend's path, or 0 if there is no common subpath at all.
 *
 * A subpath of a path is a contiguous sequence of cities within that path.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, paths = [[0,1,2,3,4],
 *                        [2,3,4],
 *                        [4,0,1,2,3]]
 * Output: 2
 * Explanation: The longest common subpath is [2,3].
 * Example 2:
 *
 * Input: n = 3, paths = [[0],[1],[2]]
 * Output: 0
 * Explanation: There is no common subpath shared by the three paths.
 * Example 3:
 *
 * Input: n = 5, paths = [[0,1,2,3,4],
 *                        [4,3,2,1,0]]
 * Output: 1
 * Explanation: The possible longest common subpaths are [0], [1], [2], [3], and [4]. All have a length of 1.
 */
public class LongestCommonSubpath {
    public static void main(String[] args) {

    }

    int N = 100010;
    int[][] paths;
    long[] p, h;
    public int longestCommonSubpath(int n, int[][] paths) {
        this.paths = paths;
        p = new long[N];
        h = new long[N];
        int l = 0, r = N;
        for (int[] pa : paths) {
            r = Math.min(r, pa.length);
        }
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }
    public boolean check(int mid) {
        HashSet<Long> set = new HashSet<>();
        p[0] = 1;
        int k = 0;
        for (int[] pa : paths) {
            int n = pa.length;
            for (int i = 1; i <= n; ++i) {
                p[i] = p[i - 1] * 133331;
                h[i] = h[i - 1] * 133331 + pa[i - 1];
            }
            HashSet<Long> tmp = new HashSet<>();
            for (int i = mid; i <= n; ++i) {
                long t = get(i - mid + 1, i);
                if (k == 0) {
                    set.add(t);
                } else {
                    tmp.add(t);
                }
            }
            if (k != 0) set.retainAll(tmp);
            if (set.size() == 0) return false;
            k++;
        }
        return true;
    }
    public long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }

}
