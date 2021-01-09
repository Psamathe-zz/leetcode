package com.example.leetcode.challenge.test2020.June.week1;


/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *
 *
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 */
public class ReverseString {
    public static void main(String[] args) {
        char[] s = new char[]{'h','e','l','l','o'};
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString(s);
        s.toString();
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        char temp;
        while (left<right){
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }

    }
}
