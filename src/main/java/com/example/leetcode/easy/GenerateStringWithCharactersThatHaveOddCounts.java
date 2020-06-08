package com.example.leetcode.easy;

import java.util.Arrays;

/**
 * Given an integer n, return a string with n characters such that each character in such string occurs an odd number of times.
 *
 * The returned string must contain only lowercase English letters. If there are multiples valid strings, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: "pppz"
 * Explanation: "pppz" is a valid string since the character 'p' occurs three times and the character 'z' occurs once.
 * Note that there are many other valid strings such as "ohhh" and "love".
 * Example 2:
 *
 * Input: n = 2
 * Output: "xy"
 * Explanation: "xy" is a valid string since the characters 'x' and 'y' occur once. Note that there are many other valid strings such as "ag" and "ur".
 * Example 3:
 *
 * Input: n = 7
 * Output: "holasss"
 */
public class GenerateStringWithCharactersThatHaveOddCounts {
    public static void main(String[] args) {
        GenerateStringWithCharactersThatHaveOddCounts generateStringWithCharactersThatHaveOddCounts = new GenerateStringWithCharactersThatHaveOddCounts();
        String result = generateStringWithCharactersThatHaveOddCounts.generateTheString(6);
        System.out.println(result);
    }

    public String generateTheString(int n) {
        char[] temp = new char[n];
        if(n%2==1) {
            Arrays.fill(temp,'a');
        } else {
            Arrays.fill(temp,0,1,'a');
            Arrays.fill(temp,1,n,'b');
        }
        return new String(temp);
    }
}
