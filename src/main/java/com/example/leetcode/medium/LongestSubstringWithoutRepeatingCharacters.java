package com.example.leetcode.medium;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {


    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if(s.length() <= 1) return length;
        int result = 0;
        int tmp;
        for(int i = 0; i < length; i++){
            tmp = 1;
            int currentChar = (int) s.charAt(i);
            for(int j = i+1; j<length;j++){
                char charToTest = s.charAt(j);
                String subStr = s.substring(i,j);
                if(subStr.indexOf(charToTest)>=0){
                    break;
                } else {
                    tmp ++;
                }
            }
            if(tmp>result)
                result = tmp;
        }
        return result;
    }

    public int lengthOfLongestSubstringV2(String s) {
        if(s.length() <= 1) return s.length();

        HashMap<Character, Integer> charsLastIndexMap = new HashMap<>();

        int windowStartIndex = 0;
        int windowEndIndex = 0;

        int maxLen = 0;

        for(windowEndIndex=0; windowEndIndex<s.length(); windowEndIndex++){

            char currChar = s.charAt(windowEndIndex);

            if(charsLastIndexMap.containsKey(currChar)){
                windowStartIndex = Math.max(windowStartIndex, charsLastIndexMap.get(currChar) + 1); //Move start of window
            }

            maxLen = Math.max(maxLen, windowEndIndex - windowStartIndex + 1);                       //Update maxLen
            charsLastIndexMap.put(currChar, windowEndIndex);
        }

        return maxLen;
    }


    public int lengthOfLongestSubstringV3(String s) {
        if(s.length() <= 1) return s.length();

        Set<Character> charsLastIndexSet= new HashSet<>();

        int windowStartIndex = 0;
        int windowEndIndex = 0;

        int maxLen = 0;

        for(windowEndIndex=0; windowEndIndex<s.length(); windowEndIndex++){

            char currChar = s.charAt(windowEndIndex);

        }

        return maxLen;
    }



}
