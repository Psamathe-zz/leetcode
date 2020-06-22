package com.example.leetcode.medium;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Example 1:
 *
 * Input:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * Output:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * Example 2:
 *
 * Input:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * Output:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 */
public class SetMatrixZeroes {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        SetMatrixZeroes setMatrixZeroes = new SetMatrixZeroes();
        setMatrixZeroes.setZeroes(nums);
        System.out.println(nums);
    }

    public void setZeroes(int[][] matrix) {
        int u = matrix.length;
        int v = matrix[0].length;
        int[] uR  = new int[u];
        int[] vR  = new int[v];
        for(int i = 0;i <u ;i++) {
            for (int j = 0; j < v; j++) {
                if(matrix[i][j] == 0){
                    uR[i] = 1;
                    vR[j] = 1;
                }
            }
        }

        for(int i = 0;i <u ;i++){
            for(int j = 0;j<v;j++){
                matrix[i][j] *= (1 - uR[i])*(1 - vR[j]);
            }
        }
    }
}
