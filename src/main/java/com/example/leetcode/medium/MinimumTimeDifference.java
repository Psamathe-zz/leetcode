package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
 * Example 1:
 * Input: ["23:59","00:00"]
 * Output: 1
 * Note:
 * The number of time points in the given list is at least 2 and won't exceed 20000.
 * The input time is legal and ranges from 00:00 to 23:59.
 */
public class MinimumTimeDifference {
    public static void main(String[] args) {
        List<String> timePoints = Arrays.asList(new String[]{"23:59","00:00"});
        MinimumTimeDifference minimumTimeDifference = new MinimumTimeDifference();
        int result = minimumTimeDifference.findMinDifference(timePoints);
    }
    static int FIX = 60 * 24;
    public int findMinDifference(List<String> timePoints) {
        int result = Integer.MAX_VALUE;
        int size = timePoints.size();
        int[] times = new int[size];
        int temp;
        for (int i = 0; i < size; i++) {
            times[i] = convert(timePoints.get(i));
            for (int j = 0; j < i; j++) {
                temp = Math.abs(times[i] - times[j]);
                temp = Math.min(temp, FIX - temp);
                result = Math.min(result,temp);
            }
        }
        return result;
    }

    public int convert(String time){
        String[] list = time.split(":");
        return Integer.parseInt(list[0]) * 60 + Integer.parseInt(list[1]);
    }


    /**
     * faster solution
     * @param timePoints
     * @return
     */
    public int findMinDifferenceV1(List<String> timePoints) {
        boolean[] bucketTime = new boolean[24 * 60];
        for (String time : timePoints) {
            int hr = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(3));
            if (bucketTime[hr * 60 + min]) return 0;
            bucketTime[hr * 60 + min] = true;
        }
        int firstTime = -1;
        int prevTime = -1;
        int minTimeDiff = Integer.MAX_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (bucketTime[i]) {
                if (firstTime == -1) {
                    firstTime = i;
                }
                if (prevTime != -1) {
                    minTimeDiff = Math.min(minTimeDiff, Math.min(i - prevTime, 1440 - i + prevTime));
                }
                prevTime = i;
            }
        }
        //System.out.println(minTimeDiff);
        minTimeDiff = Math.min(minTimeDiff, Math.min(prevTime - firstTime, 1440 - prevTime + firstTime));
        return minTimeDiff;
    }


    /**
     * less memory
     * @param timePoints
     * @return
     */
    public int findMinDifferenceV2(List<String> timePoints) {
        int n = 24 * 60;
        boolean[] buckets = new boolean[n];
        for (String time : timePoints) {
            int minutes = countMins(time);
            if (buckets[minutes]) {
                return 0;
            }

            buckets[minutes] = true;
        }

        int pre = -1, first = -1, res = 24 * 60;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i]) {
                if (first == -1) {
                    first = i;
                } else {
                    res = Math.min(res, i - pre);
                }

                pre = i;
            }
        }

        res = Math.min(res, first + 24 * 60 - pre);
        return res;
    }

    private int countMins(String time) {
        int index = time.indexOf(':');
        int hours = Integer.valueOf(time.substring(0, index));
        int minutes = Integer.valueOf(time.substring(index + 1));
        return hours * 60 + minutes;
    }
}
