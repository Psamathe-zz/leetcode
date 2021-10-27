package com.example.leetcode.challenge.test2021.september.week2;

/**
 * Given a string s, reverse the string according to the following rules:
 *
 * All the characters that are not English letters remain in the same position.
 * All the English letters (lowercase or uppercase) should be reversed.
 * Return s after reversing it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ab-cd"
 * Output: "dc-ba"
 * Example 2:
 *
 * Input: s = "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 * Example 3:
 *
 * Input: s = "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 *
 * "7_28]"
 *
 */
public class ReverseOnlyLetters {
    public static void main(String[] args) {
        ReverseOnlyLetters reverseOnlyLetters = new ReverseOnlyLetters();
        String res = reverseOnlyLetters.reverseOnlyLetters("7_28]");
        System.out.println(res);
    }

    public String reverseOnlyLetters(String s) {
        int length = s.length();
        int left = 0;
        int right = length - 1;
        char[] chars = s.toCharArray();
        char temp;

        while (left < right){
            while (left < right && !isLetter(chars[left])){
                left++;
            }
            while (left < right && !isLetter(chars[right])){
                right--;
            }
            if(left < right && left < length - 1 && right > 0) {
                temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        return String.valueOf(chars);
    }

    public boolean isLetter(char c){
        if(( c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a')) {
            return true;
        }
        return false;
    }
}
