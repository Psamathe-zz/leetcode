package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :
 *
 * p[0] = start
 * p[i] and p[i+1] differ by only one bit in their binary representation.
 * p[0] and p[2^n -1] must also differ by only one bit in their binary representation.
 *
 *
 * Example 1:
 *
 * Input: n = 2, start = 3
 * Output: [3,2,0,1]
 * Explanation: The binary representation of the permutation is (11,10,00,01).
 * All the adjacent element differ by one bit. Another valid permutation is [3,1,0,2]
 * Example 2:
 *
 * Input: n = 3, start = 2
 * Output: [2,6,7,5,4,0,1,3]
 * Explanation: The binary representation of the permutation is (010,110,111,101,100,000,001,011).
 *
 *
 * Constraints:
 *
 * 1 <= n <= 16
 * 0 <= start < 2 ^ n
 */
public class CircularPermutation {
    public static void main(String[] args) {
        int n = 2;
        int start = 9100;

        long startTime = System.currentTimeMillis();
        CircularPermutation circularPermutation = new CircularPermutation();
        List<Integer> result = circularPermutation.circularPermutation(n,start);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(result);

    }

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> result = new ArrayList<>();
        int[] count = new int[(int) Math.pow(2, n)];
        if(start >= Math.pow(2,n) || start < 0)
            return result;
        result.add(start);
        count[start] = 1;
        int xor = 1;
        int temp = start;
        while (result.size() != Math.pow(2,n)){
            temp ^= xor;
            if(count[temp] == 0){
                result.add(temp);
                count[temp] = 1;
                xor = 1;
            } else {
                temp ^= xor;
                xor <<= 1;
            }
        }
        return result;
    }


    public List<Integer> circularPermutationV2(int n, int start) {
        int[] count = new int[(int) Math.pow(2, n)];
        List<Integer> res = new ArrayList<>(count.length);
        res.add(start);
        count[start] = 1;

        for (int i = 1; i < count.length; i++) {
            start = next(start, count);
            res.add(start);
        }
        return res;
    }

    public int next(int a, int[] count) {
        int xor = 1;
        while (true) {
            a ^= xor;
            if (count[a] == 0) {
                count[a]=1;
                return a;
            }
            a ^= xor;
            xor <<= 1;
        }

    }
}
