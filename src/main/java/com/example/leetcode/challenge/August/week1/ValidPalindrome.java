package com.example.leetcode.challenge.August.week1;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        boolean res = validPalindrome.isPalindrome("race a car");
        System.out.println(res);
    }

    public boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c: s.toLowerCase().toCharArray()){
            if(c>='a' && c<='z' || c>='0' && c<='9')
                stringBuilder.append(c);
        }
        String s1 = stringBuilder.toString();
        String s2 = stringBuilder.reverse().toString();
        return s1.equals(s2);
    }
}
