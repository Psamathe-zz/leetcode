package com.example.leetcode.challenge.test2023.january.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
    public static void main(String[] args) {
        InsertInterval insertInterval = new InsertInterval();
        insertInterval.insert(new int[][]{
                {1,3},
                {6,9}
        }, new int[]{2,5});
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        boolean start = false;
        for (int[] interval: intervals) {
            if(interval[1] < newInterval[0]) {
                res.add(interval);
            } else if(interval[0] > newInterval[1]) {
                if(!start) {
                    res.add(newInterval);
                    start = true;
                }
                res.add(interval);
            } else {
                newInterval[0] = Math.min(interval[0],newInterval[0]);
                newInterval[1] = Math.max(interval[1],newInterval[1]);
            }
        }
        if(start) {
            res.add(newInterval);
        }
        int[][] r = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            r[i][0] = res.get(i)[0];
            r[i][1] = res.get(i)[1];
        }
        return r;

    }
}
