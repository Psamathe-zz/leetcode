package com.example.leetcode.biweeklycontest.old.contest57;


/**
 * Given a string s, return true if s is a good string, or false otherwise.
 *
 * A string s is good if all the characters that appear in s have the same number of occurrences (i.e., the same frequency).
 *
 *
 *
 * Example 1:
 *
 * Input: s = ""vvvvvvvvvvvvvvvvvvv""
 * Output: true
 * Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
 * Example 2:
 *
 * Input: s = "aaabb"
 * Output: false
 * Explanation: The characters that appear in s are 'a' and 'b'.
 * 'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.
 */
public class CheckAllCharacters {
    public static void main(String[] args) {
        CheckAllCharacters checkAllCharacters = new CheckAllCharacters();

    }

    public boolean areOccurrencesEqual(String s) {
        int[] count = new int[26];
        for (char c: s.toCharArray()){
            count[c - 'a']++;
        }
        int size = 0;
        for (int i = 0; i < 26; i++) {
            if(count[i] != 0){
                if(size == 0)
                    size = count[i];
                else if(size != count[i])
                    return false;
            }
        }
        return true;
    }
}
