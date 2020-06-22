package com.example.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * We are given hours, a list of the number of hours worked per day for a given employee.
 *
 * A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 *
 * A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.
 *
 * Return the length of the longest well-performing interval.
 *
 *
 *
 * Example 1:
 *
 * Input: hours = [9,9,6,0,6,6,9]
 * Output: 3
 * Explanation: The longest well-performing interval is [9,9,6].
 *
 * [6,6,9]
 */
public class LongestWellPerformingInterval {
    public static void main(String[] args) {
        int[] hours = new int[]{9,9,6,0,6,6,9};
        LongestWellPerformingInterval longestWellPerformingInterval = new LongestWellPerformingInterval();
        int result = longestWellPerformingInterval.longestWPI(hours);
        System.out.println(result);
    }

    /**
     * dp[i] = dp[i+1]
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        int result = 0;
        int interval;
        for(int i=0 ;i< hours.length;i++){
            interval = 0;
            for (int j = i; j < hours.length; j++) {
                if(hours[j] > 8)
                    interval++;
                else
                    interval--;
                if(interval > 0 && j - i + 1>result)
                    result = j - i + 1 ;
            }
        }
        return result;
    }


    public int longestWPIV1(int[] hours) {
        int i, n;
        n = hours.length;
        int[] first = new int[n + 3];
        for (i = 0; i < n + 3; i++) {
            first[i] = n + 1;
        }
        int res = 0, sum = 0;
        for (i = 0; i < n; i++) {
            if (hours[i] > 8)
                sum++;
            else
                sum--;
            if (sum > 0) {
                res = i + 1;
            } else {
                if (i - first[-sum + 1] > res) {
                    res = i - first[-sum + 1];
                }
                if (first[-sum] > i) {
                    first[-sum] = i;
                }
            }
        }
        return res;
    }


    public int longestWPIV2(int[] hours) {
        if (hours == null || hours.length == 0) return 0;
        Map<Integer, Integer> hashmap = new HashMap<>();

        int prefixSum = 0;
        int res = 0;
        for (int i = 0 ; i < hours.length ; i++) {
            prefixSum += hours[i] > 8 ? 1 : -1;
            if (prefixSum > 0) {
                res = i + 1;
            } else {
                if (!hashmap.containsKey(prefixSum)) {
                    hashmap.put(prefixSum, i);
                }
                if (hashmap.containsKey(prefixSum - 1)) {
                    res = Math.max(res, i - hashmap.get(prefixSum - 1));
                }
            }
        }
        return res;
    }
}
