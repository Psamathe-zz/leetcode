package com.example.leetcode.biweeklycontest.contest30;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a date string in the form Day Month Year, where:
 *
 * Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
 * Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
 * Year is in the range [1900, 2100].
 * Convert the date string to the format YYYY-MM-DD, where:
 *
 * YYYY denotes the 4 digit year.
 * MM denotes the 2 digit month.
 * DD denotes the 2 digit day.
 *
 *
 * Example 1:
 *
 * Input: date = "20th Oct 2052"
 * Output: "2052-10-20"
 * Example 2:
 *
 * Input: date = "6th Jun 1933"
 * Output: "1933-06-06"
 * Example 3:
 *
 * Input: date = "26th May 1960"
 * Output: "1960-05-26"
 *
 */
public class ReformatDate {
    public static void main(String[] args) {
        ReformatDate reformatDate = new ReformatDate();
        reformatDate.reformatDate("6th Jun 1933");
    }

    Map<String,String> monthMap;
    public String reformatDate(String date) {
        monthMap = new HashMap<>();
        monthMap.put("Jan","01");
        monthMap.put("Feb","02");
        monthMap.put("Mar","03");
        monthMap.put("Apr","04");
        monthMap.put("May","05");
        monthMap.put("Jun","06");
        monthMap.put("Jul","07");
        monthMap.put("Aug","08");
        monthMap.put("Sep","09");
        monthMap.put("Oct","10");
        monthMap.put("Nov","11");
        monthMap.put("Dec","12");
        String[] strings = date.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strings[2]+"-");
        stringBuilder.append(monthMap.get(strings[1]) + "-");
        stringBuilder.append(("0000" + strings[0]).substring(strings[0].length()).substring(0,2));
        return stringBuilder.toString();
    }
}
