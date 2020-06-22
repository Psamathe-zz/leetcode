package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 *
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
 * and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 *
 *
 *
 * Example 1:
 *
 * Input: [3,6,9,12]
 * Output: 4
 * Explanation:
 * The whole array is an arithmetic sequence with steps of length = 3.
 * Example 2:
 *
 * Input: [9,4,7,2,10]
 * Output: 3
 * Explanation:
 * The longest arithmetic subsequence is [4,7,10].
 * Example 3:
 *
 * Input: [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:
 * The longest arithmetic subsequence is [20,15,10,5].
 */
public class LongestArithmeticSequence {

    public static void main(String[] args) {
        int[] A = new int[]{20,1,15,3,10,5,8};
        LongestArithmeticSequence longestArithmeticSequence = new LongestArithmeticSequence();
        int result = longestArithmeticSequence.longestArithSeqLength(A);
        System.out.println(result);
    }

    public int longestArithSeqLength(int[] A) {
        int result = 0;
        Arrays.sort(A);
        int length = A.length;
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {

            }
        }
        return result;
    }

}
