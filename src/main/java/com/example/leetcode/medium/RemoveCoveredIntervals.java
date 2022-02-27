package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
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
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * intervals[i] != intervals[j] for all i != j
 */
public class RemoveCoveredIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {34335,39239},
                {15875,91969},
                {29673,66453},
                {53548,69161},
                {40618,93111}
        };
        RemoveCoveredIntervals removeCoveredIntervals = new RemoveCoveredIntervals();
        int result = removeCoveredIntervals.removeCoveredIntervals(intervals);
        System.out.println(result);
    }

    public int removeCoveredIntervals(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        List<int[]> listTemp = new ArrayList<>();
        boolean add;
        for (int[] interval : intervals){
            add = true;
            for(int[] value : list){
                if(interval[0] >= value[0] && interval[1] <= value[1]){
                    add = false;
                }
                if(interval[0] > value[0] || interval[1] < value[1]){
                    listTemp.add(value);
                }
            }
            if(add){
                listTemp.add(interval);
            }
            list.clear();
            list.addAll(listTemp);
            listTemp.clear();
        }

        return list.size();
    }


    /**
     * faster solution
     * @param intervals
     * @return
     */
    public int removeCoveredIntervalsV2(int[][] intervals) {
        int result = intervals.length;
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i] != null) {
                for (int j = i + 1; j < intervals.length; j++) {
                    if (intervals[j] != null) {
                        if (isCovered(intervals[j], intervals[i])) {
                            intervals[j] = null;
                            result--;
                        } else if (isCovered(intervals[i], intervals[j])) {
                            result--;
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    boolean isCovered(int[] interval1, int[] interval2) {
        return interval1[0] >= interval2[0] && interval1[1] <= interval2[1];
    }
}
