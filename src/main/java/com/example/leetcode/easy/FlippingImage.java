package com.example.leetcode.easy;

/**
 * Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
 *
 * To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
 *
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
 *
 * Example 1:
 *
 * Input: [[1,1,0],[1,0,1],[0,0,0]]
 * Output: [[1,0,0],[0,1,0],[1,1,1]]
 * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
 * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
 * Example 2:
 *
 * Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
 * Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Notes:
 *
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 */
public class FlippingImage {
    public int[][] flipAndInvertImage(int[][] A) {
        int u = A.length;
        int v = A[0].length;
        int m = v%2 == 1?v/2 + 1:v/2;
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < m; j++) {
                int t = A[i][j];
                A[i][j] = A[i][v-1-j]==0?1:0;
                A[i][v-1-j] = t==0?1:0;
            }
        }
        return A;
    }

    /**
     * faster solution
     * @param A
     * @return
     */
    public int[][] flipAndInvertImageV1(int[][] A) {
        int m = A.length, n = A[0].length;
        for(int i = 0; i < m; ++i) {
            int l = 0, r = n - 1;
            while(l <= r) {
                int temp = A[i][l];
                A[i][l] = 1 - A[i][r];
                A[i][r] = 1 - temp;
                l++;
                r--;
            }
        }
        return A;
    }


    /**
     * less memory
     * @param A
     * @return
     */
    public int[][] flipAndInvertImageV2(int[][] A) {
        int rowSize = A.length;
        int colSize = A[0].length;
        // int colSize = A[0].length;

        int temp;
        for (int i = 0; i < rowSize; i++) {
            int forwardPoint = 0;
            int backwardPoint = colSize - 1;
            while (forwardPoint < backwardPoint) {
                temp = A[i][forwardPoint];
                A[i][forwardPoint] = A[i][backwardPoint];
                A[i][backwardPoint] = temp;
                forwardPoint++;
                backwardPoint--;
                // System.out.println(forwardPoint);
                // System.out.println(backwardPoint);
            }
            for (forwardPoint = 0; forwardPoint < colSize; forwardPoint++) {
                if (A[i][forwardPoint] == 0) {
                    A[i][forwardPoint] = 1;
                } else {
                    A[i][forwardPoint] = 0;
                }
            }
        }
        return A;
    }
}
