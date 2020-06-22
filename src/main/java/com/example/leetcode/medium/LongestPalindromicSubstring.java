package com.example.leetcode.medium;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babadc"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * babadc
 * cdababcdabab
 * babadc
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "babadc";
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String result = longestPalindromicSubstring.longestPalindrome(s);
        System.out.println(result);
    }

    public String longestPalindrome(String s) {
        if(s.length() == 0)
            return "";
        String result = s.substring(0,1);
        int length = 1;
        for(int i = 0; i < s.length() - length;i++){
            for (int j = s.length(); j > i + length - 1 ; j--) {
                String s1 = s.substring(i,j);
                String s2 = new StringBuilder(s1).reverse().toString();
                if(s1.equals(s2) && s1.length()>result.length()) {
                    result = s1;
                    length = result.length();
                    break;
                }
            }

        }
        return result;
    }
}
