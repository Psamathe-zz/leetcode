package com.example.leetcode.weeklycontest.old.test263;

/**
 *A sentence is a list of tokens separated by a single space with no leading or trailing spaces. Every token is either a positive number consisting of digits 0-9 with no leading zeros, or a word consisting of lowercase English letters.
 *
 * For example, "a puppy has 2 eyes 4 legs" is a sentence with seven tokens: "2" and "4" are numbers and the other tokens such as "puppy" are words.
 * Given a string s representing a sentence, you need to check if all the numbers in s are strictly increasing from left to right (i.e., other than the last number, each number is strictly smaller than the number on its right in s).
 *
 * Return true if so, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * example-1
 * Input: s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles"
 * Output: true
 * Explanation: The numbers in s are: 1, 3, 4, 6, 12.
 * They are strictly increasing from left to right: 1 < 3 < 4 < 6 < 12.
 * Example 2:
 *
 * Input: s = "hello world 5 x 5"
 * Output: false
 * Explanation: The numbers in s are: 5, 5. They are not strictly increasing.
 * Example 3:
 *
 * example-3
 * Input: s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"
 * Output: false
 * Explanation: The numbers in s are: 7, 51, 50, 60. They are not strictly increasing.
 * Example 4:
 *
 * Input: s = "4 5 11 26"
 * Output: true
 * Explanation: The numbers in s are: 4, 5, 11, 26.
 * They are strictly increasing from left to right: 4 < 5 < 11 < 26.
 * "hello world 5 x 5"
 *
 */
public class CheckIfNumbersAreAscending {

    public static void main(String[] args) {
        CheckIfNumbersAreAscending checkIfNumbersAreAscending = new CheckIfNumbersAreAscending();
        boolean res = checkIfNumbersAreAscending.areNumbersAscending("sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s");
        System.out.println(res);
    }


    public boolean areNumbersAscending(String s) {
        int pre = 0;
        int cur = 0;
        boolean find = false;
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            char c = chars[i];
            if(c <= '9' && c >= '0'){
                cur = cur * 10 + (c - '0');
                find = true;
            } else {
                if(find) {
                    if(cur <= pre){
                        return false;
                    }
                    pre = cur;
                    cur = 0;
                    find = false;
                }
            }
        }
        if(cur <= pre){
            return false;
        }


        return true;
    }
}
