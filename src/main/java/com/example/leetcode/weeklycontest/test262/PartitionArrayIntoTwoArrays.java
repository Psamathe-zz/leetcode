package com.example.leetcode.weeklycontest.test262;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the two arrays.
 *
 * Return the minimum possible absolute difference.
 *
 *
 *
 * Example 1:
 *
 * example-1
 * Input: nums = [3,9,7,3]
 * Output: 2
 * Explanation: One optimal partition is: [3,9] and [7,3].
 * The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
 * Example 2:
 *
 * Input: nums = [-36,36]
 * Output: 72
 * Explanation: One optimal partition is: [-36] and [36].
 * The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
 * Example 3:
 *
 * example-3
 * Input: nums = [2,-1,0,4,-2,-9]
 * Output: 0
 * Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
 * The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
 */
public class PartitionArrayIntoTwoArrays {
    public static void main(String[] args) {

    }

    /**
     *
     * https://leetcode-cn.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/solution/java-jian-dan-de-shuang-xiang-dfs-by-kon-76ac/
     */
    final int INF = 2_000_000_000;
    int[] ns;
    int n;
    ArrayList<TreeSet<Integer>> ls;
    int ans;

    private void ldfs(int idx, int sum, int cnt) {
        if (idx >= n) {
            ls.get(cnt).add(sum);
            return;
        }
        ldfs(idx + 1, sum - ns[idx], cnt);
        ldfs(idx + 1, sum + ns[idx], cnt + 1);
    }

    private void rdfs(int idx, int sum, int cnt) {
        if (idx >= 2 * n) {
            Integer x1 = ls.get(n - cnt).floor(-sum);
            Integer x2 = ls.get(n - cnt).ceiling(-sum);
            if (x1 != null) {
                ans = Math.min(ans, Math.abs(sum + x1));
            }
            if (x2 != null) {
                ans = Math.min(ans, Math.abs(sum + x2));
            }
            return;
        }
        rdfs(idx + 1, sum - ns[idx], cnt);
        rdfs(idx + 1, sum + ns[idx], cnt + 1);
    }

    public int minimumDifference(int[] _ns) {
        ns = _ns;
        n = ns.length / 2;
        ans = INF;
        ls = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            ls.add(new TreeSet<>());
        }
        Arrays.sort(ns); // 加速TreeSet内部排序
        ldfs(0, 0, 0);
        rdfs(n, 0, 0);
        return ans;
    }

}
