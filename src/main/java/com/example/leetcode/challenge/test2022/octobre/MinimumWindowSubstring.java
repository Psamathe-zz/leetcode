package com.example.leetcode.challenge.test2022.octobre;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {

    }

    public String minWindow(String s, String t) {
        int result = Integer.MAX_VALUE;
        String resultStr = "";
        Map<Character,Integer> count1 = new HashMap<>();
        Map<Character,Integer> count2 = new HashMap<>();
        int sumToCount = t.length();
        for(char c: t.toCharArray()){
            count1.put(c,count1.getOrDefault(c,0) + 1);
            count2.put(c,count2.getOrDefault(c,0) + 1);
        }
        int left = 0;
        int right = 0;
        int length = s.length();
        while (left <= right){
            if(sumToCount == 0) {
                if(result > right - left){
                    result = right - left;
                    resultStr = s.substring(left,right);
                }

                if(count2.containsKey(s.charAt(left))){
                    if(count2.get(s.charAt(left)) < count1.get(s.charAt(left)) && count2.get(s.charAt(left)) >=0)
                        sumToCount++;
                    count2.put(s.charAt(left),count2.get(s.charAt(left)) + 1);
                }

                left++;

            } else if(sumToCount > 0) {
                if(right == length)
                    break;
                if(count2.containsKey(s.charAt(right))){
                    if( count2.get(s.charAt(right)) > 0)
                        sumToCount--;
                    count2.put(s.charAt(right),count2.get(s.charAt(right)) - 1);
                }
                right++;
            }
        }
        return resultStr;
    }
}
