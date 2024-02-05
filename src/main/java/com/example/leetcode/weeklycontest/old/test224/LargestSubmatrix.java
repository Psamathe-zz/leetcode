package com.example.leetcode.weeklycontest.old.test224;


import java.util.Arrays;

/**
 * You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
 *
 * Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
 * Output: 4
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 4.
 * Example 2:
 *
 *
 *
 * Input: matrix = [[1,0,1,0,1]]
 * Output: 3
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 3.
 * Example 3:
 *
 * Input: matrix = [[1,1,0],[1,0,1]]
 * Output: 2
 * Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.
 * Example 4:
 *
 * Input: matrix = [[0,0],[0,0]]
 * Output: 0
 * Explanation: As there are no 1s, no submatrix of 1s can be formed and the area is 0.
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m * n <= 105
 * matrix[i][j] is 0 or 1.
 */
public class LargestSubmatrix {
    public static void main(String[] args) {

    }

    /**
     * 预处理数组，计算以这个点为结尾，上面有多少个连续的1，就是这一列以这个点为结尾的最大高度
     * 这样就将二维问题转成一维
     *
     * 遍历每一行，对每一行进行排序，记录矩形的最长的高度，每次更新结果
     * https://leetcode-cn.com/problems/largest-submatrix-with-rearrangements/solution/java-yu-chu-li-shu-zu-bian-li-mei-xing-p-qpqu/
     * @param matrix
     * @return
     */
    public int largestSubmatrix(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int res=0;
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]==1){
                    //记录向上连续1的个数
                    matrix[i][j]+=matrix[i-1][j];
                }
            }
        }
        for(int i=0;i<n;i++){
            Arrays.sort(matrix[i]);
            for(int j=m-1;j>=0;j--){
                //更新矩形的最大高度
                int height=matrix[i][j];
                //更新最大面积
                res=Math.max(res,height*(m-j));
            }
        }
        return res;

    }
}
