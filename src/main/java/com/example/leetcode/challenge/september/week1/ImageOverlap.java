package com.example.leetcode.challenge.september.week1;

/**
 * Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)
 *
 * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.
 * After, the overlap of this translation is the number of positions that have a 1 in both images.
 *
 * (Note also that a translation does not include any kind of rotation.)
 *
 * What is the largest possible overlap?
 *
 * Example 1:
 *
 * Input: A = [[1,1,0],
 *             [0,1,0],
 *             [0,1,0]]
 *        B = [[0,0,0],
 *             [0,1,1],
 *             [0,0,1]]
 * Output: 3
 * Explanation: We slide A to right by 1 unit and down by 1 unit.
 * Notes:
 *
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
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
