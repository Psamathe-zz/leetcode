package com.example.leetcode.challenge.test2020.August.week3;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 *
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * Example 2:
 *
 * Input: N = 2, K = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 *
 * Note:
 *
 * 1 <= N <= 9
 * 0 <= K <= 9
 */
public class NumbersWithSameConsecutiveDifferences {
    public static void main(String[] args) {
        NumbersWithSameConsecutiveDifferences numbersWithSameConsecutiveDifferences = new NumbersWithSameConsecutiveDifferences();
        numbersWithSameConsecutiveDifferences.numsSameConsecDiff(2,1);
    }
    List<Integer> res = new ArrayList<>();
    public int[] numsSameConsecDiff(int N, int K) {
        StringBuilder stringBuilder = new StringBuilder();
        if(N == 1)
            return IntStream.range(0,9).toArray();
        for (int i = 1; i <= 9 ; i++) {
            if(i + K <= 9 || i - K >= 0) {
                stringBuilder.append(i);
                helper(stringBuilder, i, 1, N, K);
                stringBuilder.setLength(0);
            }
        }
        return res.stream().mapToInt(e->e).toArray();
    }

    public void helper(StringBuilder stringBuilder,int current, int currentLength, int N, int K){
        if(currentLength == N){
            res.add(Integer.valueOf(stringBuilder.toString()));
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if(Math.abs(i - current) == K) {
                stringBuilder.append(i);
                helper(stringBuilder, i, currentLength + 1, N, K);
                stringBuilder.setLength(currentLength);
            }
        }
    }
}
