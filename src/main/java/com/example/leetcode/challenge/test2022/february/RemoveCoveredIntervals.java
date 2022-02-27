package com.example.leetcode.challenge.test2022.february;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.
 *
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
 *
 * Return the number of remaining intervals.
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
 * [[34335,39239],[15875,91969],[29673,66453],[53548,69161],[40618,93111]]
 * [[3,10],[4,10],[5,11]]
 * [[97744,99177],[9782,42547],[21210,35161],[31377,85790],[53330,82476],[59552,64449],[4177,4511],[22686,79581],[7900,55898],[70317,75508],[48660,60445],[4175,59106],[64406,97296],[2547,35392],[24716,42920],[69598,78736],[74744,91826],[66305,69290],[34631,95035],[23099,86779],[23707,83804],[84597,91731],[76336,97281],[4507,19729],[68007,87741],[47660,72540],[20096,72534],[79422,89929],[66650,94270],[17827,46319],[5584,44234],[52418,53669],[29550,79734],[4525,37837],[26458,54655],[10550,97776],[19571,49453],[62428,94877],[41642,76480],[22741,44283],[40915,83070],[41016,59403],[49628,97532],[2874,25053],[902,4508],[17388,46321],[64582,70841],[27836,36686],[29291,82231],[34819,96651]]
 */
public class RemoveCoveredIntervals {
    public static void main(String[] args) {
        RemoveCoveredIntervals removeCoveredIntervals = new RemoveCoveredIntervals();
        removeCoveredIntervals.removeCoveredIntervals(new int[][]{{97744,99177},{9782,42547},{21210,35161},{31377,85790},{53330,82476},{59552,64449},{4177,4511},{22686,79581},{7900,55898},{70317,75508},{48660,60445},{4175,59106},{64406,97296},{2547,35392},{24716,42920},{69598,78736},{74744,91826},{66305,69290},{34631,95035},{23099,86779},{23707,83804},{84597,91731},{76336,97281},{4507,19729},{68007,87741},{47660,72540},{20096,72534},{79422,89929},{66650,94270},{17827,46319},{5584,44234},{52418,53669},{29550,79734},{4525,37837},{26458,54655},{10550,97776},{19571,49453},{62428,94877},{41642,76480},{22741,44283},{40915,83070},{41016,59403},{49628,97532},{2874,25053},{902,4508},{17388,46321},{64582,70841},{27836,36686},{29291,82231},{34819,96651}});
    }

    public int removeCoveredIntervals(int[][] intervals) {
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
