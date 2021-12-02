package com.example.leetcode.challenge.test2021.november;

/**
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * Example 2:
 * <p>
 * Input: matrix = []
 * Output: 0
 * Example 3:
 * <p>
 * Input: matrix = [["0"]]
 * Output: 0
 * Example 4:
 * <p>
 * Input: matrix = [["1"]]
 * Output: 1
 * Example 5:
 * <p>
 * Input: matrix = [["0","0"]]
 * Output: 0
 */
public class MaximalRectangle {
    public static void main(String[] args) {

    }

    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        // Start typing your Java solution below
        // DO NOT write main() function
        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;

        for(int i = 0; i < m; i++){//row
            for(int j = 0; j < n; j++){//col
                if(matrix[i][j] == '1'){
                    int area = maxRectangle(matrix, i, j);
                    if(area > res)
                        res = area;
                }
            }
        }

        return res;
    }

    private int maxRectangle(char[][] matrix, int row, int col) {
        int minWidth = Integer.MAX_VALUE;
        int maxArea = 0;
        for (int i = row; i < matrix.length && matrix[i][col] == '1'; i++) {
            int width = 0;
            while (col + width < matrix[row].length
                    && matrix[i][col + width] == '1') {
                width++;
            }
            if (width < minWidth) {// 如果当前宽度小于了以前的最小宽度，更新它，为下面的矩形计算做准备
                minWidth = width;
            }
            int area = minWidth * (i - row + 1);
            if (area > maxArea)
                maxArea = area;
        }
        return maxArea;
    }
}
