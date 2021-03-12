package com.example.leetcode.challenge.test2021.february.week4;


/**
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 * Example 2:
 *
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 */
public class SearchMatrixII {
    public static void main(String[] args) {

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int u = matrix.length;
        int v = matrix[0].length;
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                if(matrix[i][j] == target)
                    return true;
                else if(matrix[i][j] > target)
                    break;
            }
        }
        return false;
    }

    /**
     * faster solution
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixV0(int[][] matrix, int target) {
        if(matrix == null || matrix.length==0 || matrix[0].length==0) return false;

        int column = matrix[0].length-1;
        int i=0;
        while(i< matrix.length && column>=0){
            if(matrix[i][column]==target){
                return true;
            } else if(matrix[i][column]>target){
                column--;
            } else {
                i++;
            }
        }
        return false;
    }
}
