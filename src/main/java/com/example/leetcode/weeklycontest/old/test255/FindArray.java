package com.example.leetcode.weeklycontest.old.test255;

import java.util.TreeMap;

/**
 * You are given an integer n representing the length of an unknown array that you are trying to recover. You are also given an array sums containing the values of all 2n subset sums of the unknown array (in no particular order).
 *
 * Return the array ans of length n representing the unknown array. If multiple answers exist, return any of them.
 *
 * An array sub is a subset of an array arr if sub can be obtained from arr by deleting some (possibly zero or all) elements of arr. The sum of the elements in sub is one possible subset sum of arr. The sum of an empty array is considered to be 0.
 *
 * Note: Test cases are generated such that there will always be at least one correct answer.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, sums = [-3,-2,-1,0,0,1,2,3]
 * Output: [1,2,-3]
 * Explanation: [1,2,-3] is able to achieve the given subset sums:
 * - []: sum is 0
 * - [1]: sum is 1
 * - [2]: sum is 2
 * - [1,2]: sum is 3
 * - [-3]: sum is -3
 * - [1,-3]: sum is -2
 * - [2,-3]: sum is -1
 * - [1,2,-3]: sum is 0
 * Note that any permutation of [1,2,-3] and also any permutation of [-1,-2,3] will also be accepted.
 * Example 2:
 *
 * Input: n = 2, sums = [0,0,0,0]
 * Output: [0,0]
 * Explanation: The only correct answer is [0,0].
 * Example 3:
 *
 * Input: n = 4, sums = [0,0,5,5,4,-1,4,9,9,-1,4,3,4,8,3,8]
 * Output: [0,-1,4,5]
 * Explanation: [0,-1,4,5] is able to achieve the given subset sums.
 */
public class FindArray {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/find-array-given-subset-sums/solution/javaban-ben-by-jackie-tien-e4n4/
     * @param n
     * @param sums
     * @return
     */
    public int[] recoverArray(int n, int[] sums) {
        int bias = 0;
        for (int sum : sums) {
            bias = Math.min(bias, sum);
        }
        bias = -bias;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int sum : sums) {
            treeMap.merge(sum + bias, 1, Integer::sum);
        }
        int[] ans = new int[n];
        int first = treeMap.firstKey();
        treeMap.put(first, treeMap.get(first) - 1);
        if (treeMap.get(first) == 0) {
            treeMap.remove(first);
        }
        ans[0] = treeMap.firstKey();
        for (int i = 1; i < n; i++) {
            for (int mask = 0; mask < (1 << i); mask++) {
                if (((mask >>> (i - 1)) & 1) != 0) {
                    int sum = 0;
                    for (int j = 0; j < i; j++) {
                        if (((mask >>> j) & 1) != 0) {
                            sum += ans[j];
                        }
                    }
                    treeMap.put(sum, treeMap.get(sum) - 1);
                    if (treeMap.get(sum) == 0) {
                        treeMap.remove(sum);
                    }
                }
            }
            ans[i] = treeMap.firstKey();
        }

        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (((i >>> j) & 1) != 0) {
                    sum += ans[j];
                }
            }
            if (sum == bias) {
                for (int j = 0; j < n; j++) {
                    if (((i >>> j) & 1) != 0) {
                        ans[j] = -ans[j];
                    }
                }
                break;
            }
        }
        return ans;
    }
}
