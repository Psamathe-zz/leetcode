package com.example.leetcode.challenge.test2021.february.week1;


/**
 * Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the shortest distance from s[i] to the character c in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "loveleetcode", c = "e"
 * Output: [3,2,1,0,1,0,0,1,2,2,1,0]
 * Example 2:
 *
 * Input: s = "aaab", c = "b"
 * Output: [3,2,1,0]
 */
public class ShortestDistance {
    public static void main(String[] args) {
        ShortestDistance shortestDistance = new ShortestDistance();
        shortestDistance.shortestToChar("loveleetcode",'e');
    }

    public int[] shortestToChar(String s, char c) {
        int length = s.length();
        int[] res = new int[length];
        int val;
        int curIndex = -1;
        for (int i = 0; i < length; i++) {
            if(s.charAt(i) == c){
                curIndex = i;
            } else {
                res[i] = curIndex == -1 ? Integer.MAX_VALUE: i - curIndex;
            }
        }
        curIndex = -1;
        for (int i = length - 1; i >=0 ; i--) {
            if(s.charAt(i) == c){
                curIndex = i;
            } else {
                val = curIndex == -1 ? Integer.MAX_VALUE: curIndex - i;
                res[i] = Math.min(res[i] , val);
            }
        }
        return res;
    }
}
