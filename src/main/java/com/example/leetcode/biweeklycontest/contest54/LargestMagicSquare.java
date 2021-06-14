package com.example.leetcode.biweeklycontest.contest54;

/**
 * A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both diagonal sums are all equal. The integers in the magic square do not have to be distinct. Every 1 x 1 grid is trivially a magic square.
 *
 * Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be found within this grid.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * Output: 3
 * Explanation: The largest magic square has a size of 3.
 * Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
 * - Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
 * - Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
 * - Diagonal sums: 5+4+3 = 6+4+2 = 12
 * Example 2:
 *
 *
 * Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 * Output: 2
 */
public class LargestMagicSquare {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/largest-magic-square/solution/java-qian-zhui-he-jia-bao-li-mei-ju-by-j-znyw/
     * @param grid
     * @return
     */
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 1;
        int[][] rowsum = new int[m][n+1];// 每一行的前缀和
        int[][] colsum = new int[m+1][n];// 每一列的前缀和
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                rowsum[i][j+1] = rowsum[i][j] + grid[i][j];
                colsum[i+1][j] = colsum[i][j] + grid[i][j];
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){// 从每一个点进行扩散
                for(int step = result; i+step < m && j+step < n; step++){ // 低于result的幻方没必要再寻找了，只需要找比它大的就好
                    if(isMagic(grid,rowsum,colsum,i,j,step)){result = step+1;}
                }
            }
        }
        return result;
    }
    public boolean isMagic(int[][] grid, int[][] rowsum, int[][] colsum, int x1, int y1, int step){// 判断是否是幻方
        boolean result = true;
        int sum = 0;
        int subsum = 0;
        for(int i = 0; i <= step; i++){
            sum += grid[x1+i][y1+i];// 左斜对角线之和
            subsum += grid[x1+step-i][y1+i];// 右斜对角线之和
        }
        if(sum != subsum){return false;}
        for(int i = 0; i <= step; i++){
            if(sum != rowsum[x1+i][y1+step+1]-rowsum[x1+i][y1]){return false;}// 判断每一行是否相等
            if(sum != colsum[x1+step+1][y1+i]-colsum[x1][y1+i]){return false;}// 判断每一列是否相等
        }
        return result;
    }
}
