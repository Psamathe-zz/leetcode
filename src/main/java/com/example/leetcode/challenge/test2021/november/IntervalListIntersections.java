package com.example.leetcode.challenge.test2021.november;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 *
 *
 * Example 1:
 *
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Example 2:
 *
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 * Example 3:
 *
 * Input: firstList = [], secondList = [[4,8],[10,12]]
 * Output: []
 * Example 4:
 *
 * Input: firstList = [[1,7]], secondList = [[3,10]]
 * Output: [[3,7]]
 */
public class IntervalListIntersections {
    public static void main(String[] args) {

    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int length1 = firstList.length;
        int length2 = secondList.length;
        List<int[]> res = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;
        int min;
        int max;
        while (index1 < length1 && index2 < length2) {
            min = Math.max( firstList[index1][0] , secondList[index2][0]);
            max = Math.min( firstList[index1][1] , secondList[index2][1]);
            if(max >= min) {
                res.add( new int[]{min, max});
            }

            if(firstList[index1][1] < secondList[index2][1]) {

                index1++;
            } else if(firstList[index1][1] == secondList[index2][1]) {
                index1++;
                index2++;
            } else {
                index2++;
            }
        }
        int[][] r = new int[res.size()][2];
        int index = 0;
        for (int[] t : res) {
            r[index] = t;
            index++;
        }
        return r;
    }
}
