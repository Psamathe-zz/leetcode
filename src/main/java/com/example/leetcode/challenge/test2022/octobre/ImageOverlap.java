package com.example.leetcode.challenge.test2022.octobre;

/**
 * You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.
 *
 * We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.
 *
 * Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.
 *
 * Return the largest possible overlap.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
 * Output: 3
 * Explanation: We translate img1 to right by 1 unit and down by 1 unit.
 *
 * The number of positions that have a 1 in both images is 3 (shown in red).
 *
 * Example 2:
 *
 * Input: img1 = [[1]], img2 = [[1]]
 * Output: 1
 * Example 3:
 *
 * Input: img1 = [[0]], img2 = [[0]]
 * Output: 0
 *
 */
public class ImageOverlap {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/10355589.html
     * @param A
     * @param B
     * @return
     */
    public int largestOverlap(int[][] A, int[][] B) {
        int res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                res = Math.max(res, Math.max(count(A, B, i, j), count(B, A, i, j)));
            }
        }
        return res;
    }
    int count(int[][] A, int[][] B, int rowOffset, int colOffset) {
        int sum = 0, n = A.length;
        for (int i = rowOffset; i < n; ++i) {
            for (int j = colOffset; j < n; ++j) {
                sum += A[i][j] * B[i - rowOffset][j - colOffset];
            }
        }
        return sum;
    }
}
