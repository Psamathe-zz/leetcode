package com.example.leetcode.medium;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * Example 4:
 *
 * Input: s = "abc3[cd]xyz"
 * Output: "abccdcdcdxyz"
 */
public class DecodeString {
    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        DecodeString decodeString = new DecodeString();
        String res = decodeString.decodeString(s);
        System.out.println(res);
    }

    public String decodeString(String s) {
        StringBuilder t = new StringBuilder();
        StringBuilder temp;
        Stack<Integer> s_num = new Stack<>();
        Stack<String> s_str = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                cnt = 10 * cnt + s.charAt(i) - '0';
            } else if (s.charAt(i) == '[') {
                s_num.push(cnt);
                s_str.push(t.toString());
                cnt = 0;
                t = new StringBuilder();
            } else if (s.charAt(i) == ']') {
                int k = s_num.peek();
                s_num.pop();
                temp = new StringBuilder(s_str.pop());
                for (int j = 0; j < k; ++j)
                    temp.append(t);
                t = temp;
            } else {
                t.append(s.charAt(i));
            }
        }
        return t.toString();
    }
}
