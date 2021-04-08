package com.example.leetcode.challenge.test2021.april.week2;

import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 *
 *
 *
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 */
public class LetterCombinations {
    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        letterCombinations.letterCombinations("8");
    }
    public List<String> letterCombinations(String digits) {
        int length = digits.length();
        if(length == 0)
            return new ArrayList<>();
        Queue<char[]> queue = new LinkedList<>();
        queue.add(new char[length]);
        int digit;
        int size;
        int pre;
        int queueSize;
        char[] temp;
        char[] newChars;
        for (int i = 0; i < length; i++) {
            digit = digits.charAt(i) - '2';
            pre = 0;
            switch (digit){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    pre = digit * 3;
                    break;
                case 6:
                case 7:
                    pre = digit * 3 + 1;
                    break;
            }
            size = (digit == 5 || digit == 7) ? 4:3;
            queueSize = queue.size();
            while (queueSize > 0){
                temp = queue.poll();
                for (int j = 0; j < size; j++) {
                    newChars = Arrays.copyOf(temp,length);
                    newChars[i] = (char) (pre + j + 'a');
                    queue.add(newChars);
                }
                queueSize --;
            }
        }
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()){
            res.add(new String(queue.poll()));
        }
        return res;
    }

}
