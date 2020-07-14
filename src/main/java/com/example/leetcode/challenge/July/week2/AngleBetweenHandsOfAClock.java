package com.example.leetcode.challenge.July.week2;

/**
 * Given two numbers, hour and minutes. Return the smaller angle (in degrees) formed between the hour and the minute hand.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: hour = 12, minutes = 30
 * Output: 165
 * Example 2:
 *
 *
 *
 * Input: hour = 3, minutes = 30
 * Output: 75
 * Example 3:
 *
 *
 *
 * Input: hour = 3, minutes = 15
 * Output: 7.5
 * Example 4:
 *
 * Input: hour = 4, minutes = 50
 * Output: 155
 * Example 5:
 *
 * Input: hour = 12, minutes = 0
 * Output: 0
 */
public class AngleBetweenHandsOfAClock {
    public static void main(String[] args) {
        int hour = 1;
        int minutes = 57;
        com.example.leetcode.medium.AngleBetweenHandsOfAClock angleBetweenHandsOfAClock = new com.example.leetcode.medium.AngleBetweenHandsOfAClock();
        angleBetweenHandsOfAClock.angleClock(hour,minutes);
    }

    public double angleClock(int hour, int minutes) {
        if(hour>=12)
            hour -= 12;

        double hourDouble = hour + (double)minutes / 60;
        hourDouble = hourDouble * 5;
        double max = Math.max(hourDouble,minutes);
        double min = Math.min(hourDouble,minutes);
        double res = Math.min(max-min,min + 60 - max) * 6;
        return res;
    }
}