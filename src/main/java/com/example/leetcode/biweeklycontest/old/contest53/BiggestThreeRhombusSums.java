package com.example.leetcode.biweeklycontest.old.contest53;

import java.util.*;

/**
 * You are given an m x n integer matrix grid​​​.
 *
 * A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:
 *
 *
 * Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.
 *
 * Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
 * Output: [228,216,211]
 * Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
 * - Blue: 20 + 3 + 200 + 5 = 228
 * - Red: 200 + 2 + 10 + 4 = 216
 * - Green: 5 + 200 + 4 + 2 = 211
 * Example 2:
 *
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [20,9,8]
 * Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
 * - Blue: 4 + 2 + 6 + 8 = 20
 * - Red: 9 (area 0 rhombus in the bottom right corner)
 * - Green: 8 (area 0 rhombus in the bottom middle)
 * Example 3:
 *
 * Input: grid = [[7,7,7]]
 * Output: [7]
 * Explanation: All three possible rhombus sums are the same, so return [7].
 *
 */
public class BiggestThreeRhombusSums {
    public static void main(String[] args) {

    }

    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                set.add(grid[i][j]);// 先把中心点添加到set里面
                int step = 1;// 当前中心扩散的长度
                while(i-step >= 0 && j -step >= 0 && i+step < m && j+step < n){
                    int sub = 0;
                    for(int x = 0; x < step; x++){
                        sub += grid[i+x][j+step-x]+grid[i-x][j-step+x]+grid[i+step-x][j-x]+grid[i-step+x][j+x];
                    }
                    set.add(sub);
                    step++;
                }
            }
        }
        int len = set.size();
        if(len <= 3){
            int[] result = new int[len];
            int loc = len-1;
            for(int each:set) {
                result[loc--] = each;
            }
            return result;
        }
        else{
            int[] result = new int[3];
            Object[] mid = set.toArray();
            result[2] = (int)mid[mid.length-3];
            result[1] = (int)mid[mid.length-2];
            result[0] = (int)mid[mid.length-1];
            return result;
        }
    }

}
