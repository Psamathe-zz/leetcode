package com.example.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.
 *
 *
 *
 * Example 1:
 *
 * Input: date = "2019-01-09"
 * Output: 9
 * Explanation: Given date is the 9th day of the year in 2019.
 * Example 2:
 *
 * Input: date = "2019-02-10"
 * Output: 41
 * Example 3:
 *
 * Input: date = "2003-03-01"
 * Output: 60
 * Example 4:
 *
 * Input: date = "2004-03-01"
 * Output: 61
 */
public class DayoftheYear {

    public static void main(String[] args) {
        String date = "2004-03-01";
        DayoftheYear dayoftheYear = new DayoftheYear();
        int result = dayoftheYear.dayOfYear(date);
        System.out.println(result);
    }
    public int dayOfYear(String date) {
        int result = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,31);
        map.put(2,28);
        map.put(3,31);
        map.put(4,30);
        map.put(5,31);
        map.put(6,30);
        map.put(7,31);
        map.put(8,31);
        map.put(9,30);
        map.put(10,31);
        map.put(11,30);
        map.put(12,31);
        String[] listStr = date.split("-");
        int year = Integer.valueOf(listStr[0]);
        int month = Integer.valueOf(listStr[1]);
        int day = Integer.valueOf(listStr[2]);

        for(int i = 1;i<month;i++){
            result += map.get(i);
        }
        if(year%4 == 0 && year%100!=0 && month>2){
            result++;
        }

        return result + day;
    }
}
