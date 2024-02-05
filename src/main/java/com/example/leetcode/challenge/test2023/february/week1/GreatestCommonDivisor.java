package com.example.leetcode.challenge.test2023.february.week1;

/**
 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
 *
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * Example 2:
 *
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * Example 3:
 *
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 */
public class GreatestCommonDivisor {
    public static void main(String[] args) {

    }

    public String gcdOfStrings(String str1, String str2) {
        if (str1.length() < str2.length())
            return gcdOfStrings(str2, str1);
        if (str2.length() == 0 )
            return str1;
        if (!str1.startsWith(str2))
            return "";
        return gcdOfStrings(str1.substring(str2.length()), str2);
    }
}
