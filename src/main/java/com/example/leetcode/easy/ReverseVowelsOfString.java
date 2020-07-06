package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * Example 1:
 *
 * Input: "hello"
 * Output: "holle"
 * Example 2:
 *
 * Input: "leetcode"
 * Output: "leotcede"
 * Note:
 * The vowels does not include the letter "y".
 */
public class ReverseVowelsOfString {
    public static void main(String[] args) {
        ReverseVowelsOfString reverseVowelsOfString = new ReverseVowelsOfString();
        reverseVowelsOfString.reverseVowels("hello");
    }

    /**
     * https://www.cnblogs.com/grandyang/p/5426682.html
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        list.add('A');
        list.add('E');
        list.add('I');
        list.add('O');
        list.add('U');
        char[] chars = s.toCharArray();
        int left = 0, right= chars.length - 1;
        while (left < right) {
            if (list.contains(chars[left]) && list.contains(chars[right])) {
                char t = chars[left];
                chars[left] = chars[right];
                chars[right] = t;
                left++;
                right--;
            } else if (list.contains(chars[left])) {
                --right;
            } else {
                ++left;
            }
        }
        return String.valueOf(chars);
    }

}
