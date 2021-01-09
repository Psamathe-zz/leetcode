package com.example.leetcode.challenge.test2021.January.week1;


import java.util.*;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 *
 * Input: s = ""
 * Output: 0
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "pwwkew";
        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        int res = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s);
        System.out.println(res);
    }
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Queue<Character> queue = new LinkedList<>();
        char c;
        char temp;
        int res = 0;
        for (int i = 0; i < length; i++) {
            c = s.charAt(i);
            if(queue.contains(c)){
                res = Math.max(res,queue.size());
                while ((temp = queue.poll()) != c){

                }
            }
            queue.offer(c);
        }

        res = Math.max(res,queue.size());
        return res;
    }

    public int lengthOfLongestSubstringV1(String s) {
        int start =0 , end = 0;
        HashMap<Character,Integer> map = new HashMap();
        int max = 0;
        while(end < s.length()){
            char c = s.charAt(end);
            if(map.containsKey(c)){
                start = Math.max(start,map.get(c)+1);
            }
            map.put(c,end);
            max = Math.max(max,end-start+1);
            end++;
        }

        return max;
    }
}
