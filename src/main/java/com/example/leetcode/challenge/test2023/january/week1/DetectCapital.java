package com.example.leetcode.challenge.test2023.january.week1;

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
        if(word.length() == 1)
            return true;
        boolean find = false;
        int count = 0;
        int length = word.length();
        char c;
        for (int i = 0; i < length; i++) {
            c = word.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                if(find)
                    return false;
                count++;
            } else {
                find = true;
                if(count > 1)
                    return false;
            }
        }
        return true;

    }
}
