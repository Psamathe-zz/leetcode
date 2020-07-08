package com.example.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {
    public static void main(String[] args) {
        String s = "ab";
        String t = "aa";
        IsomorphicStrings isomorphicStrings = new IsomorphicStrings();
        isomorphicStrings.isIsomorphic(s,t);
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) != t.charAt(i)){
                return false;
            }

            if(!map.containsKey(s.charAt(i))){
                if(map.containsValue(t.charAt(i)))
                    return false;
                map.put(s.charAt(i),t.charAt(i));
            }
        }
        return true;
    }


    /**
     * faster solution
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphicV1(String s, String t) {
        char[] temp=new char[128];
        char[] temp1=new char[128];
        char[] S=s.toCharArray();
        char[] T=t.toCharArray();
        int length=s.length();
        for(int i=0;i<length;i++)
        {
            if(temp[S[i]]!='\0'||temp1[T[i]]!='\0')
            {
                if(temp1[T[i]]!=S[i])
                    return false;
            }else{
                temp[S[i]]=T[i];
                temp1[T[i]]=S[i];
            }
        }
        return true;
    }
}
