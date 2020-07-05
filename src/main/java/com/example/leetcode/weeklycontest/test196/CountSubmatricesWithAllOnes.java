package com.example.leetcode.weeklycontest.test196;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,0,1],
 *               [1,1,0],
 *               [1,1,0]]
 * Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2.
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 * Example 2:
 *
 * Input: mat = [[0,1,1,0],
 *               [0,1,1,1],
 *               [1,1,1,0]]
 * Output: 24
 * Explanation:
 * There are 8 rectangles of side 1x1.
 * There are 5 rectangles of side 1x2.
 * There are 2 rectangles of side 1x3.
 * There are 4 rectangles of side 2x1.
 * There are 2 rectangles of side 2x2.
 * There are 2 rectangles of side 3x1.
 * There is 1 rectangle of side 3x2.
 * Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
 * Example 3:
 *
 * Input: mat = [[1,1,1,1,1,1]]
 * Output: 21
 * Example 4:
 *
 * Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= rows <= 150
 * 1 <= columns <= 150
 * 0 <= mat[i][j] <= 1
 */
public class CountSubmatricesWithAllOnes {

    public static void main(String[] args) {

    }


    /**
     * https://www.acwing.com/file_system/file/content/whole/index/content/1075030/
     * @param mat
     * @return
     */
    public int numSubmat(int[][] mat) {
        int u = mat.length;
        int v = mat[0].length;
        int result = 0;
        int[][] preSum = new int[u+1][v+1];

        for (int i = 1; i <= u; ++i) {
            for (int j = 1; j <= v; ++j) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j- 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int res = 0;
        for (int row = 1; row <= u; ++row) {
            for (int len = 1; row + len - 1 <= u; ++len) {
                for (int col = 1; col <= v; ++col) {
                    if (getSum(preSum, row, col, row + len - 1, col) == len) {
                        int tmp = col;
                        while (tmp <= v && getSum(preSum, row, tmp, row + len - 1, tmp) == len) ++tmp;
                        int length = tmp - col;
                        res += (1 + length) * length / 2;
                        col = tmp - 1;
                    }
                }
            }
        }


        return res;
    }

    int getSum(int[][] preSum, int x1, int y1, int x2, int y2)
    {
        return preSum[x2][y2] - preSum[x1 - 1][y2] - preSum[x2][y1 - 1] + preSum[x1 - 1][y1 - 1];
    }


    /**
     * faster solution
     * @param mat
     * @return
     */
    public int numSubmatV1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] prefix = new int[m][n];
        for (int j = 0; j < n; j++)
            prefix[0][j] = mat[0][j];
        for (int i = 1; i < m; i++)
            for (int j = 0; j < n; j++)
                prefix[i][j] = mat[i][j] == 0 ? 0 : prefix[i-1][j] + 1;
        int count = 0;
        for (int i = 0; i < m; i++) {
            Deque<int[]> q = new ArrayDeque<>();
            int area = 0;
            for (int j = 0; j < n; j++) {
                int me = 1;
                while (!q.isEmpty() && q.peekLast()[0] >= prefix[i][j]) {
                    int[] hc = q.removeLast();
                    area -= (hc[0] - prefix[i][j]) * hc[1];
                    me += hc[1];
                }
                q.addLast(new int[]{prefix[i][j], me});
                area += prefix[i][j];
                count += area;
            }
        }
        return count;
    }

    public int numSubmatV2(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] dp = new int[n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            Stack<int[]> stack = new Stack<>();
            for (int j = 0; j < n; j++) {
                dp[j] = mat[i][j] == 0 ? 0 : dp[j]+1;

                int prevPos = j;
                while (!stack.isEmpty() && stack.peek()[1] > dp[j]) {
                    int[] info = stack.pop();
                    int prev = !stack.isEmpty() ? stack.peek()[1] : 0;
                    count += (j-info[0]) * (j-info[0]+1) / 2 * (info[1]-Math.max(dp[j], prev));
                    prevPos = info[0];
                }
                if (stack.isEmpty()) {
                    stack.push(new int[]{0, dp[j]});
                } else if (stack.peek()[1] < dp[j]) {
                    stack.push(new int[]{prevPos, dp[j]});
                }
            }

            while (!stack.isEmpty()) {
                int[] info = stack.pop();
                int prev = stack.isEmpty() ? 0 : stack.peek()[1];
                count += (n-info[0]) * (n-info[0]+1) * (info[1]-prev) / 2;
            }
        }
        return count;
    }
}
