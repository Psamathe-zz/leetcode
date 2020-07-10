package com.example.leetcode.hard;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 *
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 *
 * If there is no way to make arr1 strictly increasing, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * Output: 1
 * Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
 * Example 2:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * Output: 2
 * Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
 * Example 3:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * Output: -1
 * Explanation: You can't make arr1 strictly increasing.
 *
 *
 * Constraints:
 *
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 */
public class MakeArrayStrictlyIncreasing {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,5,3,6,7};
        int[] arr2 = new int[]{4,3,1};
        MakeArrayStrictlyIncreasing makeArrayStrictlyIncreasing = new MakeArrayStrictlyIncreasing();
        makeArrayStrictlyIncreasing.makeArrayIncreasing(arr1,arr2);
    }

    /**
     * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1187-make-array-strictly-increasing/
     * https://www.youtube.com/watch?v=8ttxdMCU2GE
     * @param arr1
     * @param arr2
     * @return
     */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int kInf = 1000000000;
        int m = arr1.length;
        // Sort b and make it only containing unique numbers.
        arr2 = Arrays.stream(arr2).distinct().sorted().toArray();
        int n = arr2.length;
        // min steps to make a[0~i] valid by keeping a[i]
        int[] keep = new int[m];
        Arrays.fill(keep,kInf);
        keep[0] = 0;
        // swap[i][j] := min steps to make a[0~i] valid by a[i] = b[j]
        int[] swap = new int[n];
        Arrays.fill(swap,1);

        for (int i = 1; i < m; ++i) {
            int min_keep = kInf;
            int min_swap = kInf;
            int[] temp = new int[n];
            Arrays.fill(temp,kInf);
            for (int j = 0; j < n; ++j) {
                if (j > 0)
                    min_swap = Math.min(min_swap, swap[j - 1] + 1);
                if (arr1[i] > arr2[j])
                    min_keep = Math.min(min_keep, swap[j]);
                if (arr1[i] > arr1[i - 1])
                    keep[i] = keep[i - 1];
                if (arr2[j] > arr1[i - 1])
                    temp[j] = keep[i - 1] + 1;
                temp[j] = Math.min(temp[j], min_swap);
                keep[i] = Math.min(keep[i], min_keep);
            }
            swap = temp;
        }

        int s = Arrays.stream(swap).min().orElse(Integer.MAX_VALUE);
        int k = keep[n];
        int ans = Math.min(s, k);
        return ans >= kInf ? -1 : ans;
    }
}
