package com.example.leetcode.challenge.november.week3;


import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };

        MergeIntervals mergeIntervals = new MergeIntervals();
        mergeIntervals.merge(intervals);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4370601.html
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int count = intervals.length;
        for(int i = 0; i < intervals.length - 1; ++i) {
            int[] i1 = intervals[i];
            for(int j = i + 1; j < intervals.length; ++j) {
                int[] i2 = intervals[j];
                if(i1[0] <= i2[1] && i1[1] >= i2[0]) {
                    i2[0] = Math.min(i1[0], i2[0]);
                    i2[1] = Math.max(i1[1], i2[1]);
                    i1[0] = 1;
                    i1[1] = 0;
                    --count;
                    break;
                }
            }
        }

        int[][] ans = new int[count][];
        for(int i = 0, j = 0; i < intervals.length; ++i) {
            if(intervals[i][0] <= intervals[i][1]) {
                ans[j++] = intervals[i];
            }
        }
        return ans;
    }


}
