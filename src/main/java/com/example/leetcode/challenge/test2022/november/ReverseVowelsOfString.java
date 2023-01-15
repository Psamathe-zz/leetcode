package com.example.leetcode.challenge.test2022.november;

/**
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "hello"
 * Output: "holle"
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: "leotcede"
 */
public class ReverseVowelsOfString {
    public static void main(String[] args) {
        ReverseVowelsOfString reverseVowelsOfString = new ReverseVowelsOfString();
        reverseVowelsOfString.reverseVowels("aA");
    }

    public String reverseVowels(String s) {
        int length = s.length();
        int left = 0;
        int right = length - 1;
        String vowel = "aeiouAEIOU";
        char[] chars = s.toCharArray();
        char c;
        do {
            while (left < right) {
                if(vowel.indexOf(chars[left]) == -1) {
                    left++;
                } else
                    break;
            }

            while (left < right) {
                if(vowel.indexOf(chars[right]) == -1) {
                    right--;
                } else
                    break;
            }

            if(left < right) {
                c = chars[left];
                chars[left] = chars[right];
                chars[right] = c;
            }
            left++;
            right--;

        } while (left < right);
        return String.valueOf(chars);

    }
}
