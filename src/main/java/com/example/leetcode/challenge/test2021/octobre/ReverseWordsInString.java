package com.example.leetcode.challenge.test2021.octobre;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * Example 4:
 *
 * Input: s = "  Bob    Loves  Alice   "
 * Output: "Alice Loves Bob"
 */
public class ReverseWordsInString {
    public static void main(String[] args) {

    }

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if(chars[i] == ' '){
                if(temp.length() > 0){
                    stringBuilder.insert(0, temp);
                    stringBuilder.insert(0, chars[i]);
                    temp.setLength(0);
                }
            } else {
                temp.append(chars[i]);
            }
        }
        if(temp.length() > 0) {
            stringBuilder.insert(0, temp);
            return stringBuilder.toString();
        } else {
            return stringBuilder.substring(1, stringBuilder.length());
        }

    }
}
