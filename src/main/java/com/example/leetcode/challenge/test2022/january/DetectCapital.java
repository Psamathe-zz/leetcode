package com.example.leetcode.challenge.test2022.january;

/**
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Given a string word, return true if the usage of capitals in it is right.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "USA"
 * Output: true
 * Example 2:
 *
 * Input: word = "FlaG"
 * Output: false
 */
public class DetectCapital {
    public static void main(String[] args) {

    }

    public boolean detectCapitalUse(String word) {
        char[] chars = word.toCharArray();
        int length = chars.length;
        int countCapital = 0;
        for (int i = 0; i < length; i++) {
            if(chars[i] <= 'Z' && chars[i] >= 'A'){
                countCapital++;
            }
        }

        if(countCapital == length || (countCapital == 1 && chars[0] <= 'Z' && chars[0] >= 'A') || countCapital == 0)
            return true;
        return false;
    }
}
