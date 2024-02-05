package com.example.leetcode.weeklycontest.old.test328;

/**
 * You are given a positive integer n, indicating that we initially have an n x n 0-indexed integer matrix mat filled with zeroes.
 *
 * You are also given a 2D integer array query. For each query[i] = [row1i, col1i, row2i, col2i], you should do the following operation:
 *
 * Add 1 to every element in the submatrix with the top left corner (row1i, col1i) and the bottom right corner (row2i, col2i). That is, add 1 to mat[x][y] for for all row1i <= x <= row2i and col1i <= y <= col2i.
 * Return the matrix mat after performing every query.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, queries = [[1,1,2,2],[0,0,1,1]]
 * Output: [[1,1,0],[1,2,1],[0,1,1]]
 * Explanation: The diagram above shows the initial matrix, the matrix after the first query, and the matrix after the second query.
 * - In the first query, we add 1 to every element in the submatrix with the top left corner (1, 1) and bottom right corner (2, 2).
 * - In the second query, we add 1 to every element in the submatrix with the top left corner (0, 0) and bottom right corner (1, 1).
 * Example 2:
 *
 *
 * Input: n = 2, queries = [[0,0,1,1]]
 * Output: [[1,1],[1,1]]
 * Explanation: The diagram above shows the initial matrix and the matrix after the first query.
 * - In the first query we add 1 to every element in the matrix.
 *
 */
public class IncrementSubmatricesByOne {

    public static void main(String[] args) {

    }

    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] res = new int[n][n];

        for (int[] query : queries) {
            for (int i = query[0]; i <= query[2] ; i++) {
                for (int j = query[1]; j <= query[3] ; j++) {
                    res[i][j]++;
                }
            }
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/increment-submatrices-by-one/solution/er-wei-by-pratnius-oilo/
     * @param n
     * @param queries
     * @return
     */
    public int[][] rangeAddQueriesV1(int n, int[][] queries) {
        int[][] diff = new int[n+1][n+1];
        for(int i=0; i<queries.length; i++){
            int[] temp = queries[i];
            diff[temp[0]][temp[1]] += 1;
            diff[temp[2]+1][temp[3]+1] += 1;
            diff[temp[2]+1][temp[1]] -= 1;
            diff[temp[0]][temp[3]+1] -= 1;
        }
        int[][] ans = new int[n+1][n+1];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                ans[i+1][j+1] = ans[i][j+1] + ans[i+1][j] - ans[i][j] + diff[i][j];
            }
        }
        int[][] res = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                res[i][j] = ans[i+1][j+1];
            }
        }
        return res;
    }


    public int[][] rangeAddQueries1(int n, int[][] queries) {
        int[][] ps = new int[n][n];
        int[][] matrix = new int[n][n];

        for (int[] query : queries) {
            int x1= query[0];
            int y1= query[1];
            int x2= query[2];
            int y2= query[3];

            // update top presum
            ps[x1][y1]+=1;

            // update bottom left after presum
            if (x2 +1 <n)
                ps[x2+1][y1]-=1;

            if (x2+1<n && y2+1<n)
                ps[x2+1][y2+1] +=1;

            if (y2+1<n)
                ps[x1][y2+1]-=1;

        }

        for (int i=0; i<n; i++) {
            for (int j=1; j<n; j++) {
                ps[i][j] += ps[i][j-1];
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=1; j<n; j++) {
                ps[j][i] += ps[j-1][i];
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                matrix[i][j] += ps[i][j];
            }
        }

        return matrix;
    }
}
