package com.example.leetcode.hard;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String  s = "bba";
        String  t = "ab";
        MinimumWindowSubstring minimumWindowSubstring  = new MinimumWindowSubstring();
        minimumWindowSubstring.minWindowV3(s,t);
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

    /**
     * https://www.cnblogs.com/grandyang/p/4340948.html
     * "bba"
     * "ab"
     */

    public String minWindowV2(String s, String t) {
        String res = "";
        Map<Character, Integer> letterCnt = new HashMap<>();
        int left = 0, cnt = 0, minLen = Integer.MAX_VALUE;
        for (char c : t.toCharArray())
            letterCnt.put(c,letterCnt.getOrDefault(c,0) + 1);
        for (int i = 0; i < s.length(); ++i) {
            if (letterCnt.containsKey(s.charAt(i)) && letterCnt.get(s.charAt(i)) >= 0){
                letterCnt.put(s.charAt(i),letterCnt.get(s.charAt(i)) + 1);
                ++cnt;
            }
            while (cnt == t.length()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    res = s.substring(left, left + minLen);
                }
                if (letterCnt.containsKey(s.charAt(left)) && letterCnt.get(s.charAt(left)) > 0) {
                    letterCnt.put(s.charAt(left),letterCnt.get(s.charAt(left)) - 1);
                    --cnt;
                }
                ++left;
            }
        }
        return res;
    }

    String minWindowV3(String s, String t) {
        int[] letterCnt = new int[128];
        int left = 0, cnt = 0, minLeft = -1, minLen = Integer.MAX_VALUE;
        for (char c : t.toCharArray())
            letterCnt[c]++;
        for (int i = 0; i < s.length(); i++) {
            letterCnt[s.charAt(i)]--;
            if (letterCnt[s.charAt(i)] >= 0)
                cnt++;
            while (cnt == t.length()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    minLeft = left;
                }
                letterCnt[s.charAt(left)]++;
                if ( letterCnt[s.charAt(left)]> 0)
                    cnt--;
                left++;
            }
        }
        String result = (minLeft == -1 ? "" : s.substring(minLeft,minLeft + minLen));
        return result;
    }



}
