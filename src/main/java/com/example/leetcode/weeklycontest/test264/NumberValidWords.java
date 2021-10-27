package com.example.leetcode.weeklycontest.test264;


import java.util.List;

/**
 * A sentence consists of lowercase letters ('a' to 'z'), digits ('0' to '9'), hyphens ('-'), punctuation marks ('!', '.', and ','), and spaces (' ') only. Each sentence can be broken down into one or more tokens separated by one or more spaces ' '.
 *
 * A token is a valid word if:
 *
 * It only contains lowercase letters, hyphens, and/or punctuation (no digits).
 * There is at most one hyphen '-'. If present, it should be surrounded by lowercase characters ("a-b" is valid, but "-ab" and "ab-" are not valid).
 * There is at most one punctuation mark. If present, it should be at the end of the token.
 * Examples of valid words include "a-b.", "afad", "ba-c", "a!", and "!".
 *
 * Given a string sentence, return the number of valid words in sentence.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence = "cat and  dog"
 * Output: 3
 * Explanation: The valid words in the sentence are "cat", "and", and "dog".
 * Example 2:
 *
 * Input: sentence = "!this  1-s b8d!"
 * Output: 0
 * Explanation: There are no valid words in the sentence.
 * "!this" is invalid because it starts with a punctuation mark.
 * "1-s" and "b8d" are invalid because they contain digits.
 * Example 3:
 *
 * Input: sentence = "alice and  bob are playing stone-game10"
 * Output: 5
 * Explanation: The valid words in the sentence are "alice", "and", "bob", "are", and "playing".
 * "stone-game10" is invalid because it contains digits.
 * Example 4:
 *
 * Input: sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
 * Output: 6
 * Explanation: The valid words in the sentence are "he", "bought", "pencils,", "erasers,", "and", and "pencil-sharpener.".
 */
public class NumberValidWords {
    public static void main(String[] args) {

    }

    public int countValidWords(String sentence) {
        int res = 0;
        String[] strs = sentence.split(" ");
        for (String str : strs){
            if(str.length() > 0 && isValid(str)){
                res++;
            }
        }
        return res;
    }

    private boolean isValid(String str){
        int hyphenCount = 0;
        boolean containPunctuation = false;
        List<Character> list = List.of('!','.',',');
        int length = str.length();
        char c;
        for (int i = 0; i < length; i++) {
            c = str.charAt(i);
            if( c <= '9' && c >= '0')
                return false;
            if(containPunctuation)
                return false;
            if( list.contains(c))
                containPunctuation = true;
            if( c == '-' ){
                if(i == 0 || i == length - 1 ||  list.contains(str.charAt(i - 1)) || list.contains(str.charAt(i + 1)))
                    return false;
                else
                    hyphenCount++;
            }
        }
        return hyphenCount < 2;
    }
}
