package com.example.leetcode.challenge.test2021.may.week2;


/**
 * Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.
 *
 * Given two positive integers left and right represented as strings, return the number of super-palindromes integers in the inclusive range [left, right].
 *
 *
 *
 * Example 1:
 *
 * Input: left = "4", right = "1000"
 * Output: 4
 * Explanation: 4, 9, 121, and 484 are superpalindromes.
 * Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
 * Example 2:
 *
 * Input: left = "1", right = "2"
 * Output: 1
 */
public class SuperPalindromes {
    public static void main(String[] args) {

    }

    int res;
    public int superpalindromesInRange(String L, String R) {
        res = 0;
        long left = Long.valueOf(L), right = Long.valueOf(R);
        helper("", left, right);
        for (char c = '0'; c <= '9'; ++c) {
            helper(String.valueOf(c), left, right);
        }
        return res;
    }
    void helper(String cur, long left, long right) {
        if (cur.length() > 9)
            return;
        if (cur.length() != 0 && cur.charAt(0) != '0') {
            long num = Long.valueOf(cur);
            num *= num;
            if (num > right) return;
            if (num >= left && isPalindrome(String.valueOf(num)))
                ++res;
        }
        for (char c = '0'; c <= '9'; ++c) {
            helper(c + cur + c, left, right);
        }
    }
    boolean isPalindrome(String str) {
        int left = 0, right = (int)str.length() - 1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--))
                return false;
        }
        return true;
    }
}
