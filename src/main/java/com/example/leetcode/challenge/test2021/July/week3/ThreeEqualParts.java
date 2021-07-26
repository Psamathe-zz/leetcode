package com.example.leetcode.challenge.test2021.July.week3;


import java.util.Arrays;

/**
 * You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.
 *
 * If it is possible, return any [i, j] with i + 1 < j, such that:
 *
 * arr[0], arr[1], ..., arr[i] is the first part,
 * arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
 * arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
 * All three parts have equal binary values.
 * If it is not possible, return [-1, -1].
 *
 * Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3. Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,0,1,0,1]
 * Output: [0,3]
 * Example 2:
 *
 * Input: arr = [1,1,0,1,1]
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: arr = [1,1,0,0,1]
 * Output: [0,2]
 */
public class ThreeEqualParts {
    public static void main(String[] args) {

    }

    public int[] threeEqualParts(int[] A) {
        int cnt = 0;
        int n = A.length;
        for (int num : A) {
            if (num == 1) cnt ++;
        }
        if (cnt == 0) return new int[]{0, n - 1};
        if (cnt % 3 != 0) return new int[]{-1, -1};
        int k = cnt / 3;
        int start = 0;
        int mid = 0;
        int end = 0;
        cnt = 0;
        for (int i = 0; i < n; i ++) {
            if (A[i] == 0) continue;
            if (cnt == 0) start = i;
            cnt ++;
            if (cnt == k + 1) mid = i;
            if (cnt == 2 * k + 1) end = i;
        }

        while (end < n && A[start] == A[mid] && A[mid] == A[end]) {
            start ++;
            mid ++;
            end ++;
        }
        if (end == n) return new int[]{start - 1, mid};
        return new int[]{-1, -1};
    }

}
