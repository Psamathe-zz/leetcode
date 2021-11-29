package com.example.leetcode.challenge.test2021.november;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).
 *
 * Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 3, k = 5
 * Output: 3
 * Explanation: The 5th smallest number is 3.
 * Example 2:
 *
 *
 * Input: m = 2, n = 3, k = 6
 * Output: 6
 * Explanation: The 6th smallest number is 6.
 *
 */
public class KthSmallestNumber {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/8367505.html
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2, cnt = 0;
            for (int i = 1; i <= m; ++i) {
                cnt += (mid > n * i) ? n : (mid / i);
            }
            if (cnt < k)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }
}
