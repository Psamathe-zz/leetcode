package com.example.leetcode.challenge.test2022.november;

/**
 * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
 *
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
 *
 * Return true if a and b are alike. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "book"
 * Output: true
 * Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
 * Example 2:
 *
 * Input: s = "textbook"
 * Output: false
 * Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
 * Notice that the vowel o is counted twice.
 */
public class DetermineStringHalvesAreAlike {
    public static void main(String[] args) {

    }

    public boolean halvesAreAlike(String s) {
        s = s.toLowerCase();
        String vowels = "aeiou";
        int length = s.length();
        int vowel1 = 0;
        int vowel2 = 0;
        for (int i = 0; i < length; i++){
            if(vowels.indexOf(s.charAt(i)) >= 0 ) {
                if (i < length / 2) {
                    vowel1++;
                } else
                    vowel2++;
            }
        }
        return vowel1 == vowel2;
    }
}
