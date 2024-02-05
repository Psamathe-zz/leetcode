package com.example.leetcode.weeklycontest.old.test247;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given an m x n integer matrix grid​​​, where m and n are both even integers, and an integer k.
 *
 * The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:
 *
 *
 *
 * A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:
 *
 *
 * Return the matrix after applying k cyclic rotations to it.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[40,10],[30,20]], k = 1
 * Output: [[10,20],[40,30]]
 * Explanation: The figures above represent the grid at every state.
 * Example 2:
 *
 *
 * Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
 * Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
 * Explanation: The figures above represent the grid at every state.
 *
 */
public class CyclicallyRotating {
    public static void main(String[] args) {

    }


    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[m][n];
        int layer = Math.min(m, n) / 2;

        for (int i = 0; i < layer; i++) {
            int[] data = new int[(m - 2 * i) * (n - 2 * i) - (m - (i + 1) * 2) * (n - (i + 1) * 2)];
            int idx = 0;
            // 从左往右
            for (int offset = i; offset < n - i - 1; ++offset)
                data[idx++] = grid[i][offset];
            // 从上往下
            for (int offset = i; offset < m - i - 1; ++offset)
                data[idx++] = grid[offset][n - i - 1];
            // 从右往左
            for (int offset = n - i - 1; offset > i; --offset)
                data[idx++] = grid[m - i - 1][offset];
            // 从下往上
            for (int offset = m - i - 1; offset > i; --offset)
                data[idx++] = grid[offset][i];
            // 把旋转完的放回去
            Integer[] ret = rotateVector(data, k);
            idx = 0;
            // 从左往右
            for (int offset = i; offset < n - i - 1; ++offset)
                grid[i][offset] = ret[idx++];
            // 从上往下
            for (int offset = i; offset < m - i - 1; ++offset)
                grid[offset][n - i - 1] = ret[idx++];
            // 从右往左
            for (int offset = n - i - 1; offset > i; --offset)
                grid[m - i - 1][offset] = ret[idx++];
            // 从下往上
            for (int offset = m - i - 1; offset > i; --offset)
                grid[offset][i] = ret[idx++];

        }
        return grid;
    }

    private Integer[] rotateVector(int[] vector, int offset) {
        // 取余, 否则会有无用功, 超时
        offset = offset % vector.length;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int item : vector) deque.add(item);
        // 旋转操作
        while (offset-- > 0) {
            deque.addLast(deque.pollFirst());
        }
        return deque.toArray(new Integer[0]);
    }


}
