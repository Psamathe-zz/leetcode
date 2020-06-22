package com.example.leetcode.hard;

/**
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
 * If multiple answers exist, you may return any of them.
 *
 * (A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 *
 * "bbbaaaba"
 * "bbababbb"
 * Output
 * "bbababbbaaaba"
 * Expected
 * "bbabaaababb"
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        String str1 =  "bbbaaaba";
        String str2 =  "bbababbb";
        ShortestCommonSupersequence shortestCommonSupersequence = new ShortestCommonSupersequence();
        String result = shortestCommonSupersequence.shortestCommonSupersequence(str1,str2);
        System.out.println(result);
    }

    public String shortestCommonSupersequence(String str1, String str2) {

        if( str1.equals(str2)||str1.split(str2).length >= 2)
            return str1;
        if(str2.split(str1).length >= 2)
            return str2;
        int back;
        int front;
        boolean isFront = false;
        int result = 0;
        for(back = 0; back < str1.length() && back < str2.length(); back++){
            String temp1 = str1.substring(str1.length() - back);
            String temp2 = str2.substring(0,back);
            if(temp1.equals(temp2) && result < back){
                result = back ;
            }
        }
        for(front = 0; front < str1.length() && front < str2.length(); front++){
            String temp1 = str1.substring(0,front);
            String temp2 = str2.substring(str2.length() - front);
            if(temp1.equals(temp2) && result < front){
                result = front;
                isFront = true;
            }
        }
        if(isFront){
            return str2 + str1.substring(result);
        } else{
            return str1 + str2.substring(result);
        }
    }
}
