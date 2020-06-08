package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.
 * Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.
 *
 * Return the maximum number of rows that have all values equal after some number of flips.
 *
 *
 *
 * Example 1:
 *
 * Input: [
 * [0,1],
 * [1,1]]
 * Output: 1
 * Explanation: After flipping no values, 1 row has all values equal.
 * Example 2:
 *
 * Input: [
 * [0,1],
 * [1,0]]
 * Output: 2
 * Explanation: After flipping values in the first column, both rows have equal values.
 * Example 3:
 *
 * Input: [
 * [0,0,0],
 * [0,0,1],
 * [1,1,0]]
 * Output: 2
 * Explanation: After flipping values in the first two columns, the last two rows have equal values.
 */
public class FlipColumnsForMaximumNumberEqualRows {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0,0,0},{0,0,1},{1,1,0}};
        FlipColumnsForMaximumNumberEqualRows flipColumnsForMaximumNumberEqualRows = new FlipColumnsForMaximumNumberEqualRows();
        int result = flipColumnsForMaximumNumberEqualRows.maxEqualRowsAfterFlipsV1(matrix);
        System.out.println(result);
    }

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<Match,Integer> map = new HashMap<>();
        for(int[] values : matrix){
            Match match = new Match(values);
            map.put(match,map.getOrDefault(match,0)+1);
        }
        int max = 0;
        for(int value : map.values()){
            if(value > max)
                max = value;
        }
        return max;
    }

    public class Match{
        int[] values;

        public Match(int[] values) {
            this.values = values;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Match match = (Match) o;

            if(Arrays.equals(values, match.values))
                return true;
            else{
                for (int i = 0; i < values.length; i++) {
                    if(values[i] == match.values[i])
                        return false;
                }
                return true;
            }
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }


    /**
     * faster solution
     * @param matrix
     * @return
     */
    public int maxEqualRowsAfterFlipsV1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] row = new int[m];
        for (int i = 0; i < m; i++) {
            int r = 0;
            for (int j = 0; j < n; j++) {
                r = (r << 1) + matrix[i][j];
            }
            row[i] = r;
        }
        boolean[] visited = new boolean[m];
        int max = 0, sum = (int) Math.pow(2, n) - 1;
        for (int i = 0; i < m; i++) {
            if (visited[i]) {
                continue;
            }
            int cnt = 0;
            for (int j = i; j < m; j++) {
                if (row[i] == row[j] || row[i] + row[j] == sum) {
                    visited[j] = true;
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
