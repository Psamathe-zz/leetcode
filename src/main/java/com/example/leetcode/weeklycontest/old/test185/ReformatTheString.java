package com.example.leetcode.weeklycontest.old.test185;

import java.util.ArrayList;
import java.util.List;

/**
 * Given alphanumeric string s. (Alphanumeric string is a string consisting of lowercase English letters and digits).
 *
 * You have to find a permutation of the string where no letter is followed by another letter and no digit is followed by another digit. That is, no two adjacent characters have the same type.
 *
 * Return the reformatted string or return an empty string if it is impossible to reformat the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a0b1c2"
 * Output: "0a1b2c"
 * Explanation: No two adjacent characters have the same type in "0a1b2c". "a0b1c2", "0a1b2c", "0c2a1b" are also valid permutations.
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: ""
 * Explanation: "leetcode" has only characters so we cannot separate them by digits.
 * Example 3:
 *
 * Input: s = "1229857369"
 * Output: ""
 * Explanation: "1229857369" has only digits so we cannot separate them by characters.
 * Example 4:
 *
 * Input: s = "covid2019"
 * Output: "c2o0v1i9d"
 * Example 5:
 *
 * Input: s = "ab123"
 * Output: "1a2b3"
 */
public class ReformatTheString {

    public static void main(String[] args) {
        String input = "a0b1c2";
        ReformatTheString reformatTheString = new ReformatTheString();
        String result = reformatTheString.reformat(input);
        System.out.println(result);
    }

    public String reformat(String s) {
        StringBuffer result = new StringBuffer();

        List<Character> digit = new ArrayList<>();
        List<Character> letter = new ArrayList<>();
        for(char c: s.toCharArray()){
            if(Character.getType(c) == Character.DECIMAL_DIGIT_NUMBER){
                digit.add(c);
            } else if(Character.getType(c) == Character.LOWERCASE_LETTER){
                letter.add(c);
            }
        }
        if(s == null || s.length() == 0 || Math.abs(digit.size() - letter.size()) > 1){
            return result.toString();
        }
        int index = 0;

        if(digit.size()>=letter.size()){
            while (index < letter.size()) {
                result.append(digit.get(index));
                result.append(letter.get(index));
                index++;
            }
            if(digit.size()>letter.size())
                result.append(digit.get(index));
        }else{
            while (index < digit.size()) {
                result.append(letter.get(index));
                result.append(digit.get(index));
                index++;
            }
            result.append(letter.get(index));
        }

        return result.toString();
    }

}
