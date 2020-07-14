package com.example.leetcode.medium;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Example 1:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * Example 2:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 */
public class Search2DMatrix {
    public static void main(String[] args) {

    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int u = matrix.length;
        if(u == 0)
            return false;
        int v = matrix[0].length;
        if( v== 0 ){
            return false;
        }
        if(matrix[0][0] > target || matrix[u-1][v-1] < target)
            return false;


        int i;
        for ( i = 0; i < u; i++) {
            if((i < u - 1 && matrix[i][0] <= target && matrix[i+1][0] > target) || i == u - 1){
                break;
            }
        }

        int[] temp = matrix[i];
        int left = 0;
        int right = v -1;
        int mid;
        while (left <= right){
            mid = (left+right) / 2;
            if(temp[mid] == target)
                return true;
            else if(temp[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }
}
