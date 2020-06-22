package com.example.leetcode.medium;

public class Medium {

    public static void main(String[] args) {

        String stringToTest = "abcabcbb";
        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        int result = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(stringToTest);
        System.out.println(result);
    }
}
