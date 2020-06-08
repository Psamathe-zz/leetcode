package com.example.leetcode.easy;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = new String[]{"f"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String result = longestCommonPrefix.longestCommonPrefix(strs);
        System.out.println(result);
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        int length = Integer.MAX_VALUE;
        for(String str: strs){
            length = Math.min(length,str.length());
        }
        boolean done = false;
        int i;
        for(i = 0; i < length; i++){
            char c = strs[0].charAt(i);
            for(String str: strs){
                if(str.charAt(i) != c){
                    done = true;
                    break;
                }
            }
            if(done)
                break;
        }
        return strs[0].substring(0,i);
    }
}
