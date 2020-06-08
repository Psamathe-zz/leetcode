package com.example.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s. You should re-order the string using the following algorithm:
 *
 * Pick the smallest character from s and append it to the result.
 * Pick the smallest character from s which is greater than the last appended character to the result and append it.
 * Repeat step 2 until you cannot pick more characters.
 * Pick the largest character from s and append it to the result.
 * Pick the largest character from s which is smaller than the last appended character to the result and append it.
 * Repeat step 5 until you cannot pick more characters.
 * Repeat the steps from 1 to 6 until you pick all characters from s.
 * In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.
 *
 * Return the result string after sorting s with this algorithm.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaaabbbbcccc"
 * Output: "abccbaabccba"
 * Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
 * After steps 4, 5 and 6 of the first iteration, result = "abccba"
 * First iteration is done. Now s = "aabbcc" and we go back to step 1
 * After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
 * After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
 * Example 2:
 *
 * Input: s = "rat"
 * Output: "art"
 * Explanation: The word "rat" becomes "art" after re-ordering it with the mentioned algorithm.
 * Example 3:
 *
 * Input: s = "leetcode"
 * Output: "cdelotee"
 * Example 4:
 *
 * Input: s = "ggggggg"
 * Output: "ggggggg"
 * Example 5:
 *
 * Input: s = "spo"
 * Output: "ops"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s contains only lower-case English letters.
 * Accepted
 * 14,235
 * Submissions
 * 18,892
 */
public class IncreasingDecreasingString {
    public static void main(String[] args) {
        String s = "";
        IncreasingDecreasingString increasingDecreasingString = new IncreasingDecreasingString();
        String result = increasingDecreasingString.sortString(s);
        System.out.println(result);
    }

    public String sortString(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        Map<Character,Integer> map = new HashMap<>();
        for(char c: s.toCharArray()){
            map.put(c,map.getOrDefault(c,0) + 1);
        }
        while(true){
            for(char ch='a';ch<='z';++ch){
                if(map.containsKey(ch)) {
                    int value = map.get(ch);
                    if (value > 0) {
                        map.put(ch, value - 1);
                        stringBuffer.append(ch);
                    }
                }
            }
            for(char ch='z';ch>='a';--ch){
                if(map.containsKey(ch)) {
                    int value = map.get(ch);
                    if (value > 0) {
                        map.put(ch, value - 1);
                        stringBuffer.append(ch);
                    }
                }
            }
            if(stringBuffer.length()==s.length())
                break;
        }
        return stringBuffer.toString();
    }
}
