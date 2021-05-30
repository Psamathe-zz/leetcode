package com.example.leetcode.challenge.test2021.may.week4;

/**
 * Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Hello"
 * Output: "hello"
 * Example 2:
 *
 * Input: s = "here"
 * Output: "here"
 * Example 3:
 *
 * Input: s = "LOVELY"
 * Output: "lovely"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of printable ASCII characters.
 */
public class ToLowerCase {
    public static void main(String[] args) {

    }

    public String toLowerCase(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c:s.toCharArray()){
            if(c >= 'A' && c <= 'Z')
                stringBuilder.append((char) (c+32));
            else
                stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
