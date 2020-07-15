package com.example.leetcode.challenge.July.week3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an input string, reverse the string word by word.
 *
 *
 *
 * Example 1:
 *
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Note:
 *
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Follow up:
 *
 * For C programmers, try to solve it in-place in O(1) extra space.
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        String s = "the sky is blue";
        ReverseWordsInString reverseWordsInString = new ReverseWordsInString();
        String res = reverseWordsInString.reverseWordsV1(s);
        System.out.println(res);
    }

    public String reverseWords(String s) {
        String[] list = s.trim().split(" ");
        List<String> ss = Arrays.stream(list).filter(e-> !e.isEmpty()).map(e->e+ " ").collect(Collectors.toList());
        Collections.reverse(ss);
        return ss.stream().collect(Collectors.joining()).trim();
    }

    /**
     * faster solution
     * @param s
     * @return
     */
    public String reverseWordsV1(String s) {
        if (s == null) {
            return s;
        }

        StringBuilder ans = new StringBuilder();

        for (int i=s.length()-1, j; i>=0; i--) {
            if (s.charAt(i) != ' ') {
                j = i; // j is pointing to the end char of the current word
                i = s.lastIndexOf(' ', i); // this returns the white space preceding the current word
                ans.append(s, i+1, j+1).append(" ");
            }
        }

        // if (ans.length() > 0) {
        //     ans.deleteCharAt(ans.length()-1);
        // }

        return ans.toString().trim();
    }
}
