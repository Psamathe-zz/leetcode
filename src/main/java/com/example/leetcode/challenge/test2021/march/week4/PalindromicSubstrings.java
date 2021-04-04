package com.example.leetcode.challenge.test2021.march.week4;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 *
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 *
 * Example 2:
 *
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 */
public class PalindromicSubstrings {
    public static void main(String[] args) {
        PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
        palindromicSubstrings.countSubstrings("aaa");
    }

    private int count=0;
    public int countSubstrings(String s) {

        for(int i=0;i<s.length();i++){
            countPalindormic(s,i,i);
            countPalindormic(s,i,i+1);
        }
        return count;
    }
    public void countPalindormic(String s, int start, int end){
        while(start>=0&&end<s.length()&&s.charAt(start)==s.charAt(end)){
            start--;
            end++;
            count++;
        }

    }

    public int countSubstringsVOld(String s) {
        int res = 0;
        int length = s.length();

        for (int i = 0; i < length; i++) {
            for (int j = length; j > i ; j--) {
                if(i + 1 == j)
                    res++;
                else if(new StringBuilder(s.substring(i,j)).reverse().toString().equals(s.substring(i,j))) {
                    res++;
                }
            }
        }
        return res;
    }
}
