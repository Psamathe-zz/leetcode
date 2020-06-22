package com.example.leetcode.weeklycontest.test187;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.
 *
 * You are allowed to choose exactly 1 element from each row to form an array.
 * Return the Kth smallest array sum among all possible arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 5
 * Output: 7
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
 * Example 2:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 9
 * Output: 17
 * Example 3:
 *
 * Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * Output: 9
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
 * Example 4:
 *
 * Input: mat = [[1,1,10],[2,2,9]], k = 7
 * Output: 12
 */
public class FindKthSmallestSum {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1,10,10},
                {1,4,5},
                {2,3,6}
        };
        int k = 7;
        FindKthSmallestSum findKthSmallestSum = new FindKthSmallestSum();
        int result = findKthSmallestSum.kthSmallest(mat,k);
        System.out.println(result);
    }

    public int kthSmallest(int[][] mat, int k) {
        Set<List<Integer>> mySet = new HashSet<>();
        int result = 0;
        int u = mat.length;
        int v = mat[0].length;
        if(k>= u * v){
            for(int i = 0;i<u;i++){
                result += mat[i][v-1];
            }
            return result;
        }
        int[] index = new int[u];
        int times = 1;
        int temp;
        int next = 0;
        boolean isStable = false;
        while (times<k && !isStable) {
            isStable = true;
            temp = Integer.MAX_VALUE;
            for (int i = 0; i < u; i++) {
                if (index[i] < v - 1 && mat[i][index[i] + 1] - mat[i][0] <= temp) {
                    temp = mat[i][index[i] + 1] - mat[i][0];
                    next = i;
                    isStable = false;
                }
            }
            if(!isStable) {
                index[next]++;
                times++;
            }

        }
        for(int i = 0;i<u;i++){
            result += mat[i][index[i]-1];
        }
        return result;
    }
}
