package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 *
 * Note that every number in the answer must not have leading zeros except for the number 0 itself.
 * For example, 01 has one leading zero and is invalid, but 0 is valid.
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
        numbersWithSameConsecutiveDifferences.numsSameConsecDiff(3,1);
    }
    Set<Integer> res;
    public int[] numsSameConsecDiff(int N, int K) {
        res = new HashSet<>();
        if(N == 1)
            return IntStream.range(0,10).toArray();
        else{
            for (int i = 1; i <= 9; i++)
                helper( i, N - 1, K);

            return res.stream().mapToInt(e->e).toArray();
        }
    }

    void helper(int curInt, int N, int K) {
        if (N == 0) {
            res.add(curInt);
            return;
        }
        int last = curInt % 10;
        if (last + K <= 9)
            helper(curInt * 10 + last + K, N - 1, K);
        //if (last - K >= 0 && K != 0)  both are OK
        if (last - K >= 0)
            helper(curInt * 10 + last - K, N - 1, K);
    }


    /**
     * faster solution
     * @param N
     * @param K
     * @return
     */
    public int[] numsSameConsecDiffV1(int N, int K) {
        double x = 0.0;
        for(int i=1; i<N; i++) {
            x = Math.pow(10, i);
        }
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<10; i++) {
            dfs(x, K, i, res);
        }
        int[] ans = new int[res.size()];
        for(int j=0; j<res.size(); j++) {
            ans[j] = res.get(j);
        }
        return ans;
    }

    private void dfs(double x, int K, int cur, List<Integer> res) {
        if(cur >= x) {
            res.add(cur);
            return;
        }
        if(cur == 0) return;
        if(cur%10+K < 10) {
            dfs(x, K, cur*10+cur%10+K, res);
        }
        if(K != 0 && cur%10-K >= 0) {
            dfs(x, K, cur*10+cur%10-K, res);
        }
    }
}
