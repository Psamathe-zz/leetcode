package com.example.leetcode.weeklycontest.old.test225;


import java.util.PriorityQueue;

/**
 * You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given an integer k.
 *
 * The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j] where 0 <= i <= a < m and 0 <= j <= b < n (0-indexed).
 *
 * Find the kth largest value (1-indexed) of all the coordinates of matrix.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[5,2],[1,6]], k = 1
 * Output: 7
 * Explanation: The value of coordinate (0,1) is 5 XOR 2 = 7, which is the largest value.
 * Example 2:
 *
 * Input: matrix = [[5,2],[1,6]], k = 2
 * Output: 5
 * Explanation: The value of coordinate (0,0) is 5 = 5, which is the 2nd largest value.
 * Example 3:
 *
 * Input: matrix = [[5,2],[1,6]], k = 3
 * Output: 4
 * Explanation: The value of coordinate (1,0) is 5 XOR 1 = 4, which is the 3rd largest value.
 * Example 4:
 *
 * Input: matrix = [[5,2],[1,6]], k = 4
 * Output: 0
 * Explanation: The value of coordinate (1,1) is 5 XOR 2 XOR 1 XOR 6 = 0, which is the 4th largest value.
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 106
 * 1 <= k <= m * n
 */
public class FindKthLargestXORCoordinateValue {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/solution/yuan-di-dpyou-xian-dui-lie-by-simon123-t-yuwm/
     * @param matrix
     * @param k
     * @return
     */
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        //预处理第一行和第一列，便于后续DP
        for(int i=1;i<n;i++)matrix[0][i] ^= matrix[0][i-1];
        for(int i=1;i<m;i++)matrix[i][0] ^= matrix[i-1][0];
        //原地DP
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                matrix[i][j] ^= matrix[i-1][j] ^ matrix[i][j-1] ^ matrix[i-1][j-1];
            }
        }
        //维护长度为K的优先队列
        PriorityQueue<Integer> Q = new PriorityQueue<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                Q.add(matrix[i][j]);
                if(Q.size() > k){
                    Q.poll();
                }
            }
        }
        return Q.poll();

    }
}
