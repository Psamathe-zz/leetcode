package com.example.leetcode.medium;

/**
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * Example 2:
 *
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */
public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                { 5, 1, 9,11},
                { 2, 4, 8,10},
                {13, 3, 6, 7},
                {15,14,12,16}
        };
        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate(matrix);
        matrix.toString();
    }

    /**
     * y = length - x
     * x = y
     * @param matrix*
     *
     *
     */
    public void rotate(int[][] matrix) {
        int count;
        int x;
        int y;
        int xTemp;
        int yTemp;
        int temp;
        int length = matrix.length;
        int width =  length % 2 == 1 ? length + 1: length;
        for (int i = 0; i < length/2; i++) {
            for (int j = 0; j < width/2; j++) {
                temp = matrix[i][j];
                x = i;
                y = j;
                count = 3;
                while (count >0){
                    xTemp = x;
                    yTemp = y;
                    x = length - 1 - yTemp ;
                    y = xTemp;
                    matrix[xTemp][yTemp] = matrix[x][y];
                    count--;
                }
                matrix[x][y] = temp;
            }
        }

    }

    /**
     * faster solution
     * @param matrix
     */
    public void rotateV1(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - 1 - j];
                matrix[i][len - 1 - j] = temp;
            }
        }
    }
}
