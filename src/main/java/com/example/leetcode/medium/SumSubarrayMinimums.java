package com.example.leetcode.medium;

import java.util.Stack;

/**
 * Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 *
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,1,2,4]
 * Output: 17
 * Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 30000
 * 1 <= A[i] <= 30000
 *
 */
public class SumSubarrayMinimums {
    public static void main(String[] args) {
        int[] A = new int[]{3,1,2,4};
        SumSubarrayMinimums sumSubarrayMinimums = new SumSubarrayMinimums();
        sumSubarrayMinimums.sumSubarrayMins(A);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/11273330.html
     * @param A
     * @return
     */
    public int sumSubarrayMins(int[] A) {
        int res = 0;
        int module = 1000000007;
        int n = A.length;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i <= n; ++i) {
            int cur = (i == n) ? 0 : A[i];
            while (!st.empty() && cur < A[st.peek()]) {
                int idx = st.pop();
                int left = idx - (st.empty() ? -1 : st.peek());
                int right = i - idx;
                res = (res + A[idx] * left * right) % module;
            }
            st.push(i);
        }
        return res;
    }
}
