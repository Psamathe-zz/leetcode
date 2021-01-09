package com.example.leetcode.challenge.test2020.October.week1;

import java.util.*;

/**
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
 *
 * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 *
 * After doing so, return the number of remaining intervals.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * Example 2:
 *
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 * Example 3:
 *
 * Input: intervals = [[0,10],[5,12]]
 * Output: 2
 * Example 4:
 *
 * Input: intervals = [[3,10],[4,10],[5,11]]
 * Output: 2
 * Example 5:
 *
 * Input: intervals = [[1,2],[1,4],[3,4]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 1000
 * intervals[i].length == 2
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * All the intervals are unique.
 */
public class RemoveCoveredIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {0,10},
                {5,12}
        };
        RemoveCoveredIntervals removeCoveredIntervals = new RemoveCoveredIntervals();
        removeCoveredIntervals.removeCoveredIntervals(intervals);
    }

    public int removeCoveredIntervals(int[][] intervals) {
        int res = 0;
        int length = intervals.length;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        boolean add;
        for (int i = 0; i < length - 1; i++) {
            add = true;
            for (int j = i+1; j < length; j++) {
                if(intervals[j][1] >= intervals[i][1]){
                    add = false;
                    break;
                }
            }
            if(add)
                res++;
        }
        return res + 1;
    }

}
