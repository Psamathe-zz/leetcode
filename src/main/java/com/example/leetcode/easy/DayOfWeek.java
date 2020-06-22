package com.example.leetcode.easy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a date, return the corresponding day of the week for that date.
 *
 * The input is given as three integers representing the day, month and year respectively.
 *
 * Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 *
 *
 *
 * Example 1:
 *
 * Input: day = 31, month = 8, year = 2019
 * Output: "Saturday"
 * Example 2:
 *
 * Input: day = 18, month = 7, year = 1999
 * Output: "Sunday"
 * Example 3:
 *
 * Input: day = 15, month = 8, year = 1993
 * Output: "Sunday"
 * if(year%4 == 0 && year%100!=0 && month>2){
 *             result++;
 *         }
 *
 */
public class DayOfWeek {
    public static void main(String[] args) {
        int day = 31;
        int month = 8;
        int year = 2019;
        DayOfWeek dayOfWeek = new DayOfWeek();
        String result = dayOfWeek.dayOfTheWeek(day,month,year);
        System.out.println(result);
    }
    public String dayOfTheWeekV0(int day, int month, int year) {
        LocalDate localDate = LocalDate.of(year,month,day);
        return  localDate.getDayOfWeek().toString();
    }
    long DAYS_0000_TO_1970 = (146097 * 5L) - (30L * 365L + 7L);
    public String dayOfTheWeek(int day, int month, int year) {
        int dow0 = Math.floorMod(toEpochDay(day,month,year) + 3, 7);
        Map<Integer,String> map = new HashMap<>();
        map.put(0,"Monday");
        map.put(1,"Tuesday");
        map.put(2,"Wednesday");
        map.put(3,"Thursday");
        map.put(4,"Friday");
        map.put(5,"Saturday");
        map.put(6,"Sunday");
        return map.get(dow0);
    }

    public long toEpochDay(int day, int month, int year) {
        long y = year;
        long m = month;
        long total = 0;
        total += 365 * y;
        if (y >= 0) {
            total += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
        } else {
            total -= y / -4 - y / -100 + y / -400;
        }
        total += ((367 * m - 362) / 12);
        total += day - 1;
        if (m > 2) {
            total--;
            if (isLeapYear(year) == false) {
                total--;
            }
        }
        return total - DAYS_0000_TO_1970;
    }

    public boolean isLeapYear(long prolepticYear) {
        return ((prolepticYear & 3) == 0) && ((prolepticYear % 100) != 0 || (prolepticYear % 400) == 0);
    }


    /**
     * version 2
     * @param day
     * @param month
     * @param year
     * @return
     */
    public String dayOfTheWeekV2(int day, int month, int year) {
        int daysGone = 0;
        for (int i = 1970; i < year; i++)
            daysGone += isLeapYear(i) ? 366 : 365;
        for (int i = 1; i < month; i++)
            daysGone += numOfDaysInMonth(i, year);
        daysGone += day;
        System.out.println(daysGone);
        return getDayName(daysGone % 7);
    }

    public String getDayName(int num) {
        String result = "";
        switch(num) {
            case 0:
                result = "Wednesday";
                break;
            case 1:
                result = "Thursday";
                break;
            case 2:
                result = "Friday";
                break;
            case 3:
                result = "Saturday";
                break;
            case 4:
                result = "Sunday";
                break;
            case 5:
                result = "Monday";
                break;
            case 6:
                result = "Tuesday";
                break;
            default:
                result = "Impossible!";
        }
        return result;
    }

    public boolean isLeapYear(int year) {
        boolean A = (year % 4 == 0);
        boolean B = (year % 100 == 0);
        boolean C = (year % 400 == 0);
        return ((A && B && C) || (A && !B));
    }

    public int numOfDaysInMonth(int month, int year) {
        if (month == 2)
            return isLeapYear(year) ? 29 : 28;
        else if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;
        else
            return 31;
    }

}
