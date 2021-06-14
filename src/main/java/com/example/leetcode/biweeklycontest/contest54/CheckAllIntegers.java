package com.example.leetcode.biweeklycontest.contest54;


import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given a 2D integer array ranges and two integers left and right. Each ranges[i] = [starti, endi] represents an inclusive interval between starti and endi.
 *
 * Return true if each integer in the inclusive range [left, right] is covered by at least one interval in ranges. Return false otherwise.
 *
 * An integer x is covered by an interval ranges[i] = [starti, endi] if starti <= x <= endi.
 *
 *
 *
 * Example 1:
 *
 * Input: ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * Output: true
 * Explanation: Every integer between 2 and 5 is covered:
 * - 2 is covered by the first range.
 * - 3 and 4 are covered by the second range.
 * - 5 is covered by the third range.
 * Example 2:
 *
 * Input: ranges = [[1,10],[10,20]], left = 21, right = 21
 * Output: false
 * Explanation: 21 is not covered by any range.
 *
 */
public class CheckAllIntegers {
    public static void main(String[] args) {
        CheckAllIntegers checkAllIntegers = new CheckAllIntegers();
        boolean res = checkAllIntegers.isCovered(new int[][]{
                {1,2},
                {3,4},
                {5,6},
        }, 2, 5);
        System.out.println(res);
    }

    public boolean isCovered(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, (o1, o2) -> {
            if(o1[0] == o2[0])
                return o1[1] - o2[1];
            else
                return o1[0] - o2[0] ;
        });
        int length = ranges.length;
        for (int i = 0; i < length; i++) {
            if(ranges[i][1] < left)
                continue;
            else if(ranges[i][0] > right)
                break;
            else if(ranges[i][0] > left)
                return false;
            else
                left = ranges[i][1] + 1;
        }
        return left > right;

    }
}
