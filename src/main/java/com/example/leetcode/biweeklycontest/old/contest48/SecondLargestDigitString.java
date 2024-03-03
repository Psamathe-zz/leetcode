package com.example.leetcode.biweeklycontest.old.contest48;


/**
 * Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does not exist.
 *
 * An alphanumeric string is a string consisting of lowercase English letters and digits.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dfa12321afd"
 * Output: 2
 * Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
 * Example 2:
 *
 * Input: s = "abc1111"
 * Output: -1
 * Explanation: The digits that appear in s are [1]. There is no second largest digit.
 */
public class SecondLargestDigitString {
    public static void main(String[] args) {

    }

    public int secondHighest(String s) {
        int length = s.length();
        int max = -1;
        int secondMax = -1;
        char c;
        int val;
        for (int i = 0; i < length; i++) {
            c = s.charAt(i);
            if( c <= '9' && c >= '0'){
                val = c - '0';
                if(val > max) {
                    secondMax = max;
                    max = val;
                } else if( val != max && val > secondMax){
                    secondMax = val;
                }
            }
        }
        return secondMax;
    }
}
