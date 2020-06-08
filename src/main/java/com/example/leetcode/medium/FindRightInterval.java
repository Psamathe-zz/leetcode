package com.example.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i,
 * which can be called that j is on the "right" of i.
 *
 * For any interval i, you need to store the minimum interval j's index,
 * which means that the interval j has the minimum start point to build the "right" relationship for interval i.
 * If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.
 *
 * Note:
 *
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 *
 *
 * Example 1:
 *
 * Input: [ [1,2] ]
 *
 * Output: [-1]
 *
 * Explanation: There is only one interval in the collection, so it outputs -1.
 *
 *
 * Example 2:
 *
 * Input: [ [3,4], [2,3], [1,2] ]
 *
 * Output: [-1, 0, 1]
 *
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 *
 *
 * Example 3:
 *
 * Input: [ [1,4], [2,3], [3,4] ]
 *
 * Output: [-1, 2, -1]
 *
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 *
 * Accepted
 * 34,161
 * Submissions
 * 76,257
 */
public class FindRightInterval {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {3,4},
                {2,3},
                {1,2}
        };
        FindRightInterval findRightInterval = new FindRightInterval();
        int[] result = findRightInterval.findRightInterval(intervals);
        System.out.println(result);
    }
    public int[] findRightInterval(int[][] intervals) {
        int[] result = new int[intervals.length];
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int index = 0;
        for(int[] interval : intervals){
            if(!map.containsKey(interval[0]))
                map.put(interval[0],index);
            index++;
        }
        List<Integer> list = map.keySet().stream().sorted().collect(Collectors.toList());
        index = 0;
        for(int[] interval : intervals){

            int right = interval[1];
            Integer idx = map.ceilingKey(intervals[index][1]);
            int value = -1;
            for (int i = 0; i < list.size(); i++) {
                if(right <= list.get(i) ){
                    value = map.get(list.get(i));
                    break;
                }
            }
            result[index] = value;
            index++;
        }
        return result;
    }

    /**
     * faster solution
     * @param intervals
     * @return
     */
    public int[] findRightIntervalV1(int[][] intervals) {
        int min = intervals[0][0];
        int max = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            if(intervals[i][0] < min) min = intervals[i][0];
            if(intervals[i][1] > max) max = intervals[i][1];
        }
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, -1);
        for (int i = 0; i < intervals.length; i++) {
            bucket[intervals[i][0] - min] = i;
        }
        for (int i = bucket.length - 2; i >= 0; i--) {
            if(bucket[i] == - 1) bucket[i] = bucket[i + 1];
        }
        int[] result = new int[intervals.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = bucket[intervals[i][1] - min];
        }
        return result;
    }
}
