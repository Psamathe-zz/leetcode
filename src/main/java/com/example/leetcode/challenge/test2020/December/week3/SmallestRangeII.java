package com.example.leetcode.challenge.test2020.December.week3;

import java.util.Arrays;

/**
 * Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).
 *
 * After this process, we have some array B.
 *
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1], K = 0
 * Output: 0
 * Explanation: B = [1]
 * Example 2:
 *
 * Input: A = [0,10], K = 2
 * Output: 6
 * Explanation: B = [2,8]
 * Example 3:
 *
 * Input: A = [1,3,6], K = 3
 * Output: 3
 * Explanation: B = [4,6,3]
 */
public class SmallestRangeII {
    public static void main(String[] args) {

    }

    /**
     * https://zhanghuimeng.github.io/post/leetcode-910-smallest-range-ii/
     * @param A
     * @param K
     * @return
     */
    public int smallestRangeII(int[] A, int K) {
        Arrays.sort(A);
        int N = A.length;
        int res = A[N-1] - A[0];
        for (int i = 0; i < N - 1; i++) {
            res = Math.min(res, Math.max(A[N-1], A[i] + 2*K) - Math.min(A[i+1], A[0] + 2*K));
        }
        return res;
    }
}
