package com.example.leetcode.challenge.test2020.november.week4;


import java.util.Arrays;

/**
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is less than or equal to k.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of only lowercase English letters.
 * 1 <= k <= 105
 */
public class LongestSubstring {
    public static void main(String[] args) {

    }

    public int longestSubstring(String s, int k) {
        int result = 0;
        int[] times = new int[26];
        char[] chars = s.toCharArray();
        for(char c: chars){
            times[c-'a']++;
        }
        if(Arrays.stream(times).filter(e-> e!=0 && e < k).sum() == 0)
            return s.length();

        int index = 0;
        for(int i = 0;i < chars.length;i++){
            if(times[chars[i]-'a'] < k ){
                int temp = longestSubstring(s.substring(i - index,i),k);
                if(result < temp)
                    result = temp;
                index = 0;
            } else {
                index++;
            }

        }
        if(index != 0){
            int temp = longestSubstring(s.substring(chars.length - index,chars.length),k);
            if(result < temp)
                result = temp;
        }
        return result;
    }
}
