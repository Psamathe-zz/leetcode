package com.example.leetcode.challenge.may.week1;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an arbitrary ransom note string and another string containing letters from all the magazines,
 * write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
 *
 * Each letter in the magazine string can only be used once in your ransom note.
 *
 * Note:
 * You may assume that both strings contain only lowercase letters.
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */
public class RansomNote {
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";

        RansomNote ransomNote = new RansomNote();
        boolean result = ransomNote.canConstruct(s1,s2);
        System.out.println(result);
    }

    public boolean canConstruct(String ransomNote, String magazine) {

        Map<Character,Integer> myMap = new HashMap<>();

        for(char c : magazine.toCharArray()){
            myMap.put(c,myMap.getOrDefault(c,0) + 1);
        }

        for(char c : ransomNote.toCharArray()){
            if(!myMap.containsKey(c) || myMap.get(c)==0){
                return false;
            } else {
                myMap.put(c,myMap.get(c) - 1);
            }

        }

        return true;
    }

    /**
     * faster solution
     */
    public boolean canConstructV1(String ransomNote, String magazine) {
        /// 1ms, 99.65%
        if (ransomNote.length() > magazine.length()) return false;

        int[] chars = new int[26];
        int counts = 0;
        for (int i = 0; i < ransomNote.length(); i++)
        {
            int index = ransomNote.charAt(i) - 'a';
            if (chars[index] == 0) counts++;
            chars[index]++;
        }

        for (int i = 0; i < magazine.length(); i++)
        {
            int index = magazine.charAt(i) - 'a';
            if (chars[index] == 1) counts--;
            if (counts == 0) return true;
            chars[index]--;
        }

        return counts == 0;
    }

    /**
     * less memory
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstructV3(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] charCounts = new int[26];
        for (char currentChar : magazine.toCharArray()) {
            charCounts[currentChar - 'a']++;
        }
        for (char currentChar : ransomNote.toCharArray()) {
            charCounts[currentChar - 'a']--;
            if (charCounts[currentChar - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
