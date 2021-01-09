package com.example.leetcode.challenge.test2020.april.week4;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Output: 4
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};
        MaximalSquare maximalSquare = new MaximalSquare();
        int result = maximalSquare.maximalSquare(matrix);
        System.out.println(result);
    }

    public int maximalSquare(char[][] matrix) {
        char[][] temp = matrix;
        char[][] isSquare;
        int u = matrix.length - 1;
        if(matrix.length == 0)
            return 0;
        int v = matrix[0].length - 1;
        if(matrix.length == 1 || matrix[0].length == 1){
            for(char[] chars: matrix){
                for(char c : chars){
                    if(c == '1')
                        return 1;
                }
            }
            return 0;
        }
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length;j++) {
                if (matrix[i][j] == '1') {
                    index = 1;
                    break;
                }
            }
        }
        boolean isStable = false;
        while (!isStable && index>=1) {
            isStable = true;
            isSquare = new char[u][v];
            for (int i = 0; i < temp.length - 1; i++) {
                for (int j = 0; j < temp[0].length - 1; j++) {
                    if (temp[i][j] == '1' && temp[i + 1][j] == '1' && temp[i][j + 1] == '1' && temp[i + 1][j + 1] == '1') {
                        isSquare[i][j] = '1';
                        isStable = false;
                    } else {
                        isSquare[i][j] = '0';
                    }
                }
            }
            temp = isSquare;
            u--;
            v--;
            if(!isStable)
                index++;
        }

        return index==0?0:(int)Math.pow(index,2);
    }

    /**
     * faster solution
     */

    int maxLen = 0;
    public int maximalSquareV2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rc = matrix.length;
        int cc = matrix[0].length;
        for (int i = 0; i < rc; i++) {
            for (int j = 0; j < cc; j++) {
                checkSquare(matrix, i, j);
            }
        }
        return maxLen*maxLen;
    }

    public void checkSquare(char[][] matrix, int r, int c) {
        if (matrix[r][c] == '0') {
            return;
        }
        if (r == 0 || c == 0) {
            maxLen = Math.max(maxLen, 1);
            return;
        }
        int max = 1;
        max = Math.min(Math.min(matrix[r - 1][c] - '0', matrix[r - 1][c - 1] - '0'), matrix[r][c - 1] - '0');
        maxLen = Math.max(max + 1, maxLen);
        matrix[r][c] = (char)(max + 1 + '0');
    }


    /**
     * less memory
     */
    int ans = 0;
    public int maximalSquareV3(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        Integer[][]memo = new Integer[matrix.length][matrix[0].length];
        backtrack(0, 0,matrix,memo);
        return ans;
    }

    // Returns max length of 1's square with top left corner (i, j)
    private int backtrack(int i, int j,char[][] matrix,Integer[][] memo) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return 0;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int right = backtrack(i, j + 1,matrix,memo);
        int bottomRight = backtrack(i + 1, j + 1,matrix,memo);
        int bottom = backtrack(i + 1, j,matrix,memo);

        if (matrix[i][j] != '0') {
            memo[i][j] = Math.min(right, Math.min(bottomRight, bottom)) + 1;
        } else {
            memo[i][j] = 0;
        }

        ans = Math.max(ans, memo[i][j] * memo[i][j]);
        return memo[i][j];
    }
}
