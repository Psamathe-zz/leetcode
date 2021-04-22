package com.example.leetcode.challenge.test2021.april.week3;


import java.util.List;

/**
 *
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 *
 *
 *
 * Example 1:
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 *
 * Input: triangle = [[-10]]
 * Output: -10
 *  */
public class Triangle {
    public static void main(String[] args) {

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        int[][] map = new int[length][length];
        int indexX = 0;
        int indexY;
        for (List<Integer> list : triangle){
            indexY = 0;
            for (Integer val : list){
                map[indexX][indexY] = val;
                if(indexX > 0) {
                    if (indexY == 0) {
                        map[indexX][indexY] += map[indexX - 1][indexY];
                    } else if(indexY == indexX){
                        map[indexX][indexY] += map[indexX - 1][indexY - 1];
                    } else {
                        map[indexX][indexY] += Math.min(map[indexX - 1][indexY - 1],map[indexX - 1][indexY]);
                    }
                }
                indexY++;
            }
            indexX++;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            res = Math.min(map[length-1][i],res);
        }

        return res;
    }
}
