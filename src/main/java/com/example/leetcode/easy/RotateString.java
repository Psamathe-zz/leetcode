package com.example.leetcode.easy;

/**
 * We are given two strings, A and B.
 *
 * A shift on A consists of taking string A and moving the leftmost character to the rightmost position.
 * For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.
 *
 * Example 1:
 * Input: A = 'abcde', B = 'cdeab'
 * Output: true
 *
 * Example 2:
 * Input: A = 'abcde', B = 'abced'
 * Output: false
 */
public class RotateString {
    public static void main(String[] args) {
        String A = "abcde";
        String B = "cdeab";
        RotateString rotateString = new RotateString();
        boolean result = rotateString.rotateString(A,B);
        System.out.println(result);
    }

    public boolean rotateString(String A, String B) {
        if(A.length() != B.length())
            return  false;
        String target = A.concat(A);
        return target.contains(B);
    }
}
