package com.example.leetcode.challenge.test2020.may.week2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.
 *
 *
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 * Example 2:
 *
 *
 *
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates contains no duplicate point.
 */
public class CheckStraightLine {
    public static void main(String[] args) {
        int[][] coordinates  = new int[][]{{1,1},{2,2},{3,4},{4,5},{5,6},{7,7}};
        CheckStraightLine checkStraightLine = new CheckStraightLine();
        boolean result = checkStraightLine.checkStraightLine(coordinates);
        System.out.println(result);
    }

    public boolean checkStraightLine(int[][] coordinates) {
        Arrays.sort(coordinates, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        double baseRadio = 0;


        for(int i = 1; i < coordinates.length;i++){
            double radio = (double)(coordinates[i][0] - coordinates[0][0]) / (double)(coordinates[i][1] - coordinates[0][1]);
            if(i == 1)
                baseRadio = radio;
            else{
                if(radio != baseRadio)
                    return false;
            }
        }

        return true;
    }


    /**
     * faster solution
     * @param coordinates
     * @return
     */
    public boolean checkStraightLineV2(int[][] coordinates) {
        // 移项 (y - y1) / (x - x1) = (y1 - y0) / (x1 - x0)
        int x0 = coordinates[0][0], y0 = coordinates[0][1], x1 = coordinates[1][0], y1 = coordinates[1][1];
        int dx = x1 - x0, dy = y1 - y0;
        for (int[] co : coordinates) {
            int x = co[0], y = co[1];
            if (dx * (y - y1) != dy * (x - x1))
                return false;
        }
        return true;
    }
}
