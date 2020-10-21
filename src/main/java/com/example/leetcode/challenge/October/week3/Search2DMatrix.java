package com.example.leetcode.challenge.October.week3;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
 * Output: true
 * Example 2:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 13
 * Output: false
 * Example 3:
 *
 * Input: matrix = [], target = 0
 * Output: false
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 0 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,50}
        };
        int target = 22;
        Search2DMatrix search2DMatrix = new Search2DMatrix();
        boolean res = search2DMatrix.searchMatrix(matrix,target);
        System.out.println(res);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int u = matrix.length;
        if(u == 0)
            return false;
        int v = matrix[0].length;
        if(1 == u && v==0)
            return false;
        if(target < matrix[0][0] || target > matrix[u-1][v-1])
            return false;
        int vLeft = 0;
        int vRight = v - 1;
        int vMid;
        int i;
        for ( i = 0; i < u; i++) {
            if((i < u - 1 && matrix[i][0] <= target && matrix[i+1][0] > target) || i == u - 1){
                break;
            }
        }
        while (vLeft <= vRight){
            vMid = (vLeft + vRight) / 2;
            if(matrix[i][vMid] == target){
                return true;
            } else if(matrix[i][vMid] < target){
                vLeft = vMid + 1;
            } else {
                vRight = vMid - 1;
            }
        }
        return false;
    }
}
