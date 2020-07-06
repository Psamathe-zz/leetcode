package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

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
        validPalindrome.isPalindrome("race a car");
    }


    public boolean isPalindrome(String s) {
        List<Character> list = new ArrayList<>();
        for(char c : s.toLowerCase().toCharArray()){
            if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
                list.add(c);
        }
        int length = list.size();
        if(length <= 1)
            return true;

        for (int i = 0; i < length/2; i++) {
            if(list.get(i) != list.get(length - 1 - i))
                return false;
        }
        return true;
    }


    /**
     * faster solution
     * @param c
     * @return
     */
    private boolean isValid(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    public boolean isPalindromeV1(String s) {
        if (s == null || s.isEmpty()) return true;

        s = s.toLowerCase();

        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char cleft = s.charAt(left);
            if (!isValid(cleft)) {
                left++;
                continue;
            }
            char cright = s.charAt(right);
            if (!isValid(cright)) {
                right --;
                continue;
            }
            if (cleft != cright) return false;
            left++;
            right--;
        }

        return true;
    }
}
