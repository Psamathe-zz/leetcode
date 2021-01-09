package com.example.leetcode.challenge.test2020.December.week1;


/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 */
public class SpiralMatrixII {
    public static void main(String[] args) {
        SpiralMatrixII s = new SpiralMatrixII();
        int n = 3;
        int[][] matrix = s.generateMatrix(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n -i; ++j) {
                matrix[i][j] = ++num;
            }
            for (int j = i + 1; j < n -i; ++j) {
                matrix[j][n - i - 1] = ++num;
            }
            for (int j = n - i - 2; j >= i; --j) {
                matrix[n - i - 1][j] = ++num;
            }
            for (int j = n - i - 2; j >= i + 1; --j) {
                matrix[j][i] = ++num;
            }
        }

        return matrix;
    }

    /**
     * faster solution
     * @param n
     * @return
     */
    public int[][] generateMatrixV1(int n) {
        int[][] res = new int[n][n];
        int top=0, bottom=n-1;
        int left=0, right=n-1;
        int k=1;
        while(left<right && top<bottom){
            for (int i=left; i<right;i++)
                res[top][i]=k++;
            for (int i=top; i<bottom;i++)
                res[i][right]=k++;
            for (int i=right;i> left;i--)
                res[bottom][i]=k++;
            for (int i=bottom; i> top;i--)
                res[i][left] =k++;
            top++;
            bottom--;
            left++;
            right--;
        }
        if (n%2==1) res[n/2][n/2]=k;
        return res;
    }
}
